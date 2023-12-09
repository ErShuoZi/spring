package com.java.spring.bean;

/**
 * @author liushuo
 * @version 1.0
 */
public class House {
    private String name;

    public House(String name) {
        this.name = name;
    }

    public House() {
        System.out.println("House的无参构造器");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("House SetName()");
        this.name = name;
    }

    public void init() {
        System.out.println("House init()");
    }
    public void destory() {
        System.out.println("House destory()");
    }
}
