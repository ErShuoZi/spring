package com.java.spring.aop.aspectj;

import org.springframework.stereotype.Component;

/**
 * @author liushuo
 * @version 1.0
 */
@Component
public class Camera implements UsbInterface{
    @Override
    public void work() {
        System.out.println("相机工作了");
    }
}
