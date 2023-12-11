package com.java.spring.annotation;

import com.java.spring.component.UserDao;
import com.java.spring.lsApplicationContext.LsApplicationContext;

/**
 * @author liushuo
 * @version 1.0
 */
public class LsSpringApplicationContextTest {
    public static void main(String[] args) throws Exception {
        LsSpringApplicationContext lsSpringApplicationContext = new LsSpringApplicationContext(LsSpringConfig.class);
        UserDao userDao = (UserDao) lsSpringApplicationContext.getBean("customUserDao");
        System.out.println(userDao);
    }
}
