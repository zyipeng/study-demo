/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.springbootutil;

import lombok.val;
import org.springframework.util.ObjectUtils;

/**
 * <B>主类名称：</B>UseUtil<BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author 周毅鹏
 * @since 2022/6/29 上午 9:29
 */
public class UseUtil {
    public static void main(String[] args) {
        final String value = new String();
        final val i = ObjectUtils.nullSafeHashCode(value);
        System.out.println(i);
    }
}