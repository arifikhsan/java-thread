package org.example;

import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;

public class ExchangerApp {
    public static void main(String[] args) {
        final var exchange = new Exchanger<String>();
        final var executor = Executors.newFixedThreadPool(10);

        executor.execute(() -> {
            try {
                System.out.println("Thread 1 Send: First");
                Thread.sleep(1000);
                String value = exchange.exchange("First");
                System.out.println("Thread 1 Receive: " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute(() -> {
            try {
                System.out.println("Thread 2 Send: Second");
                Thread.sleep(3000);
                String value = exchange.exchange("Second");
                System.out.println("Thread 2 Receive: " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();
    }
}
