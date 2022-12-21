/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.other.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import java.util.List;

/**
 * <B>主类名称：</B><BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author zhouyp
 * @since 2022/2/6 9:43
 */
public class XML编辑1 {
    //dom4j编辑
    public static void main(String[] args) throws DocumentException {
        final SAXReader saxReader = new SAXReader();
        final Document document = saxReader.read("C:\\Users\\XS-1009387\\Desktop\\abc.xml");
        final Element rootElement = document.getRootElement();
        final List<Node> nodes = document.selectNodes("/class/student");
//        try (final FileWriter fileWriter = new FileWriter("output.xml")) {
//            final XMLWriter xmlWriter = new XMLWriter(fileWriter);
//            xmlWriter.write(document);
//        } catch (IOException e) {
//            System.out.println("未找到文件");
//        }
        //test

    }
}