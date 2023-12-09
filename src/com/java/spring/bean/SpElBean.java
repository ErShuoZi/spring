package com.java.spring.bean;

/**
 * @author liushuo
 * @version 1.0
 */
public class SpElBean {
    private String name;
    private Monster monster;
    private String monsterName;
    private String crySound;
    private String bookName;
    private Double result;

    public SpElBean() {
    }

    public String getName() {
        return name;
    }

    public Monster getMonster() {
        return monster;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public String getCrySound() {
        return crySound;
    }

    public String getBookName() {
        return bookName;
    }

    public Double getResult() {
        return result;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    public void setCrySound(String crySound) {
        this.crySound = crySound;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setResult(Double result) {
        this.result = result;
    }
    public String cry(String sound) {
        return "发出" + sound + "叫声";
    }
    public static String read(String bookName) {
        return "正在看" + bookName;
    }
}
