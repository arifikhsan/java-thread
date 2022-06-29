package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreApp {
    public static void main(String[] args) {
        final var semaphore = new Semaphore(10);
        final var executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 1000; i++) {
            final var a = i;
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    Thread.sleep(1000);
                    System.out.println("Finish " + a);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            });
        }

        executor.shutdown();
    }
}
