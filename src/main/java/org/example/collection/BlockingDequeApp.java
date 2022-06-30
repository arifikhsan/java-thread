package org.example.collection;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

@SuppressWarnings("ALL")
public class BlockingDequeApp {
    public static void main(String[] args) {
        var queue = new LinkedBlockingDeque<Integer>();
        var executor = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            final var index = i;
            try {
                queue.putLast(index);
                System.out.println("Finish Put " + index);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        executor.execute(() -> {
            try {
                while (true) {
                    Thread.sleep(2000);
                    System.out.println("Received Data: " + queue.takeFirst());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();
    }
}
