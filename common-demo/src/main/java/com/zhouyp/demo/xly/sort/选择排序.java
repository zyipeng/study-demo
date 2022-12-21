/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.xly.sort;

import java.util.Arrays;

/**
 * <B>主类名称：</B><BR>
 * <B>概要说明：</B>  <BR>
 *
 * @author zhouyp
 * @since 2022/1/27 14:29
 */
public class 选择排序 {
    public static void main(String[] args) {
        Integer[] data = {9, 8, 6, 7, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(data));
        selectSort(data);
        System.out.println(Arrays.toString(data));
        Integer[] data1 = {9, 8, 6, 7, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(data1));
        selectSortRecursion(data1,data1.length);
        System.out.println(Arrays.toString(data1));
    }

    public static void selectSort(Integer[] data) {
        //1.从序列中挑选最大的元素记住其位置,与最后一个元素位置交换（下次排序不需要考虑当前操作序列的最后一个元素）
        //2.从上一次序列除去最后一个元素作为新序列执行第一步操作，直到序列只剩下最后一个元素
        for (int i = data.length; i > 1; i--) {
            selectMax2End(data,i);
        }
    }

    public static void selectMax2End(Integer[] data, int end) {
        //寻找最大的位置
        int tmpIndex = 0;
        for (int j = 1; j < end; j++) {
            if (data[tmpIndex] < data[j]) {
                tmpIndex = j;
            }
        }
        //交换
        Integer tmp = data[end - 1];data[end - 1] = data[tmpIndex];data[tmpIndex] = tmp;
    }

    public static void selectSortRecursion(Integer[] data, int end) {
        //寻找最大的位置
        int tmpIndex = 0;
        for (int j = 1; j < end; j++) {
            if (data[tmpIndex] < data[j]) {
                tmpIndex = j;
            }
        }
        //交换
        Integer tmp = data[end - 1];data[end - 1] = data[tmpIndex];data[tmpIndex] = tmp;
        if (end > 1) {
            selectSortRecursion(data, --end);
        }
    }
}