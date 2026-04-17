package com.thinkdifferent.aipicturebackend;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SerializableTest {
    public static void main(String[] args) throws Exception {
        User user = new User("Tom", 18,"男");
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("user.dat"));
        out.writeObject(user);
        out.close();
        System.out.println("对象已序列化");
    }
}
