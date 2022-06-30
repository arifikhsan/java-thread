package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserCyclicBarrierApp {
    public static void main(String[] args) {
        final var phaser = new Phaser();
        final var executor = Executors.newFixedThreadPool(15);

        phaser.bulkRegister(5); // register atau menambah 5 threads

        for (int i = 0; i < 5; i++) {
            final var index = i;
            executor.execute(() -> {
                System.out.println("Start Task");
                phaser.arriveAndAwaitAdvance(); // alternative cyclic barrier
                System.out.println("Finish " + index);
            });
        }

        executor.execute(() -> {
            phaser.awaitAdvance(0); // ditahan sampai semua thread melewati barrier
            System.out.println("All tasks finished");
        });

        executor.shutdown();
    }
}
