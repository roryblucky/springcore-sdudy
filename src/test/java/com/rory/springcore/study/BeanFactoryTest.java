package com.rory.springcore.study;

import com.rory.springcore.study.context.ApplicationContext;
import com.rory.springcore.study.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class BeanFactoryTest {

    @Test
    public void test() throws Exception {
        //创建application context
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        //取出bean
        HelloService helloService = (HelloService) applicationContext.getBean("helloService");
        helloService.helloWorld();
    }
}
