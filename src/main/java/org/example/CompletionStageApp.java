package org.example;

import java.util.Random;
import java.util.concurrent.*;

@SuppressWarnings("ALL")
public class CompletionStageApp {
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);
    private static final Random random = new Random();

    public static CompletableFuture<String> getValue() {
        CompletableFuture<String> future = new CompletableFuture<>();
        executor.execute(() -> {
            try {
                Thread.sleep(2000);
                future.complete("Arif");
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }
        });
        return future;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = getValue();

        var result = future
                .thenApply(value -> value.toUpperCase())
                .thenApply(value -> value + " " + "Udin");


        System.out.println(result.get());
        executor.shutdown();
    }
}
