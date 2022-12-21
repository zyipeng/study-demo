/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * <B>主类名称：</B>FP<BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author 周毅鹏
 * @since 2022/7/28 下午 3:04
 */
@Slf4j
public class FP {
    static public void exe(){
        log.info("函数引用方法");
    }
    //    传统方式
    public interface I {
        void apply();
    }
    class C implements I{
        @Override
        public void apply() {
            log.info("实现接口方法");
        }
    }
    //对比
    @Test
    public void test01() {
        I[] is = {
                new C(),
                new I() {
                    @Override
                    public void apply() {
                        log.info("匿名内部类方法");
                    }
                },
                () -> log.info("lambda方式"),
                FP::exe
        };
        for (I i : is) {
            i.apply();
        }
    }
    class C1 {
        public C1() {
            System.out.println("C1::new");
        }
        public void ts() {
            System.out.println("C1.ts");
        }
    }
    interface I1 {
        void apply(C1 c1);
    }
    //未绑定的方法引用
    @Test
    public void test02() {
        I1 i1 = C1::ts;
        i1.apply(new C1());
    }
    interface I2{
        C1 apply();
    }
    //构造函数的引用
    @Test
    public void test03() {
        I2 i2 = C1::new;
        i2.apply();
    }
}