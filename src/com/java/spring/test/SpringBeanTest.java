package com.java.spring.test;

import com.java.spring.bean.*;
import com.java.spring.component.MyComponent;
import com.java.spring.component.UserController;
import com.java.spring.component.UserDao;
import com.java.spring.component.UserService;
import com.java.spring.component.t.Pig;
import com.java.spring.service.MemberServiceImpl;
import com.java.spring.web.OrderServlet;
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
        Monster monster01 = (Monster) ioc.getBean("monster01");
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
        Monster bean = ioc.getBean("monster02", Monster.class);
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
        System.out.println("emp'department=" + emp.getDept().getName());
    }

    //通过静态工厂获取bean
    @Test
    public void setBeanByStaticFactory() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Monster myMonster02 = ioc.getBean("my_monster01", Monster.class);
        System.out.println(myMonster02);
    }


    //通过实例工厂获取bean
    @Test
    public void setBeanByInstanceFactory() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Monster myMonster02 = ioc.getBean("my_monster02", Monster.class);
        System.out.println(myMonster02);
    }


    //通过FactoryBean获取bean
    @Test
    public void setBeanByFactoryBean() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Monster myMonster03 = ioc.getBean("my_monster03", Monster.class);
        System.out.println(myMonster03);
    }

    //继承bean
    @Test
    public void setBeanByExtends() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Monster myMonster11 = ioc.getBean("monster11", Monster.class);
        System.out.println(myMonster11);
    }


    @Test
    public void setBeanByLife() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        House house = ioc.getBean("house", House.class);
        System.out.println(house);
        ioc.close();
        /**
         * House的无参构造器
         * House SetName()
         * House init()
         * House destory()
         */
    }


    //演示后置处理器
    @Test
    public void setBeanByPostProcessor() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans02.xml");
        House house = ioc.getBean("house", House.class);
//        House house02 = ioc.getBean("house02", House.class);
//        System.out.println(house);
//        System.out.println(house02);
        ioc.close();

        /**
         * House的无参构造器
         * House SetName()
         * postProcessBeforeInitialization() bean=com.java.spring.bean.House@44ebcd03beanName=house
         * House init()
         * postProcessAfterInitialization() bean=com.java.spring.bean.House@44ebcd03beanName=house
         * com.java.spring.bean.House@44ebcd03
         * House destory()
         */
    }


    //使用属性文件配置bean
    @Test
    public void setBeanByPropertyFile() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans.xml");
        Monster monster1000 = ioc.getBean("monster1000", Monster.class);
        System.out.println(monster1000);
    }

    //测试自动装配
    @Test
    public void setBeanByAutowire() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans02.xml");
        OrderServlet orderServlet = ioc.getBean("orderServlet", OrderServlet.class);
        //验证是否自动装配上orderService
        System.out.println(orderServlet.getOrderService()); //com.java.spring.service.OrderService@7ec7ffd3
        //验证是否自动装配上orderDao
        System.out.println(orderServlet.getOrderService().getOrderDao()); //com.java.spring.dao.OrderDao@1fa268de
    }


    //测试SpElBean
    @Test
    public void setBeanByEl() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans03.xml");
        SpElBean elBean = ioc.getBean("elBean", SpElBean.class);
        System.out.println(elBean.getBookName());
    }

    //通过注解来配置bean
    @Test
    public void setBeanByAnnotation() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans04.xml");
       
        UserDao UserDao = ioc.getBean(UserDao.class);
        UserService UserService = ioc.getBean(UserService.class);
        UserController UserController = ioc.getBean(UserController.class);
        MyComponent MyComponent = ioc.getBean(MyComponent.class);
        System.out.println("UserDao=" + UserDao + "UserService=" + UserService + "UserController=" + UserController + "MyComponent=" + MyComponent );
        Pig Pig = ioc.getBean(Pig.class);
        System.out.println("Pig=" + Pig);  //Pig=com.java.spring.component.t.Pig@41c2284a 证明在扫描包的时候,子包也会被扫描

    }



    @Test
    public void setBeanByExclude() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans04.xml");
        //在默认情况下,注解标识的类创建对应对象后在容器中,id为类名的首字母小写
        //也可以使用注解的value属性指定id值.并且value可以省略
        UserDao UserDao = ioc.getBean("userDao", UserDao.class);
        System.out.println("UserDao1=" + UserDao);
    }

    @Test
    public void setBeanByCustomIdByAnnotation() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans04.xml");
        //在默认情况下,注解标识的类创建对应对象后在容器中,id为类名的首字母小写
        //也可以使用注解的value属性指定id值.并且value可以省略
        UserDao UserDao = ioc.getBean("customUserDao", UserDao.class);
        System.out.println("UserDao1=" + UserDao);
    }



    @Test
    public void setPropertyByAutowired() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans05.xml");
        UserController  userController =(UserController) ioc.getBean("userController");
        UserService userService = (UserService)ioc.getBean("userService200", UserService.class);
        userController.sayOk();
        System.out.println("容器中的ioc UserService = " + userService);

    }
}
