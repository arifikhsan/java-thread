package org.example;

public class SleepApp {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            try {
                Thread.sleep(2_000);
                System.out.println("Hello from thread: " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        var thread = new Thread(runnable);
        thread.start();

        System.out.println("Hello from thread: " + Thread.currentThread().getName());
        System.out.println("Selesai");
    }
}
