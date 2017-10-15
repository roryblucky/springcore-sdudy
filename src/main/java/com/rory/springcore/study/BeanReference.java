package com.rory.springcore.study;

public class BeanReference {
    private String refBeanClassName;

    private Object referenceBean;

    public BeanReference(String refBeanClassName) {
        this.refBeanClassName = refBeanClassName;
    }

    public String getRefBeanClassName() {
        return refBeanClassName;
    }

    public void setRefBeanClassName(String refBeanClassName) {
        this.refBeanClassName = refBeanClassName;
    }

    public Object getReferenceBean() {
        return referenceBean;
    }

    public void setReferenceBean(Object referenceBean) {
        this.referenceBean = referenceBean;
    }
}
