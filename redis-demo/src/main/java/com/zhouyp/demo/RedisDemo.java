/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <B>主类名称：</B>RedisDemo<BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author 周毅鹏
 * @since 2022/4/28 下午 3:14
 */
public class RedisDemo {
    public static void main(String[] args) {
        final RedisAsyncCommands<String, String> async = RedisClient
                .create("redis://localhost:6379/0")
                .connect().async();
        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 1; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread()+"start...");
                for (int i1 = 100000000; i1 >= 1; i1--) {
                    final String oldOpenId = UUID.randomUUID().toString().replace("-", "");
                    long hashL = Integer.valueOf(oldOpenId.hashCode()).longValue();
                    if (hashL < 0) {
                        hashL = (1L<<32)-hashL;
                    }
                    final long dbKey = hashL >> 9;
                    final String hValue = "";
                    async.hset(Long.toString(dbKey), oldOpenId, hValue);
                    System.out.println(Thread.currentThread()+","+dbKey+","+oldOpenId);
                }
                System.out.println(Thread.currentThread()+"finished...");
            });
        }
    }
}