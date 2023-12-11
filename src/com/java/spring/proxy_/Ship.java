package com.java.spring.proxy_;

/**
 * @author liushuo
 * @version 1.0
 */
public class Ship implements Vehicle{
    @Override
    public void run() {
//        System.out.println("交通工具开始运行了");
        System.out.println("轮船在海上running");
//        System.out.println("交通工具结束运行了");
    }
}
