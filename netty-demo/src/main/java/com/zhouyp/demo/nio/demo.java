/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Properties;

/**
 * <B>主类名称：</B>demo<BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author 周毅鹏
 * @since 2022/4/25 上午 9:35
 */
public class demo {

    public static void main(String[] args) throws IOException {
        String relativelyPath = System.getProperty("user.dir");
        FileInputStream input = new FileInputStream(relativelyPath + "/data/testin.txt");
        ReadableByteChannel source = input.getChannel();
        FileOutputStream output = new FileOutputStream(relativelyPath + "/data/testout.txt");
        WritableByteChannel destination = output.getChannel();
        copyData(source, destination);
        source.close();
        destination.close();
        System.out.println("Copy Data finished.");
    }

    private static void copyData(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocateDirect(20 * 1024);
        while (src.read(buffer) != -1) {
            // The buffer is used to drained
            buffer.flip();
            // keep sure that buffer was fully drained
            while (buffer.hasRemaining()) {
                dest.write(buffer);
            }
            buffer.clear(); // Now the buffer is empty, ready for the filling
        }
    }

    public void watch() throws IOException {
        final FileInputStream fileInputStream = new FileInputStream("");
        final FileChannel ch0 = fileInputStream.getChannel();
        ch0.close();
        DatagramChannel ch1 = DatagramChannel.open();
        ch1.close();
        SocketChannel ch2 = SocketChannel.open();
        ch2.connect(new InetSocketAddress("localhost", 9));
        ch2.close();
        ServerSocketChannel ch3 = ServerSocketChannel.open();
        ch3.socket().bind (new InetSocketAddress (9));
        ch3.close();
    }
}