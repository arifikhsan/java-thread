package org.example.thread.local;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("ALL")
public class ThreadLocalRandomStreamApp {
    public static void main(String[] args) {
        var executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            // ThreadLocalRandom.current().ints().limit(10).forEach(System.out::println);
            ThreadLocalRandom.current().ints(1000, 0, 1000)
                    .forEach(System.out::println);
        });
        executor.shutdown();
    }
}
