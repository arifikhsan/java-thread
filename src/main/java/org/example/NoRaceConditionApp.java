package org.example;

import org.example.model.SynchronizeCounter;

@SuppressWarnings("ALL")
public class NoRaceConditionApp {
    public static void main(String[] args) throws InterruptedException {
        var counter = new SynchronizeCounter();
        Runnable runnable = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
        };

        var thread1 = new Thread(runnable);
        var thread2 = new Thread(runnable);
        var thread3 = new Thread(runnable);

        // result: still 3_000_000
        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(counter.getValue());
    }
}
