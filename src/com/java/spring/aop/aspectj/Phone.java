package com.java.spring.aop.aspectj;

import org.springframework.stereotype.Component;

/**
 * @author liushuo
 * @version 1.0
 */
@Component
public class Phone implements UsbInterface{
    @Override
    public void work() {
        System.out.println("手机工作了");
    }
}
