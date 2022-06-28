package org.example;

import org.example.model.Balance;

public class DeadlockApp {
    public static void main(String[] args) throws InterruptedException {
        var balance1 = new Balance(10L);
        var balance2 = new Balance(10L);

        var thread1 = new Thread(() -> {
            try {
                Balance.transfer(balance1, balance2, 5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        var thread2 = new Thread(() -> {
            try {
                Balance.transfer(balance2, balance1, 5L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(balance1.getValue());
        System.out.println(balance2.getValue());
        assert balance1.getValue().equals(balance2.getValue());
    }
}
