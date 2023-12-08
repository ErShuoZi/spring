package com.java.spring.lsApplicationContext;

import com.java.spring.bean.Monster;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liushuo
 * @version 1.0
 *
 * 1.用于实现spring的一个简单容器机制
 * 2.这里实现解析beans.xml,生成对象,放入容器
 * 3.提供方法getBean(String id) 返回对应对象
 * 4.
 */
public class LsApplicationContext {

    private ConcurrentHashMap<String,Object> singletonObjects = new ConcurrentHashMap<>();

    //构造器

    /**
     *接收一个容器的配置文件,比如beans.xml,该文件默认在Src文件下
     * @param iocBeanXmlFile
     *
     *
     */
    public LsApplicationContext(String iocBeanXmlFile) throws Exception {
        //1.得到类加载路径
        String path = this.getClass().getResource("/").getPath();


        //2.使用dom4j进行文件读取
        SAXReader saxReader = new SAXReader();

        //3.得到Document对象
        Document document = saxReader.read(new File(path + "beans.xml"));

        //4,得到root Document
        Element rootElement = document.getRootElement();

        //5.获取第一个bean-monster01
        Element bean = (Element)rootElement.elements("bean").get(0);


        //6.获取第一个bean-Monster01的相关属性
        String id = bean.attributeValue("id");
        String classFullPath = bean.attributeValue("class");
        List<Element> properties = bean.elements("property");
        Integer monsterId = null;
        String name  = null;
        String skill  = null;

        for (Element property : properties) {
            String attributeName = property.attributeValue("name");
            String attributeValue = property.attributeValue("value");
            if(attributeName.equals("monsterId")) {
                monsterId = Integer.parseInt(attributeValue);
            }
            if(attributeName.equals("name")) {
                 name = attributeValue;

            }
            if(attributeName.equals("skill")) {
                skill  = attributeValue;
            }

        }
//        Integer monsterId = Integer.parseInt(properties.get(0).attributeValue("value"));
//        String  name = properties.get(1).attributeValue("value");
//        String skill =properties.get(2).attributeValue("value");
//
//        System.out.println(monsterId + name +  skill);

        //7.使用反射创建对象
        /**
         * 反射机制
         */
        Class<?> aClass = Class.forName(classFullPath);

        //这里o对象已经是一个monster对象
        Object o = (Monster)aClass.newInstance();
        //给o对象赋值
        //用反射赋值
        Field[] declaredFields = aClass.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            String fieldName = declaredField.getName();
            if (fieldName.equals("monsterId")) {
                declaredField.set(o, monsterId);
            } else if (fieldName.equals("name")) {
                declaredField.set(o, name);
            } else if (fieldName.equals("skill")) {
                declaredField.set(o, skill);
            }
        }

        //8.将创建好的对象放到容器中

        singletonObjects.put(id,o);
    }

    public Object getBean(String id) {
        return singletonObjects.get(id);
    }
}
