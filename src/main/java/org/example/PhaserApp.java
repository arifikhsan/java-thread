package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

@SuppressWarnings("ALL")
public class PhaserApp {
    public static void main(String[] args) {
        final var phaser = new Phaser();
        final var executor = Executors.newFixedThreadPool(10);

        phaser.bulkRegister(5); // register atau menambah 5 threads

        for (int i = 0; i < 5; i++) {
            final var index = i;
            executor.execute(() -> {
                try {
                    System.out.println("Start Task");
                    Thread.sleep(2000);
                    System.out.println("Finish " + index);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    phaser.arrive();
                }
            });
        }

        executor.execute(() -> {
            phaser.awaitAdvance(0); // ditahan sampai semua thread melewati barrier
            System.out.println("All tasks finished");
        });

        executor.shutdown();
    }
}
