package org.example;

public class DaemonApp {
    public static void main(String[] args) {
        var thread = new Thread(() -> {
            try {
                Thread.sleep(3000); // tidak akan ditunggu
                System.out.println("Run thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.setDaemon(true); // set thread as daemon thread
        thread.start();
    }
}
