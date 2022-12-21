/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.xly.classload;

/**
 * <B>主类名称：</B><BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author XS-1009387
 * @since 2022/1/26 9:39
 */
public class ClassLoaderParentMain {

    public static void main(String[] args){
        ClassLoader ClassLoader1 = ClassLoaderParentMain.class.getClassLoader();
        ClassLoader ClassLoader2 = ClassLoader1.getParent();
        ClassLoader ClassLoader3 = ClassLoader2.getParent();

        System.out.println(ClassLoader1);
        System.out.println(ClassLoader2);
        System.out.println(ClassLoader3);
    }
}