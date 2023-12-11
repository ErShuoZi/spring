package com.java.spring.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @author liushuo
 * @version 1.0
 * 使用@Controller表示该类是一个Controller类/对象
 */
@Controller
public class UserController {
    //Autowired规则
    /**
     * 1.在IOC容器中查找待装配的组件类型,如果有唯一的bean的匹配,则使用该bean装配
     * 2.如果待装配的类型对应的bean在IOC容器中存在多个,则使用待装配的属性的属性名作为id值再进行查找,找到就装配,否则抛异常
     */
    //   @Autowired

    //@Resource有两个属性是比较重要,分别是name和type,Spring将@Resource注解的name属性解析为bean的名字,而type属性则解析为bean的类型
    // 如果使用name属性,则使用byName的自动注入策略,而使用type属性时则使用byType自动注入策略
    // 如果@Resource没有指定name和type,则先使用byName注入策略,如果匹配不上,再使用byType策略,如果都不成功,就会报错

    @Resource(name = "userService")
    private UserService userService200;
    public void sayOk() {

        System.out.println("UserController中装配的userService属性=" + userService200);
        userService200.hi();
    }
    public UserController() {
    }

}
