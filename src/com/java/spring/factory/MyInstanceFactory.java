package com.java.spring.factory;

import com.java.spring.bean.Monster;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liushuo
 * @version 1.0
 */
public class MyInstanceFactory {
    private final Map<String, Monster> monsterMap;

    //使用普通代码块进行初始化
      {
        monsterMap = new HashMap<>();
        monsterMap.put("monster03",new Monster(300,"牛魔王~","芭蕉扇"));
        monsterMap.put("monster04",new Monster(400,"狐狸精~","美人计"));
    }

    //提供一个方法返回monster对象

    public  Monster getMonster(String key) {
        return monsterMap.get(key);
    }
}
