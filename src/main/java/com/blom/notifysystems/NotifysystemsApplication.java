package com.blom.notifysystems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class NotifysystemsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotifysystemsApplication.class, args);
    }

  /*  @Bean
    List<ThreadPoolTaskScheduler> threadPoolTaskScheduler() {
        List<ThreadPoolTaskScheduler> lsttpts = new ArrayList<ThreadPoolTaskScheduler>();
        for (int i=0;i<2;i++) {
            ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
            threadPoolTaskScheduler.setPoolSize(10);
            threadPoolTaskScheduler.setThreadNamePrefix("bob"+0+"-pool-");
            lsttpts.add(threadPoolTaskScheduler);

        }
        System.out.println(lsttpts.size());
        return lsttpts;

    }

*/
    @Bean
    ThreadPoolTaskScheduler threadPoolTaskScheduler() {

            ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
            threadPoolTaskScheduler.setPoolSize(2);
            threadPoolTaskScheduler.setThreadNamePrefix("bob-pool-");

        return threadPoolTaskScheduler;

    }


}
