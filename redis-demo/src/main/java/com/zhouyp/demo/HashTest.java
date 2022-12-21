/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * <B>主类名称：</B>HashTest<BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author 周毅鹏
 * @since 2022/4/29 下午 2:22
 */
@Slf4j
public class HashTest {
    static int[] count = new int[8];
    public static void main(String[] args) {
        log.info("123");
/*        List<Thread> threads = new ArrayList<>();
//        Integer[] kv = new Integer[1+(2 << 23)];
        int[] kv_int = new int[1 + (2 << 23)];
        final List<AtomicInteger> kv = Arrays.stream(new int[1 + (2 << 23)]).mapToObj(i -> new AtomicInteger(i)).collect(Collectors.toList());
//        AtomicInteger[] kv = new AtomicInteger[1 + (2 << 23)];

        for (int i0 = 0; i0 < 8; i0++) {
            final int finalI = i0;
            final Thread thread = new Thread(() -> {
                    for (int i = 500000000; i >= 1; i--) {
                        final String oldOpenId = UUID.randomUUID().toString();
                        long hashL = Integer.valueOf(oldOpenId.hashCode()).longValue();
                        if (hashL < 0) {
                            hashL = (1L << 32) - hashL;
                        }
                        final long dbKey = hashL >> 9;
                        *//*synchronized (kv[(int) dbKey]) {
                            kv[(int) dbKey]++;
                        }*//*
                        kv.get((int) dbKey).incrementAndGet();
                        ++count[finalI];
                    }
            });
            thread.start();
            threads.add(thread);
        }
        while (threads.stream().anyMatch(t -> t.isAlive())) {
            try {
                Thread.sleep(1000);
                for (int i : count) {
                    System.out.print(i+",");
                }
                System.out.println();
            } catch (InterruptedException e) {
                log.error("main-", e);
            }
        }
        *//*for (int i = 0; i < (1 + (1 << 23)); i++) {
            if (kv[i].intValue() > 512) {
                System.out.println(kv[i].intValue()+","+i);
            }
        }*//*
        for (int i = 0; i < kv.size(); i++) {
            if (kv.get(i).intValue() > 512) {
                System.out.println(kv.get(i).intValue()+","+i);
            }
        }
        System.out.println("---kv check finish");
        long total = 0L;
        for (int i : count) {
            total+=i;
        }
        log.error("insert data["+total+"] finish");*/
    }
}