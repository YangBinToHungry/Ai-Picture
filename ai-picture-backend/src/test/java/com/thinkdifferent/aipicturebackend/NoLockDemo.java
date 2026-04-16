package com.thinkdifferent.aipicturebackend;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NoLockDemo {
    static int balance = 100;

    public  static void withdraw(int amount) {
        if (balance >= amount) {
            // 模拟取钱过程中的耗时操作
            try { Thread.sleep(10); } catch (InterruptedException e) {}
            balance = balance - amount;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,                               // corePoolSize
                10,                               // maximumPoolSize
                60L,                             // keepAliveTime
                TimeUnit.SECONDS,                // 时间单位
                new ArrayBlockingQueue<>(10),    // 有界队列，避免内存爆炸
                Executors.defaultThreadFactory(), // 线程工厂
                new ThreadPoolExecutor.CallerRunsPolicy() // 拒绝策略
        );
        for (int i = 1; i <= 10; i++) {
            int taskid=i;
            executor.execute(() -> {
                withdraw(10);
                System.out.println(Thread.currentThread().getName() + " 进行取钱,余额为: "+balance+"任务id: "+taskid);
            });
        }
        executor.shutdown();
        Thread.sleep(1000);
        System.out.println("最终余额：" + balance);
    }
}