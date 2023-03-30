package com.blom.notifysystems.runnables;

import com.blom.notifysystems.model.EmailDetails;
import com.blom.notifysystems.service.EmailService;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

public class ExcelManagement implements Runnable {


    private static final Logger logger = LoggerFactory.getLogger(ExcelManagement.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private String comparingCellInExcel;
    private String minimumPercentageToNotify;
    private String filePath;
    private String emailRecipient;
    private String emailSubject;
    private EmailService emailService;

    public ExcelManagement(EmailService emailService, String emailRecipient, String emailSubject, String comparingCellInExcel, String minimumPercentageToNotify, String filePath) {
        this.comparingCellInExcel = comparingCellInExcel;
        this.minimumPercentageToNotify = minimumPercentageToNotify;
        this.filePath = filePath;
        this.emailRecipient = emailRecipient;
        this.emailSubject = emailSubject;
        this.emailService = emailService;
    }

    @Override
    public void run() {

        try (Stream<Path> paths = Files.walk(Paths.get(filePath))) {
            paths.filter(Files::isRegularFile)
                .forEach(file -> {
                try {
                    System.out.println("hi ");
                    sendEmail(file.toString());
                } catch (IOException e) {
                    throw new RuntimeException("bob3 " +e);
                }
            });


        }catch (Exception e) {
            System.out.println("bob2 " +e);
        }

    }
    private void sendEmail(String filePath) throws IOException {
        FileInputStream file = null;
        try {
             file = new FileInputStream(new File(filePath));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                XSSFRow row = sheet.getRow(i);

                if (row.getCell(Integer.valueOf(comparingCellInExcel)).getNumericCellValue() < Integer.valueOf(minimumPercentageToNotify)) {
                    EmailDetails ed = new EmailDetails();
                    ed.setRecipient(emailRecipient);
                    ed.setSubject(emailSubject);
                    for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {

                        DataFormatter dataformatter = new DataFormatter();
                        System.out.print(dataformatter.formatCellValue(row.getCell(j)) + " ");
                        ed.setMsgBody(ed.getMsgBody() + " ");


                    }
                    emailService.sendSimpleMail(ed);
                    System.out.println();
                }
                //
            }

         } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new IOException("bob1 " +e);
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                throw new RuntimeException("bob0 " +e);
            }
        }


    }


}