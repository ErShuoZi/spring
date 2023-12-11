package com.java.spring.proxy_;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author liushuo
 * @version 1.0
 * VehicleProxyProvider该类可以提供方法,返回代理对象
 */
public class VehicleProxyProvider {
    //定义一个属性
    //target_vehicle表示真正要执行的对象,要求实现Vehicle接口
    private Vehicle target_vehicle;


    //构造器
    public VehicleProxyProvider(Vehicle target_vehicle) {
        this.target_vehicle = target_vehicle;
    }


    //编写一个方法,可以返回代理对象
    /**
     *
     * @return
     */
    public Vehicle getProxy(){

        /**
         *  public static Object newProxyInstance(ClassLoader loader,
         *                                           Class<?>[] interfaces,
         *                                           InvocationHandler h)
         */


        /**
         * Proxy.newProxyInstance可以翻译一个代理对象
         * 参数:
         *  1.ClassLoader loader:表示类的加载器
         *  2. Class<?>[] interfaces:将来要代理的对象的接口信息
         *  3.  InvocationHandler :调用处理器/对象,有一个重要方法invoke
         */

        //1.得到类加载器
        ClassLoader classLoader = target_vehicle.getClass().getClassLoader();
        //2.得到要代理的对象的接口信息,底层是通过接口完成调用
        Class<?>[] interfaces = target_vehicle.getClass().getInterfaces();
        //3.创建InvocationHandler 调用处理器/对象,因为InvocationHandler是接口,所以可以通过匿名对象的方式来创建该对象
        /**
         *         public interface InvocationHandler {
         *             public Object invoke(Object proxy, Method method, Object[] args)
         *                     throws Throwable;
         *         }
         *
         *
         */
        //匿名对象
        InvocationHandler invocationHandler = new InvocationHandler() {
            /**
             *  invoke 方法是执行target_vehicle的方法时会调用到
             * @param proxy 表示代理对象
             * @param method 表示通过代理对象调用方法时的方法
             * @param args  表示调用method的时的参数
             * @return 表示代理对象.xxx()方法执行后的结果
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
              System.out.println("交通工具开始运行了");
                Object result = method.invoke(target_vehicle, args);
                System.out.println("交通工具结束运行了");
                return result;
            }
        };
        Vehicle proxy = (Vehicle)Proxy.newProxyInstance(classLoader, interfaces,invocationHandler);
        return proxy;
    }

}
