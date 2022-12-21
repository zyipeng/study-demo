/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.xly.classload;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * <B>主类名称：</B><BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author XS-1009387
 * @since 2022/1/26 9:42
 */
public class MyClassLoader extends ClassLoader{
    private String path;

    public MyClassLoader(final String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        Class cl = null;
        final byte[] classDate = getData();
        if (classDate != null) {
            cl = defineClass(name, classDate, 0, classDate.length);
        }
        return cl;
    }
    private byte[] getData() {
        File file = new File(path);
        if (file.exists()) {
            FileInputStream fileInputStream = null;
            ByteArrayOutputStream byteArrayOutputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                int size = 0;
                while ((size = fileInputStream.read(bytes)) != -1) {
                    byteArrayOutputStream.write(bytes,0,size);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return byteArrayOutputStream.toByteArray();
        }
        return null;
    }
}