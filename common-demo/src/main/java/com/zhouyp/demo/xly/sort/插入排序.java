/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.xly.sort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <B>主类名称：</B><BR>
 * <B>概要说明：</B>  <BR>
 *
 * @author zhouyp
 * @since 2022/1/27 15:14
 */
public class 插入排序 {
    //将第一待排序序列第一个元素看做一个有序序列，把第二个元素到最后一个元素当成是未排序序列。
    //从头到尾依次扫描未排序序列，将扫描到的每个元素插入有序序列的适当位置。（如果待插入的元素与有序序列中的某个元素相等，则将待插入元素插入到相等元素的后面。）

    public static void main(String[] args) {
        Integer[] data = {9, 8, 6, 7, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(data));
        data = insertSort(data);
        System.out.println(Arrays.toString(data));
        Integer[] data1 = {9, 8, 6, 7, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(data1));
        insertSort1(data1);
        System.out.println(Arrays.toString(data1));
        Integer[] data2 = {9, 8, 6, 7, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(data2));
        final LinkedL<Integer> integerLinkedL = new LinkedL<>();
        insertSort2(data2,integerLinkedL);
        integerLinkedL.println();
    }

    /**
     * <B>方法名称：</B>insertSort<BR>
     * <B>概要说明：</B> 新建一个链表，作为排序好的序列<BR>
     *
     * @param data 字段注释
     * @return void
     * @author
     * @since 2022/1/27 15:41
     */
    public static Integer[] insertSort(Integer[] data) {
        List<Integer> res = new LinkedList<Integer>();
        res.add(data[0]);
        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < res.size(); j++) {
                if (res.get(j) > data[i]) {
                    res.add(j, data[i]);
                    break;
                }
            }
        }
        return res.toArray(new Integer[res.size()]);
    }

    /**
     * <B>方法名称：</B>insertSort1<BR>
     * <B>概要说明：</B>同一个数组序列上操作，从后往前同时把值大的往后移<BR>
     *
     * @param data 字段注释
     * @return void
     * @author
     * @since 2022/1/27 15:42
     */
    public static void insertSort1(Integer[] data) {
        for (int i = 1; i < data.length; i++) {
            Integer tmp = data[i];
            int j = i;
            while (j > 0 && data[j - 1] > tmp) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = tmp;
        }
    }

    /**
     * <B>方法名称：</B>insertSort2<BR>
     * <B>概要说明：</B>创建简单链表对象实现插入排序<BR>
     *
     * @param data 字段注释
     * @return void
     * @author
     * @since 2022/1/27 16:16
     */
    public static void insertSort2(Integer[] data, LinkedL linkedL) {
        linkedL.insertS(data[0]);
        for (int i = 1; i < data.length; i++) {
            Node<Integer> node = linkedL.head;
            while (node != null) {
                final Integer e = node.e;
                if (e > data[i]) {
                    linkedL.insertS(node,data[i]);
                    break;
                }
                node = node.next;
            }
        }
    }

}