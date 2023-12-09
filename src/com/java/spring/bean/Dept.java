package com.java.spring.bean;

/**
 * @author liushuo
 * @version 1.0
 */
public class Dept {
    private String name;

    public Dept(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dept() {
    }

    @Override
    public String toString() {
        return "Dept{" +
                "name='" + name + '\'' +
                '}';
    }
}
