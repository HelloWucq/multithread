package com.wucq.basic.synctool;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CountDownLatchTest:可以用于确保某些活动直到其他活动都完成才继续执行
 */
public class CountDownLatchTest {

    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchTest.class);

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final CountDownLatch countDownLatch2 = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                        Thread.sleep(1000);
                        countDownLatch2.countDown();
                    } catch (InterruptedException e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        long startTime = System.nanoTime();
        countDownLatch.countDown();
        countDownLatch2.await();
        long stopTime = System.nanoTime();
        logger.error("finish time pass {}", stopTime - startTime);
    }

}