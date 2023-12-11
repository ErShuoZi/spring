package com.java.spring.component;

import org.springframework.stereotype.Service;

/**
 * @author liushuo
 * @version 1.0
 * 使用@Service表示该类是一个Service类/对象
 */
@Service
public class UserService {
    public UserService() {
    }

    public void hi() {
        System.out.println("UserService say Hi~");
    }
}
