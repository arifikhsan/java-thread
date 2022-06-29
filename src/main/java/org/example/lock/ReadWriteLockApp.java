package org.example.lock;

import org.example.model.CounterReadWriteLock;

@SuppressWarnings("ALL")
public class ReadWriteLockApp {
    public static void main(String[] args) throws InterruptedException {
        var counter = new CounterReadWriteLock();
        Runnable runnable = () -> {
            for (int i = 0; i < 1_000; i++) {
                counter.increment();
            }
        };

        var thread1 = new Thread(runnable);
        var thread2 = new Thread(runnable);
        var thread3 = new Thread(runnable);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(counter.getValue());
    }
}
