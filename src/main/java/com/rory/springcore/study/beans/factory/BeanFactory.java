package com.rory.springcore.study.beans.factory;

/**
 * Spring 容器接口
 */
public interface BeanFactory {
    Object getBean(String name) throws Exception;
}
