package org.example.thread.local;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("ALL")
public class ThreadLocalRandomApp {
    public static void main(String[] args) {
        var executor = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 100; i++) {
            executor.execute(() -> {
                try {
                    Thread.sleep(1000);
                    var number = ThreadLocalRandom.current().nextInt(100);
                    System.out.println(number);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.shutdown();
    }
}
