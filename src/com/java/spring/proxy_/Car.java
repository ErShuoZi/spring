package com.java.spring.proxy_;

/**
 * @author liushuo
 * @version 1.0
 */
public class Car implements Vehicle{
    @Override
    public void run() {
//        System.out.println("交通工具开始运行了");
        System.out.println("小汽车在路上running");
//        System.out.println("交通工具结束运行了");
    }
}
