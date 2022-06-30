package org.example.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("ALL")
public class ThreadPoolApp {
    public static void main(String[] args) {
        var minThread = 10;
        var maxThread = 100;
        var alive = 1;
        var aliveTime = TimeUnit.MINUTES;
        var queue = new ArrayBlockingQueue<Runnable>(100);

        var executor = new ThreadPoolExecutor(minThread, maxThread, alive, aliveTime, queue);

        Runnable runnable = () -> {
            try {
                Thread.sleep(5000);
                System.out.println("Runnable from thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        };

        executor.execute(runnable); // menjalankan
        executor.shutdown(); // menghentikan thread pool
    }
}
