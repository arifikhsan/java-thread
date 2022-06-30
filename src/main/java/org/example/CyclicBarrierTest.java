package org.example;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        final var cyclicBarrier = new CyclicBarrier(5);
        final var executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 5; i++) {
            final var index = i;
            executor.execute(() -> {
                try {
                    System.out.println("Waiting " + index);
                    cyclicBarrier.await(); // task ditahan disini sampai ada 5 thread yang melewati barrier
                    System.out.println("Finish " + index);
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
    }
}
