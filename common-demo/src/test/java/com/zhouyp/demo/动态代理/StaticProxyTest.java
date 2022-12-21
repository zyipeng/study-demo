/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.动态代理;

/**
 * <B>主类名称：</B>StaticProxyTest<BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author 周毅鹏
 * @since 2022/8/8 下午 2:29
 */
public class StaticProxyTest {

    public static void main(String[] args) {
        final Renter renter = new Renter();
        final RenterProxy renterProxy = new RenterProxy(renter);
        renterProxy.rentHouse();
    }

}

