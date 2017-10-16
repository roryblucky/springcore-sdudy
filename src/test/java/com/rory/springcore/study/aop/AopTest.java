package com.rory.springcore.study.aop;

import com.rory.springcore.study.HelloService;
import com.rory.springcore.study.context.ApplicationContext;
import com.rory.springcore.study.context.ClassPathXmlApplicationContext;
import org.junit.Test;

public class AopTest {

    @Test
    public void testAop() throws Exception {
        //普通容器，没有aop
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        HelloService helloService = (HelloService) context.getBean("helloService");
        helloService.helloWorld();

        //with aop
        //1. 创建元数据对象
        AdvisedSupport advisedSupport = new AdvisedSupport();
        //1. 设置被代理对象(JointPoint)
        TargetSource targetSource = new TargetSource(HelloService.class, helloService);
        advisedSupport.setTargetSource(targetSource);

        //2. 设置拦截器(Advice)
        advisedSupport.setInterceptor(new TimerInterceptor());

        //3. 创建代理对象
        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(advisedSupport);
        HelloService helloServiceProxy = (HelloService) jdkDynamicAopProxy.getProxy();

        helloServiceProxy.helloWorld();
    }
}
