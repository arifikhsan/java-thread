package org.example;

import java.util.Random;
import java.util.concurrent.*;

@SuppressWarnings("ALL")
public class CompletableFutureApp {
    private static final ExecutorService executor = Executors.newFixedThreadPool(10);
    private static final Random random = new Random();

    public static Future<String> getValue() {
        CompletableFuture<String> future = new CompletableFuture<>();
        executor.execute(() -> {
            try {
                Thread.sleep(2000);
                future.complete("Hi");
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }
        });
        return future;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Future<String> future = getValue();
        var future = getFastest();
        System.out.println(future.get());
        executor.shutdown();
    }

    public static void execute(CompletableFuture<String> future, String value) {
        executor.execute(() -> {
            try {
                Thread.sleep(1000 + random.nextInt(5000));
                future.complete(value);
            } catch (InterruptedException e) {
                future.completeExceptionally(e);
            }
        });
    }

    public static Future<String> getFastest() {
        CompletableFuture<String> future = new CompletableFuture<>();
        execute(future, "Thread 1");
        execute(future, "Thread 2");
        execute(future, "Thread 3");

        return future;
    }
}
