package com.java.spring.test;

import com.java.spring.bean.Monster;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

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
    }
}
