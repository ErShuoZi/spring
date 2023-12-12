package com.java.spring.aop.aspectj;

import com.java.spring.aop.aspectj.SmartAnimalable;
import org.springframework.stereotype.Component;

/**
 * @author liushuo
 * @version 1.0
 */
@Component //使用@Component注解,当Spring容器启动时,将SmartDog注入到容器中
public class SmartDog implements SmartAnimalable {
    @Override
    public float getSum(float i, float j) {
        float result = i + j;
//        int b = 10 / 0;
        System.out.println("方法内部打印result=" + result);

        return result;
    }

    @Override
    public float getSub(float i, float j) {
        float result = i - j;
        System.out.println("方法内部打印result=" + result);
        return result;
    }
}
