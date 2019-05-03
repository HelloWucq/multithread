package com.wucq.basic.threadpool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.BasicConfigurator;

/**
 * ThreadPoolDestroyTest
 */
public class ThreadPoolDestroyTest {

    public static void main(String[] args) {

        BasicConfigurator.configure();
        PoolDestoryTest();
        System.out.println("main thread is over!");
    }

    private static void PoolDestoryTest() {

        ExecutorService service = Executors.newFixedThreadPool(4);
        final CountDownLatch latch = new CountDownLatch(4);

        for (int i = 0; i < 4; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread().getName() + " is in");
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + " is out");
                        latch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            latch.await();
            System.out.println("game is over!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}