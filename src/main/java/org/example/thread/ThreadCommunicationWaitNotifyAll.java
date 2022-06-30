package org.example.thread;

@SuppressWarnings("ALL")
public class ThreadCommunicationWaitNotifyAll {
    public static String message = null;

    public static void main(String[] args) throws InterruptedException {

        final var lock = new Object();

        var thread1 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        var thread3 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                    System.out.println(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        var thread2 = new Thread(() -> {
            synchronized (lock) {
                message = "Hello World!";
                lock.notifyAll(); // notify semua thread
            }
        });

        thread1.start(); // wait nya duluan
        thread3.start();
        thread2.start(); // notify nya belakangan


        thread1.join();
        thread3.join();
        thread2.join();
    }
}
