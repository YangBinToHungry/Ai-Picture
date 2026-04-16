package com.thinkdifferent.aipicturebackend;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Document {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private String content = "Hello";

    public String read() {
        rwLock.readLock().lock();
        try { return content; }
        finally { rwLock.readLock().unlock(); }
    }

    public void write(String newContent) {
        rwLock.writeLock().lock();
        try { content = newContent; }
        finally { rwLock.writeLock().unlock(); }
    }
}