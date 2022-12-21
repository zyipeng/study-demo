/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.sms;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <B>主类名称：</B>SMS<BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author 周毅鹏
 * @since 2022/9/8 下午 7:34
 */
public class SMS {
    final static ExecutorService threadPool = Executors.newFixedThreadPool(100,
            new ThreadFactoryBuilder().setNameFormat("client-t%d").build());
    public static void main(String[] args) throws URISyntaxException, InterruptedException {
        //元保平台
        //https://exchange.yuanbaobaoxian.cn/gift/detail?insuranceId=201202163156780IB237865&version=0&channel=tgzxtopz_shuming_shuming_3263_cver&mobile=#99.91
        while (true) {
            final HashMap<String, String> map = new HashMap<>();
            //156 7493 0864
            map.put("765c5f1de4840fb1c52784b6bd036709", "eyJ1dWlkIjoiMjhjODgwNzAtMmYxOS0xMWVkLWIzNmQtNTEzZjRkYzRhZWU1IiwiY2hhbm5lbCI6InRnenh0b3B6X3NodW1pbmdfc2h1bWluZ18zMjYzX2N2ZXIiLCJjb21wYW55Q29kZSI6InliX2ppbmdqaSIsImFwcENvZGUiOiJoNV9hcHBfd2ViIiwidXJsX3BhdGhuYW1lIjoiL2dpZnQvZGV0YWlsIiwicGhvbmUiOiIxNTY3NDkzMDg2NCIsImN1clBhdGgiOiJodHRwczovL2V4Y2hhbmdlLnl1YW5iYW9iYW94aWFuLmNuL2dpZnQvZGV0YWlsP2luc3VyYW5jZUlkPTIwMTIwMjE2MzE1Njc4MElCMjM3ODY1JnZlcnNpb249MCZjaGFubmVsPXRnenh0b3B6X3NodW1pbmdfc2h1bWluZ18zMjYzX2N2ZXImbW9iaWxlPSM5OS45MSJ9");
            //171 5524 2141
            map.put("3baa40a057d52ff3447658a0389d8c2c", "eyJ1dWlkIjoiMjhjODgwNzAtMmYxOS0xMWVkLWIzNmQtNTEzZjRkYzRhZWU1IiwiY2hhbm5lbCI6InRnenh0b3B6X3NodW1pbmdfc2h1bWluZ18zMjYzX2N2ZXIiLCJjb21wYW55Q29kZSI6InliX2ppbmdqaSIsImFwcENvZGUiOiJoNV9hcHBfd2ViIiwidXJsX3BhdGhuYW1lIjoiL2dpZnQvZGV0YWlsIiwicGhvbmUiOiIxNzE1NTI0MjE0MSIsImN1clBhdGgiOiJodHRwczovL2V4Y2hhbmdlLnl1YW5iYW9iYW94aWFuLmNuL2dpZnQvZGV0YWlsP2luc3VyYW5jZUlkPTIwMTIwMjE2MzE1Njc4MElCMjM3ODY1JnZlcnNpb249MCZjaGFubmVsPXRnenh0b3B6X3NodW1pbmdfc2h1bWluZ18zMjYzX2N2ZXImbW9iaWxlPSM5OS45MSJ9");
            //133 1955 2335
            map.put("1a0746a12696261b6584385d91a7a86a", "eyJ1dWlkIjoiMjhjODgwNzAtMmYxOS0xMWVkLWIzNmQtNTEzZjRkYzRhZWU1IiwiY2hhbm5lbCI6InRnenh0b3B6X3NodW1pbmdfc2h1bWluZ18zMjYzX2N2ZXIiLCJjb21wYW55Q29kZSI6InliX2ppbmdqaSIsImFwcENvZGUiOiJoNV9hcHBfd2ViIiwidXJsX3BhdGhuYW1lIjoiL2dpZnQvZGV0YWlsIiwicGhvbmUiOiIxMzMxOTU1MjMzNSIsImN1clBhdGgiOiJodHRwczovL2V4Y2hhbmdlLnl1YW5iYW9iYW94aWFuLmNuL2dpZnQvZGV0YWlsP2luc3VyYW5jZUlkPTIwMTIwMjE2MzE1Njc4MElCMjM3ODY1JnZlcnNpb249MCZjaGFubmVsPXRnenh0b3B6X3NodW1pbmdfc2h1bWluZ18zMjYzX2N2ZXImbW9iaWxlPSM5OS45MSJ9");
            map.forEach(SMS::extracted);
            Thread.sleep(60000);
        }
    }

    private static void extracted(String sign,String msg){
        try {
            RestTemplate restTemplate = new RestTemplate();
            final URI uri = new URIBuilder().setScheme("https").setHost("passport.ybaobx.com").setPath("/api/login/getSmsCode")
                    .setParameter("msg", msg)
                    .setParameter("sign", sign).build();
            final ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(new RequestEntity<>(HttpMethod.POST, uri), JSONObject.class);
            final JSONObject body = responseEntity.getBody();
            System.out.println(body);
        } catch (URISyntaxException | RestClientException e) {
        }
    }
}