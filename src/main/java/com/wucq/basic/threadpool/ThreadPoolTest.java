package com.wucq.basic.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadPoolTest
 */
public class ThreadPoolTest {

    private static ExecutorService executorService = Executors.newFixedThreadPool(5);
    private static int count = 0;
    private static volatile AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) {

        long starttime = System.currentTimeMillis();
        for (long i = 0; i < 1000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    count = count + 1;
                    atomicInteger.incrementAndGet();
                }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - starttime);
        System.out.println(atomicInteger);
        System.out.println(count);
    }
}