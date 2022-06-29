package org.example.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CounterReadWriteLock {
    private Long value = 0L;

    final private ReadWriteLock lock = new ReentrantReadWriteLock();

    public Long getValue() {
        try {
            lock.readLock().lock();
            return value;
        } finally {
            lock.readLock().unlock();
        }
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public void increment() {
        try {
            lock.writeLock().lock();
            value++;
        } finally {
            lock.writeLock().lock();
        }
    }
}
