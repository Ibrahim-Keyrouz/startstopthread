package com.blom.notifysystems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class NotifysystemsApplication {

    @Autowired
    Environment env;

    public static void main(String[] args) {
        SpringApplication.run(NotifysystemsApplication.class, args);
    }


    @Bean
    ThreadPoolTaskScheduler threadPoolTaskScheduler() {

            ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
            threadPoolTaskScheduler.setPoolSize(Integer.valueOf(env.getProperty("notifysystems.threadpooltaskscheduler.nbrofpools")));
            threadPoolTaskScheduler.setThreadNamePrefix(env.getProperty("notifysystems.threadpooltaskscheduler.threadnameprefix"));

        return threadPoolTaskScheduler;

    }


}
