package com.rory.springcore.study.beans.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Spring 容器抽象工厂的实现。实现BeanFactory的接口方法
 */

public abstract class AbstractBeanFactory implements BeanFactory {


    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private List<String> beanNames = new ArrayList<>();

    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        //getBean时再创建，即符合Spring的lazy-init方式
        Object bean = beanDefinition.getBean();
        if (bean == null) {
            bean = this.doCreateBean(beanDefinition);
            beanDefinition.setBean(bean);
        }
        return bean;
    }

    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        //此处未进行bean的创建，而是暂时存储beanDefinition， 当getBean的时候再创建（Spring lazy-init）。
        beanDefinitionMap.put(name, beanDefinition);
        //bean name, 此处是为Spring默认方式做准备，即容器启动，就创建所有实例。
        beanNames.add(name);

    }

    /**
     * Spring默认方式，即容器启动，就创建所有实例
     * @throws Exception
     */
    public void preInstantiateSingletons() throws Exception {
        for (String className : beanNames) {
            getBean(className);
        }
    }

    public abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
}
