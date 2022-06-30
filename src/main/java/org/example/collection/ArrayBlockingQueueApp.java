package org.example.collection;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;

public class ArrayBlockingQueueApp {
    public static void main(String[] args) {
        var queue = new ArrayBlockingQueue<String>(5);
        var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index = i;
            executor.execute(() -> {
                try {
                    System.out.println("Add " + index);
                    queue.put(String.valueOf(index));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.execute(() -> {
            try {
                while (true) {
                    Thread.sleep(2000);
                    System.out.println("Received Data: " + queue.take());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();
    }
}
