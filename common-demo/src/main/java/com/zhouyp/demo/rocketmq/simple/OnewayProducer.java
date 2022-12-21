/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.rocketmq.simple;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * <B>主类名称：</B><BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author XS-1009387
 * @since 2022/1/20 11:05
 */
public class OnewayProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, UnsupportedEncodingException {
        final DefaultMQProducer producer = new DefaultMQProducer("producer-group-zhouyp-oneway");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.start();
        for (int i = 0; i < 100; i++) {
            final Message message = new Message("topic_zhou_oneway", "taga_zhouyp", "hello!!!".getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.sendOneway(message);
        }
        Thread.sleep(5000);
        producer.shutdown();
    }
}