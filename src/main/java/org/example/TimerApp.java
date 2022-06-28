package org.example;

import java.util.Timer;
import java.util.TimerTask;

public class TimerApp {
    public static void main(String[] args) throws InterruptedException {
        var task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        };

        var timer = new Timer();
        timer.schedule(task, 2000);

        Thread.sleep(3000);
        timer.cancel();
    }
}
