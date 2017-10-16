package com.rory.springcore.study.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private AdvisedSupport advisedSupport;

    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[] {advisedSupport.getTargetSource()
                .getTargetClass()}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return advisedSupport.getInterceptor().invoke(new ReflectiveMethodInvocation(method,
                advisedSupport.getTargetSource().getTarget(), args));
    }
}
