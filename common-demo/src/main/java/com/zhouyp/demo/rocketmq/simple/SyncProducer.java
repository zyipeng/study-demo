/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.rocketmq.simple;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * <B>主类名称：</B>SyncProducer <BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author XS-1009387
 * @since 2022/1/20 10:52
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("produce_group_zhouyp");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        for (int i = 0; i < 100; i++) {
            final Message message = new Message("topic_zhouyp", "taga_zhouyp", ("hello!!!".getBytes(RemotingHelper.DEFAULT_CHARSET)));
            final SendResult send = producer.send(message);
            System.out.printf("%s%n",send);
        }
        producer.shutdown();
    }
}