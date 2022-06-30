package org.example.collection;

import java.util.Comparator;
import java.util.concurrent.*;

@SuppressWarnings("ALL")
public class DelayQueueApp {
    public static void main(String[] args) {
        var queue = new DelayQueue<ScheduledFuture<Integer>>();
        var executor = Executors.newFixedThreadPool(20);
        var executorScheduler = Executors.newScheduledThreadPool(10);

        for (int i = 1; i <= 10; i++) {
            final var index = i;
            executor.execute(() -> {
                queue.put(executorScheduler.schedule(() -> {
                    System.out.println("Execute " + index);
                    return index;
                }, 1000, TimeUnit.MILLISECONDS));
            });
        }

        executor.execute(() -> {
            try {
                while (true) {
                    Thread.sleep(2000);
                    System.out.println("Received Data: " + queue.take().get());
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();
    }
}
