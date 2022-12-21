/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.xly.classload;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * <B>主类名称：</B><BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author XS-1009387
 * @since 2022/1/26 9:56
 */
public class ClassLoaderMain {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        String classPath = "C:\\workspace\\tmp\\类加载\\ClassloadInit.class";
        MyClassLoader myClassLoader = new MyClassLoader(classPath);
        String packagePath = "ClassloadInit";
        final Class<?> aClass = myClassLoader.loadClass(packagePath);
        System.out.println("类加载器是：" + aClass.getClassLoader());
        final Method main = aClass.getDeclaredMethod("main", String[].class);
        final Object instance = aClass.newInstance();
        String[] as = {"ab"};
        main.invoke(instance, (Object) as);
    }
}