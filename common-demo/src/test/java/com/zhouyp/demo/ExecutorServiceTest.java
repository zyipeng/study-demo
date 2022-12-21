/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <B>主类名称：</B>ExecutorServiceTest<BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author 周毅鹏
 * @since 2022/8/1 下午 6:02
 */
@Slf4j
public class ExecutorServiceTest {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        CountDownLatch cdl = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            final int finalI = i;
            es.execute(()->{
                try {
                    Thread.sleep(RandomUtils.nextInt(5000,7000));
                    log.info("finish thread{}", finalI);
                } catch (InterruptedException e) {
                    log.error("", e);
                }finally {
                    cdl.countDown();
                }
            });
        }
        cdl.await();
//        es.shutdown();
//        final boolean b = es.awaitTermination(1, TimeUnit.SECONDS);
        log.info("finish.");
        es.execute(()->{
            log.info("execute");
        });
//        es.shutdown();
    }
}