/*
 * frxs Inc.  湖南兴盛优选电子商务有限公司.
 * Copyright (c) 2017-2021. All Rights Reserved.
 */
package com.zhouyp.demo.other.xml.util;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.dom.DOMComment;
import org.dom4j.dom.DOMDocument;
import org.dom4j.dom.DOMElement;
import org.dom4j.dom.DOMText;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <B>主类名称：</B><BR>
 * <B>概要说明：</B> TODO <BR>
 *
 * @author zhouyp
 * @since 2022/2/6 17:25
 */
public class xml编辑 {
    public static void newPom(String oldPomPath, List<Dependency> logDependencys) throws DocumentException {
        final SAXReader saxReader = new SAXReader();
        //找不到pom文件抛出异常
        final Document oldPD = saxReader.read(oldPomPath);
        //解析pom.xml
        final Element dependenciesE = oldPD.getRootElement().element("dependencies");
        //如果存在相关依赖直接删除
        //增加新版本的元素
        String artifactIdRegex = "";
        List<DOMElement> logDepEles = new ArrayList<>();
        for (Dependency dependency : logDependencys) {
            logDepEles.add(POMDependency2XMLElement(dependency,dependenciesE.getNamespace()));
            artifactIdRegex+=dependency.getArtifactId()+"|";
        }
        artifactIdRegex = artifactIdRegex.substring(0, artifactIdRegex.length() - 1);
        final List<Element> dependencieEs = dependenciesE.elements();
        for (Element element:dependencieEs) {
            //判断element是否存在artifactId为xx的，存在就删除
            final String artifactId = element.element("artifactId").getText();
            if (artifactId.matches(artifactIdRegex)) {
                dependenciesE.remove(element);
            }
        }
        dependenciesE.add(new DOMComment("ext-plugin add log start."));
        for (DOMElement domElement : logDepEles) {
            dependenciesE.add(domElement);
        }
        dependenciesE.add(new DOMComment("ext-plugin add log end."));
        try (final FileWriter fileWriter = new FileWriter("output.xml")) {
            final OutputFormat format = OutputFormat.createPrettyPrint();
            final XMLWriter xmlWriter = new XMLWriter(fileWriter,format);
            xmlWriter.write(oldPD);
        } catch (IOException e) {
            System.out.println("未找到文件");
        }
    }
    public static DOMElement POMDependency2XMLElement(Dependency dep, Namespace namespace) {
        final DOMElement dependencyXML = new DOMElement("dependency");
        dependencyXML.setNamespace(namespace);
        final DOMElement groupId = new DOMElement("groupId");
        groupId.setText(dep.getGroupId());
        groupId.setNamespace(namespace);
        dependencyXML.appendChild(groupId);
        final DOMElement artifactId = new DOMElement("artifactId");
        artifactId.setText(dep.getArtifactId());
        artifactId.setNamespace(namespace);
        dependencyXML.appendChild(artifactId);
        final DOMElement version = new DOMElement("version");
        version.setText(dep.getVersion());
        version.setNamespace(namespace);
        dependencyXML.appendChild(version);
        final DOMElement scope = new DOMElement("scope");
        scope.setText(dep.getScope());
        scope.setNamespace(namespace);
        dependencyXML.appendChild(scope);
        return dependencyXML;
    }
    public static void main(String[] args) throws DocumentException, IOException, XmlPullParserException {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        final Model readMd = reader.read(new FileInputStream("C:\\Users\\XS-1009387\\.m2\\repository\\com\\xsyx\\foundation\\base\\xsyx-fd-log-dependency\\1.0.0-SNAPSHOT\\xsyx-fd-log-dependency-1.0.0-SNAPSHOT.pom"));
        final List<Dependency> dependencies = readMd.getDependencies();
        newPom("C:\\Users\\XS-1009387\\Desktop\\pom.xml",dependencies);
    }
}