package com.jeq.myapplication.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * The type My data.
 */
public class MyData implements Serializable{
    private int id;
    private String name;
    private int age;
    private String describe;
    private String appraise;


//private List<MyData> data;

    /**
     * Instantiates a new My data.
     */
    public MyData() {
    }

    /*public List<MyData> getData(){
        return data;
    }*/

    /**
     * Instantiates a new My data.
     *
     * @param name     the name
     * @param age      the age
     * @param appraise the appraise
     * @param describe the describe
     */
    public MyData(String name, int age, String appraise, String describe) {

        this.name = name;
        this.age = age;
        this.appraise = appraise;
        this.describe = describe;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }
    /**
     * Gets describe.
     *
     * @return the describe
     */
    public String getDescribe() {
        return describe;
    }

    /**
     * Sets describe.
     *
     * @param describe the describe
     */
    public void setDescribe(String describe) {
        this.describe = describe;
    }

    /**
     * Gets appraise.
     *
     * @return the appraise
     */
    public String getAppraise() {
        return appraise;
    }

    /**
     * Sets appraise.
     *
     * @param appraise the appraise
     */
    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }
    @Override
    public String toString() {
        return "MyData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", describe='" + describe + '\'' +
                ", appraise='" + appraise + '\'' +
                '}';
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(int age) {
        this.age = age;
    }


}
