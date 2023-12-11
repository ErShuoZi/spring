package com.java.spring.annotation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liushuo
 * @version 1.0
 * 类似于容器,作用是类似于Spring的原生ioc容器
 */
public class LsSpringApplicationContext {
    private Class configClass;
    //singletonObject存放的是通过反射创建的对象
    private final ConcurrentHashMap<String,Object> singletonObject = new ConcurrentHashMap<>();

    private List<String> listArr = new ArrayList<>();
    //构造器
    public LsSpringApplicationContext(Class configClass) throws IOException {
        this.configClass = configClass;
        //System.out.println("configClass=" + configClass);
        //得到配置类的class类型了
        //获取要扫描的包
        //1.再拿到注解
        LsComponentScan declaredAnnotation = (LsComponentScan) this.configClass.getDeclaredAnnotation(LsComponentScan.class);

        //2.得到注解就可以得到value值
        String path = declaredAnnotation.value();
        //System.out.println(value);  //com.java.spring.component
        //3.进行扫描包,获取资源(.class文件)
        /**
         * 1).先得到类的加载器
         * 2).通过类的加载器获取到要扫描包的资源url
         * 3).
         */
        //1.得到类的加载器
        ClassLoader classLoader = LsSpringApplicationContext.class.getClassLoader();
        path = path.replace(".","/"); // 将com.java.spring.component改为com/java/spring/component
        //2.通过类的加载器获取到要扫描包的资源url
        URL resource = classLoader.getResource(path);
        //System.out.println(resource);//file:/Users/ershuozi/Desktop/java/spring/out/production/spring/com/java/spring/component
        //3.将要加载的资源(.class)路径下的文件进行遍历
        File file = new File(resource.getFile());

        recursionGetFile(file, path);
        //3.判断该class文件是否需要注入到容器中(过滤),检查该类是否有@Component @Service...
            for (String classFullName : listArr) {
                try {
                    //这时得到了该类的class对象
                    //Class.for(className) 可以反射加载类
                    // classLoader.loadClass(list) 也可以反射类class
                    //区别:Class.for会执行该类的静态方法,classLoader不会
                    Class<?> aClass = classLoader.loadClass(classFullName);


                    //isAnnotationPresent()判断该类是否有下列注解
                    if(aClass.isAnnotationPresent(Component.class) || aClass.isAnnotationPresent(Service.class) || aClass.isAnnotationPresent(Repository.class) || aClass.isAnnotationPresent(Controller.class)) {
                        //Component注解指定value,分配id
                        String customValue ="";
                        if(aClass.isAnnotationPresent(Component.class)) {
                            //获取到该注解
                            Component componentAnnotation = aClass.getDeclaredAnnotation(Component.class);
                            customValue = componentAnnotation.value();
                        }
                        else if(aClass.isAnnotationPresent(Service.class)) {
                            //获取到该注解
                            Service serviceAnnotation = aClass.getDeclaredAnnotation(Service.class);
                            customValue = serviceAnnotation.value();
                        }
                        else if(aClass.isAnnotationPresent(Repository.class)) {
                            //获取到该注解
                            Repository repositoryAnnotation = aClass.getDeclaredAnnotation(Repository.class);
                            customValue = repositoryAnnotation.value();
                        }
                        else if(aClass.isAnnotationPresent(Controller.class)) {
                            //获取到该注解
                            Controller controllerAnnotation = aClass.getDeclaredAnnotation(Controller.class);
                            customValue = controllerAnnotation.value();
                        }




                        //此时可以反射对象.并放入到容器中
                        Class<?> clazz = Class.forName(classFullName);
                        Object instance = clazz.newInstance();
                        String clazzName = " ";
                        if(!"".endsWith(customValue)) {
                            clazzName =customValue;
                        }
                            else {
                            clazzName = classFullName.substring(classFullName.lastIndexOf(".") + 1);
                        }
                        //放入到容器
                        //将类名的小写首字母作为id
                        //StringUtils
                        singletonObject.put(StringUtils.uncapitalize(clazzName),instance);


                    }

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
    }

    private void recursionGetFile(File file, String path) throws IOException {
        if (file.isDirectory()) {
            //如果是目录,就把当前文件目录下的所有文件列出来
            File[] files = file.listFiles();
          if(files!=null) {
              for (File f : files) {
                  recursionGetFile(f,path);
              }
          }
        }
        else {
            //不是文件夹
            ///Users/ershuozi/Desktop/java/spring/out/production/spring/com/java/spring/component/UserDao.class
            //要获取到com/java/spring/component/UserDao.class 而且是以.的形式进行间隔
            String FileAbsolutePath = file.getAbsolutePath();
//            System.out.println(FileAbsolutePath);

            //进行文件过滤,筛除非class文件
            if (FileAbsolutePath.endsWith(".class")) {
                //1.获取到类名  substring 左闭右开
                String className = FileAbsolutePath.substring(FileAbsolutePath.indexOf(path), FileAbsolutePath.indexOf(".class"));
                //2.获取类的完整的路径 com.java.spring.component.
                String classFullName = className.replace(File.separator, ".");

                listArr.add(classFullName);
            }
        }

    }

    //返回singletonObject容器中的对象
    public Object getBean(String name) {
        return singletonObject.get(name);
    }

}
