package org.example.thread.local;

import org.example.service.UserService;

import java.util.Random;
import java.util.concurrent.Executors;

@SuppressWarnings("ALL")
public class ThreadLocalApp {
    public static void main(String[] args) {
//        var threadLocal = ThreadLocal.withInitial(() -> "Arif");
//        System.out.println(threadLocal.get());

        var random = new Random();
        var service = new UserService();
        var executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 50; i++) {
            final var index = i;
            executor.execute(() -> {
                try {
                    var user = "User " + index;
                    service.setUser(user);
                    Thread.sleep(1000 + random.nextInt(3000));
                    service.printUser();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executor.shutdown();
    }
}
