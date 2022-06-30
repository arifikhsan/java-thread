package org.example.collection;

import java.util.Comparator;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

@SuppressWarnings("ALL")
public class PriorityBlockingQueueApp {
    public static void main(String[] args) {
        var queue = new PriorityBlockingQueue<Integer>(5, Comparator.reverseOrder());
        var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index = i;
            executor.execute(() -> {
                System.out.println("Add " + index);
                queue.put(index);
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
