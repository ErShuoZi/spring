package com.java.spring.aop.aspectj;

import org.springframework.stereotype.Component;

/**
 * @author liushuo
 * @version 1.0
 */
@Component
public class Car {
    public void run() {
        System.out.println("小汽车在running");
    }
}
