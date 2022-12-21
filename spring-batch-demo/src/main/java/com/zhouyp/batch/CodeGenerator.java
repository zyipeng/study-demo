/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.batch;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.Test;

import java.util.Collections;

/**
 * <B>主类名称：</B>CodeGenerator<BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author 周毅鹏
 * @since 2022/5/31 下午 5:55
 */
public class CodeGenerator {

    @Test
    public void testXml() {

    }
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/spring_batch_demo?useUnicode=true&characterEncoding=UTF-8",
                        "root", "123456")
                .globalConfig(builder -> {
                    builder.author("周毅鹏") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\workspace\\ideaProjects\\study\\common\\demo\\spring-batch-demo\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.zhouyp") // 设置父包名
                            .moduleName("batch") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "C:\\workspace\\ideaProjects\\study\\common\\demo\\spring-batch-demo\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("message");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}