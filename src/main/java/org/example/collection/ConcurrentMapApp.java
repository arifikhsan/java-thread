package org.example.collection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class ConcurrentMapApp {
    public static void main(String[] args) {
        var countDown = new CountDownLatch(100);
        var map = new ConcurrentHashMap<Integer, String>();
        var executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            final var index = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    map.putIfAbsent(index, "Value " + index);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    countDown.countDown();
                }
            });
        }

        try {
            countDown.await();
            map.forEach((key, value) -> System.out.println(key + ": " + value));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        executor.shutdown();
    }
}
