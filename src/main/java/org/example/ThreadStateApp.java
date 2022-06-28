package org.example;

public class ThreadStateApp {
    public static void main(String[] args) throws InterruptedException {
        var thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getState());
            System.out.println("Hello from thread: " + Thread.currentThread().getName());
        });
        thread.setName("Arif");
        System.out.println(thread.getState());
        thread.start();
        thread.join();
        System.out.println(thread.getState());
    }
}
