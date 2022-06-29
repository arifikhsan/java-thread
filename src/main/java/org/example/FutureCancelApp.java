package org.example;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SuppressWarnings("ALL")
public class FutureCancelApp {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        var executor = Executors.newSingleThreadExecutor();

        Callable<String> callable = () -> {
          Thread.sleep(5000);
          return "Hi";
        };

        Future<String> future = executor.submit(callable);

        Thread.sleep(2000);
        future.cancel(true);
        System.out.println(future.isCancelled());

        System.out.println(future.get());
        System.out.println("Selesai Future");
        executor.shutdownNow();
    }
}
