package com.rory.springcore.study;

import com.rory.springcore.study.factory.AutowireCapableBeanFactory;
import com.rory.springcore.study.factory.BeanDefinition;
import com.rory.springcore.study.factory.BeanFactory;
import com.rory.springcore.study.io.ResourceLoader;
import com.rory.springcore.study.xml.XMLBeanDefinitionReader;
import org.junit.Test;

import java.util.Map;

public class BeanFactoryTest {

    @Test
    public void test() throws Exception {
        //读取Bean配置
        XMLBeanDefinitionReader xmlBeanDefinitionReader = new XMLBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("bean.xml");

        //初始化BeanFactory并创建注册bean
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> entry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }

        //取出bean
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.helloWorld();
    }
}
