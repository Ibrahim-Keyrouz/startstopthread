package com.blom.notifysystems.serviceimpl;

import com.blom.notifysystems.runnables.WriteSthg;
import com.blom.notifysystems.service.ReadExcelFromDirectoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledFuture;

import static java.lang.Thread.sleep;

@Service
public class ReadExcelFromDirectoryServiceImpl implements ReadExcelFromDirectoryService {

    @Autowired
   // List<ThreadPoolTaskScheduler> listTaskScheduler;
    ThreadPoolTaskScheduler taskScheduler;


    private List<ScheduledFuture<?>> scheduledFuture = new ArrayList<>();
    private static final Logger logger = LoggerFactory.getLogger(ReadExcelFromDirectoryServiceImpl.class);
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void startReadExcelFromDirectoryService() {
        logger.info("Started");

        logger.info("scheduledFuture.size() = "+scheduledFuture.size());
       // logger.info(listTaskScheduler.size()+"");
        if (scheduledFuture.size() == 0) {
            //  for (ThreadPoolTaskScheduler threadPoolTaskScheduler : listTaskScheduler) {

                for (int i =0;i<2;i++) {
                    logger.info("fetna "+i);
                    if (i==0)
                    scheduledFuture.add(taskScheduler.scheduleAtFixedRate(new WriteSthg(), Long.valueOf(2000)));

                    if (i==1)
                    scheduledFuture.add(taskScheduler.scheduleAtFixedRate(saySthg(), Long.valueOf(2000)));
                }

           // }

 }
    //    threadPoolTaskScheduler.setPoolSize(10);
     //   threadPoolTaskScheduler.setThreadNamePrefix("bob-pool-");
    //    threadPoolTaskScheduler.scheduleAtFixedRate(writeSthg(),Long.valueOf(2000));
   //     ScheduledTaskRegistrar scheduledTaskRegistrar = new ScheduledTaskRegistrar() ;
     //   scheduledTaskRegistrar.setTaskScheduler(threadPoolTaskScheduler);
    }

    @Override
    public void stopReadExcelFromDirectoryService() {
     //   threadPoolTaskScheduler.destroy();
     //   if (taskScheduler != null) {
    //        taskScheduler.destroy();
           // scheduledFuture.cancel(false);
     //   }

        for (int i=scheduledFuture.size() - 1 ;i >= 0;i--){
         while (!scheduledFuture.get(i).isDone()) {
                scheduledFuture.get(i).cancel(false);
            }
            scheduledFuture.remove(i);
        }



   }





    private Runnable saySthg() {
        return () -> logger.info("Say {}", dateTimeFormatter.format(LocalDateTime.now()) );
    }
}