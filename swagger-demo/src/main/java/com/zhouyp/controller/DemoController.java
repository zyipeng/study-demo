/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <B>主类名称：</B><BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author XS-1009387
 * @since 2022/3/7 16:54
 */
@RestController
public class DemoController {
    @GetMapping("/demo")
    public Object demo() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("key", "jsonkey");
        jsonObject.put("value", "jsonvalue");
        return jsonObject;
    }
}