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
    //private List<MyData> data;

    public MyData() {
    }

    /*public List<MyData> getData(){
        return data;
    }*/

    /**
     * Instantiates a new My data.
     *
     * @param id   the id
     * @param name the name
     * @param age  the age
     */
    public MyData(String name, int age) {

        this.name = name;
        this.age = age;
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

    @Override
    public String toString() {
        return "MyData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
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
