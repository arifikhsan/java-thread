package org.example.collection;

import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

@SuppressWarnings("ALL")
public class SynchronousQueueApp {
    public static void main(String[] args) {
        var queue = new SynchronousQueue<Integer>();
        var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index = i;
            executor.execute(() -> {
                System.out.println("Add " + index);
                try {
                    queue.put(index);
                    System.out.println("Finish Put " + index);
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
