package com.java.spring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author liushuo
 * @version 1.0
 *  表示@Target可以作用在哪些类型上
 *      1.ElementType.TYPE:指定LsComponentScan注解可以修饰Type程序元素之上
 *      (Class, interface (including annotation type), or enum declaration)
 *      2.@Retention():用于指定该Annotation可以保留多长时间,三种值:
 *              1>.RetentionPolicy.SOURCE:编译器使用后,直接丢弃这种策略的注解
 *              2>.RetentionPolicy.CLASS:编译器将把注解记录在class文件中,当运行java程序时候,JVM不会保留注解,这是默认值
 *              3>.RetentionPolicy.RUNTIME:编译器将把注解记录在class文件中,当运行java程序时,JVM会保留注解,程序可以通过反射获取该注解
 *
 *      3. String value() default "";表示LsComponentScan注解可以传入value属性,"value"可以省略
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface LsComponentScan {
    String value() default "";
}
