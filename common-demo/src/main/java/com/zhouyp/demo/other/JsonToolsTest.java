package com.zhouyp.demo.other;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * <B>主类名称：</B><BR>
 * <B>概要说明：</B> json工具类 <BR>
 *
 * @author wsc
 * @since 2022/1/8 15:58
 */
public class JsonToolsTest {

    private static final ThreadLocal<ObjectMapper> THREAD_LOCAL = ThreadLocal.withInitial(ObjectMapper::new);

    public static <T> Optional<T> parseJsonToObject(String objectJson, Class<T> targetClass) {
        try {
            return Optional.of(THREAD_LOCAL.get().readValue(objectJson, targetClass));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static Optional<String> covertObjectToJson(Object object) {
        try {
            return Optional.of(THREAD_LOCAL.get().writeValueAsString(object));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public static <T> T parseJsonToObject(String str, TypeReference<T> typeReference) {
        try {
            return THREAD_LOCAL.get().readValue(str, typeReference);
        } catch (IOException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        System.out.println(JsonToolsTest.covertObjectToJson(integers).get());
    }
}