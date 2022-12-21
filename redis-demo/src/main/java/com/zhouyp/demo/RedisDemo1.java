/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * <B>主类名称：</B>RedisDemo<BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author 周毅鹏
 * @since 2022/4/28 下午 3:14
 */
@Slf4j
public class RedisDemo1 {
    static JedisPool pool = new JedisPool();//default "localhost", 6379
    static int[] count = new int[8];
    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>();
        for (int i0 = 0; i0 < 8; i0++) {
            final int finalI = i0;
            final Thread thread = new Thread(() -> {
                try (Jedis jedis = pool.getResource()) {
                    for (int i = 8000000; i >= 1; i--) {
                        final String oldOpenId = UUID.randomUUID().toString();
                        long hashL = Integer.valueOf(oldOpenId.hashCode()).longValue();
                        if (hashL < 0) {
                            hashL = (1L << 32) - hashL;
                        }
                        final long dbKey = hashL >> 9;
                        final String hValue = "";
                        jedis.hset(Long.toString(dbKey), oldOpenId, hValue);
                        ++count[finalI];
//                        log.error(finalI+"thread-" + ++count[finalI]);
//                            log.error(Thread.currentThread() + "," + dbKey + "," + oldOpenId);
                    }
                }
            });
//            thread.setName("thread-"+i0);
            thread.start();
            threads.add(thread);
        }
        while (threads.stream().anyMatch(t -> t.isAlive())) {
            try {
                Thread.sleep(60000);
                for (int i : count) {
                    System.out.print(i+",");
                }
                System.out.println();
            } catch (InterruptedException e) {
                log.error("main-", e);
            }
        }
        long total = 0L;
        for (int i : count) {
            total+=i;
        }
        log.error("insert data["+total+"] finish");
    }
}