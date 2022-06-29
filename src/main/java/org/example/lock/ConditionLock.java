package org.example.lock;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionLock {
    public static void main(String[] args) throws InterruptedException {
        AtomicReference<String> message = new AtomicReference<>("");
        var lock = new ReentrantLock();
        var condition = lock.newCondition();

        var thread1 = new Thread(() -> {
            try {
                lock.lock();
                condition.await();
                System.out.println(message.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        var thread2 = new Thread(() -> {
            try {
                lock.lock();
                Thread.sleep(1000);
                message.set("Hello World");
                condition.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
