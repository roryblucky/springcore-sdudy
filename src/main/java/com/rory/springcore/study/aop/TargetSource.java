package com.rory.springcore.study.aop;

/**
 * 被代理的目标对象
 */
public class TargetSource {
    private Class targetClass;
    private Object target;

    public TargetSource(Class targetClass, Object target) {
        this.targetClass = targetClass;
        this.target = target;
    }

    public Class getTargetClass() {
        return targetClass;
    }

    public Object getTarget() {
        return target;
    }
}
