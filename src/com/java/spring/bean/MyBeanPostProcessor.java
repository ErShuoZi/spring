package com.java.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author liushuo
 * @version 1.0
 * 后置处理器必须实现 BeanPostProcessor接口
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    //该方法会在bean的init方法之前,setter方法之后调用
    /**
     * 参数:
     * 1.Object bean:传入的在IOC容器中创建/配置的Bean
     * 2.String beanName: 传入的在IOC容器中创建/配置的Bean的ID
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization() bean=" + bean + "beanName=" + beanName);
        //如果类型是House的统一改成上海豪宅 (切面编程特点)
        if(bean instanceof House) {
             ((House) bean).setName("上海豪宅");
        }
        return bean;
    }

    @Override
    //该方法会在bean的init方法之后调用
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization() bean=" + bean + "beanName=" + beanName);
        return bean;
    }
}
