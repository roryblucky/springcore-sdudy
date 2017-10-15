package com.rory.springcore.study.factory;

import com.rory.springcore.study.BeanReference;
import com.rory.springcore.study.PropertyValue;
import com.rory.springcore.study.PropertyValues;

import java.lang.reflect.Field;

/**
 * 自动装配字段的容器
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    public Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        //利用反射机制创建bean
        Object bean = this.createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        //再进行字段的自动装配
        this.applyPropertyValues(bean, beanDefinition.getPropertyValues());
        return bean;
    }

    private void applyPropertyValues(Object bean, PropertyValues propertyValues) throws Exception {
        for (PropertyValue propertyValue : propertyValues.getPropertyValueList()) {
            Field field = bean.getClass().getDeclaredField(propertyValue.getName());
            field.setAccessible(true);
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                value = getBean(((BeanReference) value).getRefBeanClassName());
            }
            field.set(bean, value);
        }
    }

    private Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }
}
