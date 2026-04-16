package com.thinkdifferent.aipicturebackend;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {
    public static void main(String[] args) {
        // 自定义线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2, 4, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(50)
        );
        CompletableFuture.supplyAsync(() -> "Hello", executor)
                         .thenApply(s -> s + " World")
                         .thenAccept(System.out::println);
        executor.shutdown();
    }
}