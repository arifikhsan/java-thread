package org.example;

@SuppressWarnings("ALL")
public class RaceConditionApp {
    public static void main(String[] args) throws InterruptedException {
        var counter = new Counter();
        Runnable runnable = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.increment();
            }
        };

        // result: 3_000_000
        // runnable.run();
        // runnable.run();
        // runnable.run();

        var thread1 = new Thread(runnable);
        var thread2 = new Thread(runnable);
        var thread3 = new Thread(runnable);

        // result: below 3_000_000
        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println(counter.getValue());
    }
}
