/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <B>主类名称：</B>RenterInvocationHandle<BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author 周毅鹏
 * @since 2022/8/8 下午 2:46
 */
public class RenterInvocationHandle<I> implements InvocationHandler {
    private I target;

    public RenterInvocationHandle(final I target) {
        this.target = target;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        System.out.println("租客和中介交流！！！");
        final Object res = method.invoke(target, args);
        return res;
    }
}