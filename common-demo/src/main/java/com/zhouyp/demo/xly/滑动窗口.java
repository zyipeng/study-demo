/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.xly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <B>主类名称：</B><BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author zhouyp
 * @since 2022/2/26 16:43
 */
public class 滑动窗口 {
    public static void main(String[] args) {
        findAnagrams("abc","cbaebabacd").stream().forEach(i->System.out.println(i));
    }

    public static List<Integer> findAnagrams(String subStr, String str) {
        List<Integer> res = new ArrayList<>();
        int left = 0, right = 0,valid=0;
        Map<Character, Integer> window = new HashMap(), map = new HashMap<>();
        subStr.chars().mapToObj(c -> (char) c).collect(Collectors.toList()).stream().forEach(c -> map.put(c, map.getOrDefault(c, 0) + 1));
        while (right < str.length()) {
            char c = str.charAt(right++);

            if (map.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (map.get(c).equals(window.get(c))) {
                    valid++;
                }
            }
            while (right - left == subStr.length()) {
                if (valid == map.size()) {
                    res.add(left);
                }
                char tmp = str.charAt(left++);
                if (map.containsKey(tmp)) {
                    if (map.get(tmp).equals(window.get(tmp))) {
                        valid--;
                    }
                    window.put(tmp, window.getOrDefault(tmp, 0) - 1);
                }
            }

        }
        return res;
    }
}