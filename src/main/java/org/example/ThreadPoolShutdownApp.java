package org.example;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class ThreadPoolShutdownApp {
    public static void main(String[] args) throws InterruptedException {
        var minThread = 80;
        var maxThread = 100;
        var alive = 1;
        var aliveTime = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(1000);

        var executor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue);

        for (int i = 0; i < 1000; i++) {
            final var task = i;
            Runnable runnable = () -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("Task " + task + " from thread: " + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            };
            executor.execute(runnable); // menjalankan
        }

        // executor.shutdownNow();
        executor.awaitTermination(5, TimeUnit.SECONDS);
         executor.shutdown(); // menghentikan thread pool
    }
}
