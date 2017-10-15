package com.rory.springcore.study.context;

import com.rory.springcore.study.beans.factory.AbstractBeanFactory;
import com.rory.springcore.study.beans.factory.AutowireCapableBeanFactory;
import com.rory.springcore.study.beans.factory.BeanDefinition;
import com.rory.springcore.study.beans.io.ResourceLoader;
import com.rory.springcore.study.beans.xml.XMLBeanDefinitionReader;

import java.util.Map;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {

    private String xmlConfigLocation;

    public ClassPathXmlApplicationContext(String xmlConfigLocation) throws Exception {
        this(xmlConfigLocation, new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String xmlConfigLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.xmlConfigLocation = xmlConfigLocation;
        refresh();
    }


    @Override
    protected void refresh() throws Exception {
        XMLBeanDefinitionReader xmlBeanDefinitionReader = new XMLBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(xmlConfigLocation);

        for (Map.Entry<String, BeanDefinition> entry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            getBeanFactory().registerBeanDefinition(entry.getKey(), entry.getValue());
        }
    }
}
