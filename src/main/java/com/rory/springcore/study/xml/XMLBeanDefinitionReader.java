package com.rory.springcore.study.xml;

import com.rory.springcore.study.AbstractBeanDefinitionReader;
import com.rory.springcore.study.PropertyValue;
import com.rory.springcore.study.factory.BeanDefinition;
import com.rory.springcore.study.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class XMLBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XMLBeanDefinitionReader(ResourceLoader resourceLoader) {
        super(resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws Exception {
        InputStream inputStream = getResourceLoader().gerResource(location).getInputStream();
        this.doLoadBeanDefinitions(inputStream);
    }

    private void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(inputStream);
        this.registerBeanDefinitions(document);
        inputStream.close();
    }

    private void registerBeanDefinitions(Document document) {
        Element root = document.getDocumentElement();

        this.parseBeanDefinitions(root);
    }

    private void parseBeanDefinitions(Element root) {
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                processBeanDefinition(ele);
            }
        }
    }

    private void processBeanDefinition(Element ele) {
        String name = ele.getAttribute("name");
        String className = ele.getAttribute("class");
        BeanDefinition beanDefinition = new BeanDefinition();
        processProperty(ele, beanDefinition);
        beanDefinition.setBeanClassName(className);
        getRegistry().put(name, beanDefinition);
    }

    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNode = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
            }
        }
    }

}
