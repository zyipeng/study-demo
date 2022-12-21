/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.xly.sort;

import java.util.Arrays;

/**
 * <B>主类名称：</B>冒泡排序主类<BR>
 * <B>概要说明：</B>  冒泡排序学习<BR>
 *
 * @author zhouyp
 * @since 2022/1/27 10:43
 */
public class 冒泡排序 {
    public static void main(String[] args) {
        //正常版本的冒泡排序
        Integer[] data = {9, 8, 6, 7, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(data));
        bubbleSort(data);
        System.out.println(Arrays.toString(data));

        //递归版本的冒泡
        Integer[] data1 = {9, 8, 6, 7, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(data1));
        bubbleSortRecursion(data1,data1.length);
        System.out.println(Arrays.toString(data1));
    }
    /**
     * <B>方法名称：</B>bubbleSort<BR>
     * <B>概要说明：</B>  冒泡排序实现<BR>
     *
     * @author
     * @since 2022/1/27 10:45
         * @param data  需要排序的数据
     * @return void
     */
    public static void bubbleSort(Integer[] data) {
        // 1.从第一个元素到倒数第二个元素遍历，对比当前元素与后面元素，把值大的放后面（本次结束将能保证最后元素最大）
        // 2.忽略第一步操作序列中的最后一个元素，对前面的序列按照第一步操作，直到元素只剩下一个的时候结束
        for (int i = data.length; i > 0; i--) {
            for (int j = 0; j < i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int tmp = data[j];data[j] = data[j + 1];data[j+1] = tmp;
                }
            }
        }
    }

    /**
     * <B>方法名称：</B>bubbleSortRecursion<BR>
     * <B>概要说明：</B> 递归版本的冒泡排序<BR>
     *
     * @author 
     * @since 2022/1/27 11:28
         * @param data  排序序列数组
         * @param end  排序序列长度
     * @return void
     */
    public static void bubbleSortRecursion(Integer[] data, int end) {
        for (int i = 0; i < end - 1; i++) {
            if (data[i] > data[i + 1]) {
                int tmp = data[i];
                data[i] = data[i + 1];
                data[i+1] = tmp;
            }
        }
        if (end > 1) {
            bubbleSortRecursion(data,--end);
        }
    }
}