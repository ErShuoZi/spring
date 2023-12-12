package com.java.spring.aop.proxy;

import org.junit.jupiter.api.Test;

/**
 * @author liushuo
 * @version 1.0
 */
public class AopTest {
    @Test
    public void smartDogTest() {
        //SmartAnimalable 接口类型接收
        SmartAnimalable smartDog = new SmartDog();
        smartDog.getSum(10.78f,89.7f);
        System.out.println("-------------------");
        smartDog.getSub(10.78f,89.7f);
    }


    @Test
    public void smartDogTestByProxy() {
        //SmartAnimalable 接口类型接收
        SmartAnimalable smartDog = new SmartDog();
        MyProxyProvider myProxyProvider = new MyProxyProvider(smartDog);
        SmartAnimalable proxy = myProxyProvider.getProxy();
        proxy.getSub(10,20);
        System.out.println("--------------");
        proxy.getSum(10,20);

    }
}
