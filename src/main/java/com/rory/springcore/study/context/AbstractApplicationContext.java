package com.rory.springcore.study.context;

import com.rory.springcore.study.beans.factory.AbstractBeanFactory;

public abstract class AbstractApplicationContext implements ApplicationContext {

    private AbstractBeanFactory beanFactory;

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object getBean(String name) throws Exception {
        return beanFactory.getBean(name);
    }

    protected AbstractBeanFactory getBeanFactory() {
        return beanFactory;
    }

    protected abstract void refresh() throws Exception;
}
