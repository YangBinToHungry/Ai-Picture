package com.thinkdifferent.aipicturebackend;

import java.util.concurrent.*;

public class SafeThreadPool {
    public static void main(String[] args) {
        // 核心线程数 = 2，最大线程数 = 5，空闲存活时间 = 60秒
        // 阻塞队列 = 有界队列 ArrayBlockingQueue 容量 10
        // 拒绝策略 = CallerRunsPolicy（让提交任务的线程自己执行）
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2,                               // corePoolSize
            5,                               // maximumPoolSize
            60L,                             // keepAliveTime
            TimeUnit.SECONDS,                // 时间单位
            new ArrayBlockingQueue<>(10),    // 有界队列，避免内存爆炸
            Executors.defaultThreadFactory(), // 线程工厂
            new ThreadPoolExecutor.CallerRunsPolicy() // 拒绝策略
        );

        // 提交任务
        for (int i = 1; i <= 20; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + " 执行任务 " + taskId);
                try { Thread.sleep(100); } catch (InterruptedException e) {}
            });
        }

        executor.shutdown();
    }
}