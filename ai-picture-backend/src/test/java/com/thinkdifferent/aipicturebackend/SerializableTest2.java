package com.thinkdifferent.aipicturebackend;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class SerializableTest2 {
    public static void main(String[] args) throws Exception {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("user.dat"));
        User user = (User) in.readObject();
        in.close();
        System.out.println(user);
    }
}
