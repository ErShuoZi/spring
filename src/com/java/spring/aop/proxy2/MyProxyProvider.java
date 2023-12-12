package com.java.spring.aop.proxy2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author liushuo
 * @version 1.0
 * 可以返回动态代理对象,可以执行SmartDog对象的方法
 */
public class MyProxyProvider {
    //定义要代理的对象,该对象需要实现SmartAnimalable接口
    private SmartAnimalable target_Object;


//    //在目标对象执行前被执行
//    public void before(Object proxy, Method method, Object[] args) {
//        //横切关注点-前置通知
//        System.out.println("~方法正常执行前-日志-方法名-" +method.getName() +"-参数 "+ Arrays.asList(args));
//    }
//
//    //在目标对象执行后被执行
//    public void after( Method method,Object result){
//        //横切关注点-后置通知
//        System.out.println("~方法正常执行后-日志-方法名-"+ method.getName() + "-结果result" + result);
//    }
    public MyProxyProvider(SmartAnimalable target_Object) {
        this.target_Object = target_Object;
    }

    public SmartAnimalable getProxy() {
        ClassLoader classLoader = target_Object.getClass().getClassLoader();

        Class<?>[] interfaces = target_Object.getClass().getInterfaces();

        Object proxyInstance = Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                try {
                    LsAOP.before(proxy, method, args);
                     result = method.invoke(target_Object, args);
                    LsAOP.after(method,result);

                } catch (Exception e) {
                    e.printStackTrace();
                    //横切关注点-异常通知
                    System.out.println("方法执行异常-日志-方法名-" + method.getName() + "-异常类型=" + e.getClass().getName());

                }
                finally {
                    //横切关注点-最终通知
                    System.out.println("方法最终结束-日志-方法名-" + method.getName());
                }
                return result;

            }
        });
        return (SmartAnimalable) proxyInstance;
    }
}
