package com.rory.springcore.study;

import org.junit.Assert;

public class OutputService {
    private HelloService helloService;

    public void output(String text){
        Assert.assertNotNull(helloService);
        System.out.println(text);
    }

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }
}
