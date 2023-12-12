package com.java.spring.aop.proxy2;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author liushuo
 * @version 1.0
 * 自己编写一个极简版AOP类
 */
public class LsAOP {
    //在目标对象执行前被执行
    public static void before(Object proxy, Method method, Object[] args) {
        //横切关注点-前置通知
        System.out.println("LsAOP-方法正常执行前-日志-方法名-" +method.getName() +"-参数 "+ Arrays.asList(args));
    }

    //在目标对象执行后被执行
    public static void after( Method method,Object result){
        //横切关注点-后置通知
        System.out.println("LsAOP-方法正常执行后-日志-方法名-"+ method.getName() + "-结果result" + result);
    }
}
