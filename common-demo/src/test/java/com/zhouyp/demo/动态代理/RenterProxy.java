/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.动态代理;

/**
 * <B>主类名称：</B>RenterProxy<BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author 周毅鹏
 * @since 2022/8/8 下午 2:46
 */
public class RenterProxy implements Person {
    Person renter;

    public RenterProxy(final Person renter) {
        this.renter = renter;
    }

    @Override
    public void rentHouse() {
        System.out.println("中介找房，转租！！");
        renter.rentHouse();
        System.out.println("中介给租客钥匙，租客入住！！！");
    }
}