package com.rory.springcore.study;

import com.rory.springcore.study.factory.AbstractBeanFactory;
import com.rory.springcore.study.factory.AutowireCapableBeanFactory;
import com.rory.springcore.study.factory.BeanDefinition;
import com.rory.springcore.study.factory.BeanFactory;
import com.rory.springcore.study.io.ResourceLoader;
import com.rory.springcore.study.xml.XMLBeanDefinitionReader;
import org.junit.Test;

import java.util.Map;

public class BeanFactoryTest {

    @Test
    public void testLazyInit() throws Exception {
        //读取Bean配置
        XMLBeanDefinitionReader xmlBeanDefinitionReader = new XMLBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("bean.xml");

        //初始化BeanFactory并注册bean，此处和master不一样，仅注册，不创建
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> entry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }

        //取出bean
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.helloWorld();
    }

    @Test
    public void testPreInitiated() throws Exception {
        //读取Bean配置
        XMLBeanDefinitionReader xmlBeanDefinitionReader = new XMLBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("bean.xml");

        //初始化BeanFactory并注册bean，此处和master不一样，仅注册，不创建
        AbstractBeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> entry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }

        //初始化全部的bean，并创建
        beanFactory.preInstantiateSingletons();

        //取出bean
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.helloWorld();

    }
}
