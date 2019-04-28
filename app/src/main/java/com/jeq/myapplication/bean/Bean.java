package com.jeq.myapplication.bean;

public class Bean {
    /***
     * id
     * age
     * name
     * describe:描述
     * appraise：评价
     */

    private int id;
    private int age;
    private String name;
    private String describe;
    private String appraise;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", describe='" + describe + '\'' +
                ", appraise='" + appraise + '\'' +
                '}';
    }
}
