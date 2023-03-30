package com.blom.notifysystems.serviceimpl;


import com.blom.notifysystems.runnables.ExcelManagement;
import com.blom.notifysystems.service.EmailService;
import com.blom.notifysystems.service.ReadExcelFromDirectoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import static java.lang.Thread.sleep;

@Service
public class ReadExcelFromDirectoryServiceImpl implements ReadExcelFromDirectoryService {

    @Autowired
   // List<ThreadPoolTaskScheduler> listTaskScheduler;
    ThreadPoolTaskScheduler taskScheduler;

    @Autowired
    Environment env;

    @Autowired
    EmailService emailService;


    private final List<ScheduledFuture<?>> scheduledFuture = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(ReadExcelFromDirectoryServiceImpl.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void startReadExcelFromDirectoryService() {
        logger.info("Started");

        logger.info("scheduledFuture.size() = "+scheduledFuture.size());
        if (scheduledFuture.size() == 0) {
                for (int i =0;i<Integer.valueOf(env.getProperty("notifysystems.startreadexcelfromdirectoryservice.nbrofworkers"));i++) {
                    logger.info("fetna "+i);
                    if (i==0)
                    scheduledFuture.add(taskScheduler.scheduleAtFixedRate(new ExcelManagement(emailService,env.getProperty("spring.mail.recipient"),env.getProperty("spring.mail.subject"),env.getProperty("notifysystems.excelmanagement.comparingcellinexcel"),env.getProperty("notifysystems.excelmanagement.minimumpercentagetonotify"),env.getProperty("notifysystems.excelmanagement.path")), Long.valueOf(env.getProperty("notifysystems.threadpooltaskscheduler.nbrofpools.fixedrate"))));

                }


 }


    }

    @Override
    public void stopReadExcelFromDirectoryService() {

        for (int i=scheduledFuture.size() - 1 ;i >= 0;i--){
         while (!scheduledFuture.get(i).isDone()) {
                scheduledFuture.get(i).cancel(false);
            }
            scheduledFuture.remove(i);
        }



   }



}