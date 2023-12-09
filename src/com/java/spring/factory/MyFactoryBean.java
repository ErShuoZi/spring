package com.java.spring.factory;

import com.java.spring.bean.Monster;
import org.springframework.beans.factory.FactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liushuo
 * @version 1.0
 */
public class MyFactoryBean implements FactoryBean<Monster> {
    //这个key就是在配置Monster对象时指定要获取的那一个对象对应的key
    private String keyVal;
    private Map<String,Monster> monster_map;
    {
        monster_map = new HashMap<>();
        monster_map.put("monster_01",new Monster(500,"黄袍怪","一阳指"));
        monster_map.put("monster_02",new Monster(600,"九头金雕","如来神掌"));
    }

    @Override
    public String toString() {
        return "MyFactoryBean{" +
                "keyVal='" + keyVal + '\'' +
                ", monster_map=" + monster_map +
                '}';
    }

    public String getKeyVal() {
        return keyVal;
    }

    public void setKeyVal(String keyVal) {
        this.keyVal = keyVal;
    }

    public Map<String, Monster> getMonster_map() {
        return monster_map;
    }

    public void setMonster_map(Map<String, Monster> monster_map) {
        this.monster_map = monster_map;
    }

    @Override
    //返回需要的FactoryBean中对象
    public Monster getObject() throws Exception {
        return monster_map.get(keyVal);
    }

    @Override
    //返回类型
    public Class<?> getObjectType() {
        return Monster.class;
    }

    @Override
//  是否单例的
    public boolean isSingleton() {
        return true;
    }
}
