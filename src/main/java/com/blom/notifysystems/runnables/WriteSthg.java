package com.blom.notifysystems.runnables;

import com.blom.notifysystems.serviceimpl.ReadExcelFromDirectoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.lang.Thread.sleep;

public class WriteSthg implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(WriteSthg.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    @Override
    public void run() {
        {
            try {
                sleep(30000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
           logger.info("Write {}", dateTimeFormatter.format(LocalDateTime.now()) );

        }
    }
}
