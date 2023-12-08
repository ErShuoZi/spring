package com.java.spring.lsApplicationContext;

import com.java.spring.bean.Monster;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author liushuo
 * @version 1.0
 */
public class Test {
    public static void main(String[] args) {
        try {
            LsApplicationContext ioc = new LsApplicationContext("beans.xml");
            Monster bean = (Monster) ioc.getBean("monster01");
            System.out.println(bean);
            System.out.println(bean.getName());
            bean.setName("孙悟空");
            System.out.println(bean.getName());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
