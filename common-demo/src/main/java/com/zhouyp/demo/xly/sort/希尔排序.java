/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.xly.sort;

import java.util.Arrays;

/**
 * <B>主类名称：</B><BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author zhouyp
 * @since 2022/1/28 8:59
 */
public class 希尔排序 {
    public static void main(String[] args) {
        Integer[] data = {9, 8, 6, 7, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(data));
        shellSort(data);
        System.out.println(Arrays.toString(data));
    }
    public static void shellSort(Integer[] data) {
        //遍历步长的序列
        for (int gap = data.length / 2; gap >= 1; gap /= 2) {
            //分成以gap为步长的数组，对起始点为k步长为gap的数组进行插入排序
            for (int k = 0; k < gap; k++) {
                //对指定序列执行插入排序
                insertSort(data,k,gap);
            }
        }
    }

    /**
     * <B>方法名称：</B>insertSort<BR>
     * <B>概要说明：</B>自定义起始位置和步长的序列的插入排序<BR>
     *
     * @author
     * @since 2022/1/28 10:36
         * @param data 排序序列所在数组
         * @param start 排序开始位置
         * @param gap 序列所在数组步长
     * @return void
     */
    public static void insertSort(Integer[] data, int start, int gap) {
        for (int i = gap+start; i < data.length; i += gap) {
            int j = i-gap;
            Integer tmp = data[i];
            //用tmp记录方式，而不用交换方式，是因为交换的时需要走更多的步骤
            while (j >= 0 && data[j] > tmp) {
                data[j + gap] = data[j];
                j -= gap;
            }
            data[j+gap] = tmp;
        }
    }
}