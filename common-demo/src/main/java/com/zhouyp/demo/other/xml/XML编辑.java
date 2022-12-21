/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.other.xml;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.Node;
import org.dom4j.Text;
import org.dom4j.bean.BeanElement;
import org.dom4j.dom.DOMElement;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultElement;
import org.dom4j.tree.DefaultEntity;
import org.dom4j.tree.DefaultText;
import org.jaxen.function.xslt.DocumentFunction;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * <B>主类名称：</B><BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author zhouyp
 * @since 2022/2/6 9:43
 */
public class XML编辑 {
    //dom4j编辑
    public static void main(String[] args) throws DocumentException {
        final SAXReader saxReader = new SAXReader();
        final Document document = saxReader.read("C:\\Users\\XS-1009387\\Desktop\\pom.xml");
        final Element rootElement = document.getRootElement();
        final Element dependencies = rootElement.element("dependencies");

        final Document logDocument = saxReader.read("C:\\workspace\\tmp\\gitlab-test\\demo\\src\\main\\resources\\xml\\log.pom.xml");

//        final List<Element> logDependencies = logDocument.getRootElement().elements();
        final Element dependenciesE = logDocument.getRootElement().element("dependencies");

        /*old*/
        final List<Element> logDependencies = dependenciesE.elements();
        dependencies.add(new DefaultText("\n      "));
        for (Element logNode : logDependencies) {
            final Element version = logNode.element("version");
            version.setText("123");
            dependencies.add(new DefaultText("  "));
            dependencies.add(logNode.detach());
            dependencies.add(new DefaultText("\n      "));
        }
        dependencies.add(new DefaultText("\n"));
        /*old*/
        try (final FileWriter fileWriter = new FileWriter("output.xml")) {
            final XMLWriter xmlWriter = new XMLWriter(fileWriter);
            xmlWriter.write(document);
        } catch (IOException e) {
            System.out.println("未找到文件");
        }
    }
}