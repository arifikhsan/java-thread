package org.example;

public class ThreadNameApp {
    public static void main(String[] args) {
        var thread = new Thread(() -> System.out.println("Hello from thread: " + Thread.currentThread().getName()));
        thread.setName("Arif");
        thread.start();
    }
}
