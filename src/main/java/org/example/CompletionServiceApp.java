package org.example;

import java.util.Random;
import java.util.concurrent.*;

@SuppressWarnings("ALL")
public class CompletionServiceApp {
    private final Random random = new Random();

    public static void main(String[] args) {
        var executor = Executors.newFixedThreadPool(10);
        var completionService = new ExecutorCompletionService<>(executor);

        // submit task
        Executors.newSingleThreadExecutor().execute(() -> {
            for (int i = 0; i < 100; i++) {
                final var index = i;
                completionService.submit(() -> {
                    try {
                        Thread.sleep(2000);
                        return "Task " + index;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        });

        Executors.newSingleThreadExecutor().execute(() -> {
            while (true) {

                try {
                    var future = completionService.poll(5, TimeUnit.SECONDS);
                    if (future != null) {
                        break;
                    } else {
                        System.out.println(future.get());
                    }
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
