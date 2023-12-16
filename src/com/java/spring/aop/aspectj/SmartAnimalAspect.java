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
     * 如果切入表达式里的类跟切面类在同一个包下面,可以省略包名,只写简单类名
     * 切入表达式也可以指向接口的方法,这时候切入表达式会对实现了接口的类/对象生效
     */
    @Before(value = "execution(public float SmartDog.*(float, float))")
    public void showBeginLog(JoinPoint joinPoint) {
        //横切关注点-前置通知
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("Spring aspectj-方法正常执行前-日志-方法名-" +signature.getName() +"-参数 "+ Arrays.asList(args));
    }
    //给Car配置前置通知
    @Before(value = "execution(public void Car.run())")
    public void ok1(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        System.out.println("切面类的ok1-执行的目标方法-" + signature.getName());
        //JoinPoint 常用方法
        //
        /**
         *                 joinPoint.getSignature(),getName 获取目标方法名
         *                 joinPoint.getSignature().getDeclaringType().getSimpleName() 获取目标方法所属类的简单类名
         *                 joinPoint.getSignature().getDeclaringType(); 获取目标方法所属类的类名
         *                 joinPoint.getArgs(); 获取传入目标方式的参数.返回数组
         *                 joinPoint.getTarget();获取被代理的对象
         *                 joinPoint.getThis();获取代理对象自己
         *                 joinPoint.getSignature().getModifiers(); 获取目标方法的修饰符
         *
         */

    }



    //返回通知
    //如果希望把目标方法执行的结果返回给切入方法,可以在@AfterReturning注解中加入returning属性,属性对应的值要一致
    @AfterReturning(value = "execution(public float com.java.spring.aop.aspectj.SmartDog.getSum(float, float))",returning = "res")
    public void showSuccessEndLog(JoinPoint joinPoint,Object res) {
        //横切关注点-前置通知
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("Spring aspectj-方法正常执行后-日志-方法名-" +signature.getName() +"-参数 " + Arrays.asList(args)+ "-返回的结果" + res);
    }

    //异常通知 AfterThrowing
    @AfterThrowing(value = "execution(public float com.java.spring.aop.aspectj.SmartDog.getSum(float, float))",throwing = "throwable")
    public void showExceptionLog(JoinPoint joinPoint,Throwable throwable) {
        //横切关注点-前置通知
        Signature signature = joinPoint.getSignature();
        Object[] args = joinPoint.getArgs();
        System.out.println("Spring aspectj-方法异常执行-日志-方法名-" +signature.getName() +"-参数 "+ Arrays.asList(args) + "错误信息=" + throwable);
    }

    //最终通知 After (不管是否发生异常都要执行)
    @After(value = "execution(public float com.java.spring.aop.aspectj.SmartDog.getSub(float , float ))")
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
