package org.example;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class ScheduledExecutorApp {
    public static void main(String[] args) throws InterruptedException {
        // delayedJob();
        periodicJob();
    }

    public static void delayedJob() {
        var executor  = Executors.newScheduledThreadPool(10);
        var future = executor.schedule(() -> System.out.println("Hello"), 5, TimeUnit.SECONDS);
        System.out.println(future.getDelay(TimeUnit.MILLISECONDS));
        executor.shutdown();
    }

    public static void periodicJob() throws InterruptedException {
        var executor  = Executors.newScheduledThreadPool(10);
        var future = executor.scheduleAtFixedRate(() -> System.out.println("Hello"), 2, 2, TimeUnit.SECONDS);
        System.out.println(future.getDelay(TimeUnit.MILLISECONDS));

        Thread.sleep(5000);
        executor.shutdown();
    }
}
