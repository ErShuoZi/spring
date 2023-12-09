package com.java.spring.bean;

/**
 * @author liushuo
 * @version 1.0
 */
public class Emp {
    private String name;
    private Dept dept;

    public Emp(String name, Dept dept) {
        this.name = name;
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "name='" + name + '\'' +
                ", dept=" + dept +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public Emp() {
    }
}
