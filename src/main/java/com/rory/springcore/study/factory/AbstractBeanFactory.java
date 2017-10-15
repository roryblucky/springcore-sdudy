package com.rory.springcore.study.factory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Spring 容器抽象工厂的实现。实现BeanFactory的接口方法
 */

public abstract class AbstractBeanFactory implements BeanFactory {


    private Map<String, BeanDefinition> map = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String name) {
        return map.get(name).getBean();
    }

    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        Object bean = this.doCreateBean(beanDefinition);
        beanDefinition.setBean(bean);
        map.put(name, beanDefinition);
    }

    public abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
}
