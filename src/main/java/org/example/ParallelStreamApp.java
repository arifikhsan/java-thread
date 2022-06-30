package org.example;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ParallelStreamApp {
    public static void main(String[] args) {
        // Stream<Integer> stream = IntStream.range(1, 1000).boxed();
        // stream.parallel().forEach((integer) -> System.out.println(Thread.currentThread().getName() + ":" + integer));

        // var pool = ForkJoinPool.commonPool();
        var pool = new ForkJoinPool(1);
        ForkJoinTask<?> task = pool.submit(() -> {
            Stream<Integer> stream = IntStream.range(1, 1000).boxed();
            stream.parallel().forEach((integer) -> System.out.println(Thread.currentThread().getName() + ":" + integer));
        });

        task.join();
    }
}
