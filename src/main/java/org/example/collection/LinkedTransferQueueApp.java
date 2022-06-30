package org.example.collection;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;

@SuppressWarnings("ALL")
public class LinkedTransferQueueApp {
    public static void main(String[] args) {
        var queue = new LinkedTransferQueue<String>();
        var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index = i;
            executor.execute(() -> {
                try {
                    System.out.println("Add " + index);
                    queue.transfer(String.valueOf(index));
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
