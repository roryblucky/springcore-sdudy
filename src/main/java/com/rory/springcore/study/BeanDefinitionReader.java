package com.rory.springcore.study;

/**
 * 从配置中读取bean的定义
 */
public interface BeanDefinitionReader {
    void loadBeanDefinitions(String location) throws Exception;
}
