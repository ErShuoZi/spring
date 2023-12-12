package com.java.spring.aop.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author liushuo
 * @version 1.0
 * 切面类
 */
@Aspect //该注解标识切面类[底层切面编程的支撑(动态代理 + 反射 + 动态绑定]
@Component //表示Spring会注入该类到容器
public class SmartAnimalAspect {
    //希望将before方法切入到SmartDog中getSum方法前执行 - 前置通知

    /**
     * 1.@Before:表示前置通知,即表示在目标对象执行方法前执行
     * 2.value = "execution(public float com.java.spring.aop.aspectj.SmartDog.getSum(float, float))"
     *      指定切入到哪个类的哪个方法.形式是:访问修饰符 返回类型 全类名.方法名(参数列表
     * 3.showBeginLog方法可以理解成切入方法
     * 4.JoinPoint joinPoint:在底层执行时.Aspectj切面编程框架会给该切入方法传入连接点(JoinPoint)对象
     * @param joinPoint
     */
    @Before(value = "execution(public float com.java.spring.aop.aspectj.SmartDog.*(float, float))")
    public void showBeginLog(JoinPoint joinPoint) {
        //横切关注点-前置通知
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("Spring aspectj-方法正常执行前-日志-方法名-" +signature.getName() +"-参数 "+ Arrays.asList(args));
    }

    //返回通知
    @AfterReturning(value = "execution(public float com.java.spring.aop.aspectj.SmartDog.getSum(float, float))")
    public void showSuccessEndLog(JoinPoint joinPoint) {
        //横切关注点-前置通知
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("Spring aspectj-方法正常执行后-日志-方法名-" +signature.getName() +"-参数 "+ Arrays.asList(args));
    }

    //异常通知 AfterThrowing
    @AfterThrowing(value = "execution(public float com.java.spring.aop.aspectj.SmartDog.getSum(float, float))")
    public void showExceptionLog(JoinPoint joinPoint) {
        //横切关注点-前置通知
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("Spring aspectj-方法异常执行-日志-方法名-" +signature.getName() +"-参数 "+ Arrays.asList(args));
    }

    //最终通知 After (不管是否发生异常都要执行)
    @After(value = "execution(* *.*(..))")
    public void showFinallyEndLog(JoinPoint joinPoint) {
        //横切关注点-前置通知
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("Spring aspectj-方法最终执行-日志-方法名-" +signature.getName() +"-参数 "+ Arrays.asList(args));
    }
    //环绕通知 Around



    @Before(value = "execution( public void com.java.spring.aop.aspectj.Phone.work()) || execution(public void com.java.spring.aop.aspectj.Camera.work())")
    public void before(JoinPoint joinPoint) {
        //横切关注点-前置通知
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("Spring aspectj-方法正常执行前-日志-方法名-" +signature.getName());
    }
}
