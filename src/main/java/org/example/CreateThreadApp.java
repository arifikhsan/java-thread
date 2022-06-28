package org.example;

public class CreateThreadApp {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("Hello from thread: " + Thread.currentThread().getName());

        var thread = new Thread(runnable);
        thread.start();
        System.out.println("Hello from thread: " + Thread.currentThread().getName());
        System.out.println("Selesai");
    }
}
