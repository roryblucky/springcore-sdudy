package com.rory.springcore.study.aop;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 *  MethodInvocation AOP对应的接入点
 * A method invocation is a joinpoint and can be intercepted by a method
 * interceptor
 *  此处ReflectiveMethodInvocation仅为逻辑类，可以获取参数，但是实现MethodInvocation，必须包含Method，target和args属性
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
    private Method method;

    private Object target;

    private Object[] args;

    public ReflectiveMethodInvocation(Method method, Object target, Object[] args) {
        this.method = method;
        this.target = target;
        this.args = args;
    }

    @Override
    public Method getMethod() {
        return method;
    }

    @Override
    public Object[] getArguments() {
        return args;
    }

    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, args);
    }

    @Override
    public Object getThis() {
        return target;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
