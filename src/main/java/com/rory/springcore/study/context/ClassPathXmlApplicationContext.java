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
        //加载bean配置并注册到容器中
        loadBeanDefinitions(getBeanFactory());
        //进行bean初始化，此处提前创建javabean，无法解决循环依赖的问题,当前测试用例为循环依赖，先注释掉
//        onRefresh();
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
        XMLBeanDefinitionReader xmlBeanDefinitionReader = new XMLBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions(xmlConfigLocation);

        for (Map.Entry<String, BeanDefinition> entry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(entry.getKey(), entry.getValue());
        }
    }

}
