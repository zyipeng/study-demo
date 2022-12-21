/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Vector;

/**
 * <B>主类名称：</B>DynamicProxyTest<BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author 周毅鹏
 * @since 2022/8/8 下午 2:38
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        {
            Renter renter = new Renter();
            RenterInvocationHandle<Person> renterInvocationHandle = new RenterInvocationHandle<>(renter);
            Person renterProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, renterInvocationHandle);
            renterProxy.rentHouse();
            renterProxy = renter;
        }
        System.gc();
        System.out.println("123");
    }
}
