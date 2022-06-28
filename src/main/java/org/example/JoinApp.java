package org.example;

@SuppressWarnings("ALL")
public class JoinApp {
    public static void main(String[] args) throws InterruptedException {
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

        System.out.println("Menunggu selesai");

        thread.join();
        System.out.println("Selesai");
    }
}
