package com.thinkdifferent.aipicturebackend;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int age;

    private String sex;

    public User(String name, int age,String sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return name + " - " + age+ " - " +sex;
    }
}
