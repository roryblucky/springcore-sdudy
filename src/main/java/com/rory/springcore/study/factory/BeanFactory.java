package com.rory.springcore.study.factory;

/**
 * Spring 容器接口
 */
public interface BeanFactory {
    Object getBean(String name);

    void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception;
}
