package org.example.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterLock {
    private Long value = 0L;

    final private Lock lock = new ReentrantLock();

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public void increment() {
        try {
            lock.lock();
            value++;
        } finally {
            lock.unlock();
        }
    }
}
