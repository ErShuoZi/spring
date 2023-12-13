package com.java.spring.aop.aspectj;
import com.java.spring.aop.aspectj.SmartAnimalable;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author liushuo
 * @version 1.0
 */
public class AopAspectjTest {
    @Test
    public void smartDogTestByProxy() throws InstantiationException, IllegalAccessException {

        ApplicationContext ioc = new ClassPathXmlApplicationContext("beans06.xml");
        //这里必须通过接口类型来获取注入的SmartDog对象-就是代理对象 因为底层返回的是动态代理的对象,动态代理的对象不能用具体类型接收
        //具体查看aspectj/proxy2/MyProxyProvider
        //方式一:获取对象通过接口方式(推荐)
//        SmartAnimalable smartAnimalable = ioc.getBean(SmartAnimalable.class);

        //方式二:开启反射后,ioc.getBean返回的是代理对象,具体对象类型无法接收代理对象,但是接口可以,所以需要转为接口类型
        SmartAnimalable smartAnimalable = (SmartAnimalable)ioc.getBean("smartDog");


//        SmartDog bean = ioc.getBean(SmartDog.class); //error
//        class com.java.spring.aop.aspectj.SmartAnimalAspect
        float sum = smartAnimalable.getSum(10, 2);
        System.out.println("----------------");
        float sub = smartAnimalable.getSub(10, 2);
        //是代理类型 class com.sun.proxy.$Proxy16
        System.out.println("SmartAnimalable的运行类型是:" + smartAnimalable.getClass());


    }


    @Test
    public void HomeWorkTest() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans06.xml");
        //因为Phone和Camera都实现了同一个接口,在按照接口获取时,会出现两个,报错,解决方案,按名字获取
//        UsbInterface bean = ioc.getBean(UsbInterface.class);
        //因为开启了AOP,getBean返回的是代理对象,需要通过接口接收,这里转为接口类型
        UsbInterface phone = (UsbInterface)ioc.getBean("phone");
        phone.work();
        System.out.println("--------");
        UsbInterface camera = (UsbInterface)ioc.getBean("camera");
        camera.work();


    }

    @Test
    public void Test2() {
        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans06.xml");
        Car car = ioc.getBean(Car.class);
        car.run();
        //这个car对象仍然是个代理对象
        System.out.println("car的运行类型=" + car.getClass());
        //切面类的ok1-执行的目标方法-run
        //小汽车在running
        //Spring aspectj-方法最终执行-日志-方法名-run-参数 []
    }
}
