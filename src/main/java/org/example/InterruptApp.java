package org.example;

public class InterruptApp {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Runnable: " + i);

                if (Thread.interrupted()) {
                    System.out.println("Interrupted");
                    break;
                }

                try {
                    Thread.sleep(1_000);
                } catch (InterruptedException e) {
                    // e.printStackTrace(); // jangan cuma print stack trace
                    break; // break dari loop
                }
            }
        };

        var thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5_000);
        thread.interrupt();
        thread.join();

        System.out.println("Selesai");
    }
}
