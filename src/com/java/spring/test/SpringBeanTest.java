package com.java.spring.test;

import com.java.spring.bean.*;
import com.java.spring.service.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liushuo
 * @version 1.0
 */
public class SpringBeanTest {
    @Test
   public void getMonster() {
        //1.创建容器 ApplicationContext
        //2.该容器和容器配置文件关联
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        //3.通过getBean获取对应对象
        //4.默认返回Object 但是它的运行类型是Monster
//        Object monster01 = ioc.getBean("monster01");
        Monster monster01 =  (Monster) ioc.getBean("monster01");
        System.out.println(monster01 + "运行内存=" + monster01.getClass());
        System.out.println("获取对应属性:" + monster01.getMonsterId() + monster01.getName() + monster01.getSkill());

        //5.也可以在获取的时候指定类型
        Monster monster011 = ioc.getBean("monster01", Monster.class);
        System.out.println(monster011);

        //查看容器注入了哪些bean对象,会输出bean的id
        String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames) + "------");
    }

    //验证类加载路径
    @Test
    public void classPath() {
        File file = new File(this.getClass().getResource("/").getPath());
        //看到类的加载路径
        System.out.println(file);  ///Users/ershuozi/Desktop/java/spring/out/production/spring
    }

    //通过ref来设置bean属性
    @Test
    public void setBeanByRef() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        MemberServiceImpl memberService = ioc.getBean("memberService", MemberServiceImpl.class);
        memberService.add();   //MemberServiceImpl add方法被调用   memberDAoimpl add() 方法

    }

    @Test
    public void setBeanByInner() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        MemberServiceImpl memberService = ioc.getBean("memberService2", MemberServiceImpl.class);
        memberService.add();   //MemberServiceImpl add方法被调用   memberDAoimpl add() 方法

    }

    @Test
    public void setBeanByConstructor() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Monster bean = ioc.getBean("monster02",Monster.class);
        System.out.println("bean+" + bean);  //bean+Monster{monsterId=200, name='白骨精', skill='吸血'}
    }


    //通过p名称空间设置属性
//    @Test
//    public void setBeanByPname() {
//        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
//        Monster bean = ioc.getBean("monster04",Monster.class);
//        System.out.println("bean+" + bean);  //bean+Monster{monsterId=200, name='白骨精', skill='吸血'}
//    }

    //给集合或者数组属性进行配置赋值
    //1.List
    @Test
    public void setBeanByCollection() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Master master = ioc.getBean("master01", Master.class);
        System.out.println("masterBean=" + master);
    }


    //使用util:list名称空间给属性赋值
    @Test
    public void setBeanByUtilList() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        BookStore bookStore = ioc.getBean("bookStore", BookStore.class);
        System.out.println("bookStoreBean=" + bookStore);
    }


    //给属性进行级联赋值
    @Test
    public void setBeanByRelation() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Dept dept = ioc.getBean("dept", Dept.class);
        System.out.println("deptBean=" + dept);
        Emp emp = ioc.getBean("emp", Emp.class);
        System.out.println("empBean=" + emp);
        System.out.println("emp'department=" +emp.getDept().getName());
    }







}
