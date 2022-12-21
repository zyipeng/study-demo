/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.动态代理;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * <B>主类名称：</B>ProxyFactory<BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author 周毅鹏
 * @since 2022/8/8 下午 2:51
 */
public class ProxyFactory<T> implements MethodInterceptor {
    private T target;

    public ProxyFactory(final T target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        final Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(final Object o, final Method method, final Object[] objects, final MethodProxy methodProxy) throws Throwable {
        System.out.println("事务开始");
        final Object invoke = method.invoke(target, objects);
        System.out.println("事务结束");
        return invoke;
    }
}