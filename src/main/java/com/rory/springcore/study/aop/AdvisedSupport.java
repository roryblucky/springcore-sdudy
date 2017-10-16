package com.rory.springcore.study.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 代理对象的元数据
 */
public class AdvisedSupport {
    private TargetSource targetSource;

    private MethodInterceptor interceptor;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getInterceptor() {
        return interceptor;
    }

    public void setInterceptor(MethodInterceptor interceptor) {
        this.interceptor = interceptor;
    }
}
