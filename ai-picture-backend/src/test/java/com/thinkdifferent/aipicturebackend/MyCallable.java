package com.thinkdifferent.aipicturebackend;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class MyCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        return Thread.currentThread().getName() +"任务结果";
    }
    public static void main(String[] args) throws Exception {
        FutureTask<String> task1 = new FutureTask<>(new MyCallable());
        FutureTask<String> task2 = new FutureTask<>(new MyCallable());
        new Thread(task1).start();
        new Thread(task2).start();
        System.out.println(task1.get()); // 阻塞获取结果
        System.out.println(task2.get()); // 阻塞获取结果
    }
}