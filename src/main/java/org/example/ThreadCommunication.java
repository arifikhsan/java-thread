package org.example;

import static java.util.Objects.isNull;

@SuppressWarnings("ALL")
public class ThreadCommunication {
    public static String message = null;

    public static void main(String[] args) throws InterruptedException {

        var thread1 = new Thread(() -> {
            while (isNull(message)) {
                // wait
            }
            System.out.println(message);
        });

        var thread2 = new Thread(() -> {
            message = "Hello World!";
        });

        thread2.start();
        thread1.start();

        thread2.join();
        thread1.join();
    }
}
