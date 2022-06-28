package org.example;

import java.util.Timer;
import java.util.TimerTask;

public class PeriodicJobApp {
    public static void main(String[] args) throws InterruptedException {
        var task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        };

        var timer = new Timer();
        timer.schedule(task, 2000, 2000);

        Thread.sleep(10_000);
        timer.cancel();
    }
}
