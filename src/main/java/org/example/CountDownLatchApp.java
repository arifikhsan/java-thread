package org.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class CountDownLatchApp {
    public static void main(String[] args) {
        var countDownLatch = new CountDownLatch(5);
        var executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("Start task");
                    Thread.sleep(2000);
                    System.out.println("Finish task");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        executor.execute(() -> {
            try {
                countDownLatch.await();
                System.out.println("All tasks finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();
    }
}
