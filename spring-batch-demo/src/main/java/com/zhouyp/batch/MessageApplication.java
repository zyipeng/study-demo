/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.batch;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Date;

import static java.lang.String.format;

/**
 * <B>主类名称：</B>MessageApplication<BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author 周毅鹏
 * @since 2022/7/1 上午 9:32
 */
@Slf4j
public class MessageApplication {
    public static void main(String[] args) {
        String jobName = "messageMigrationJob";

        try {
            ConfigurableApplicationContext context = SpringApplication.run(MessageApplication.class, args);
            JobRegistry jobRegistry = context.getBean(JobRegistry.class);
            Job job = jobRegistry.getJob(jobName);
            JobLauncher jobLauncher = context.getBean(JobLauncher.class);
            JobExecution jobExecution = jobLauncher.run(job, createJobParams());
            if (!jobExecution.getExitStatus().equals(ExitStatus.COMPLETED)) {
                throw new RuntimeException(format("%s Job execution failed.", jobName));
            }
        } catch (Exception e) {
            log.error("",e);
            throw new RuntimeException(format("%s Job execution failed.", jobName));
        }
    }

    private static JobParameters createJobParams() {
        return new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
    }
}