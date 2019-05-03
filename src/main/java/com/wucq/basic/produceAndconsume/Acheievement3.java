package com.wucq.basic.produceAndconsume;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Acheievement3
 */
public class Acheievement3 {

    private static int num;
    private static final Logger logger = LoggerFactory.getLogger(Acheievement3.class);

    public static void main(String[] args) throws InterruptedException {

        BasicConfigurator.configure();

        final BlockingQueue<String> queue = new LinkedBlockingQueue<>(3);

        Runnable produceRun = new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(1000);
                        String element = "element " + (++num);
                        queue.put(element);
                        logger.info("Thread {} put element {}", Thread.currentThread().getName(), element);
                    }

                } catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        };

        Runnable consumerRun = new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        Thread.sleep(2000);
                        String take = queue.take();
                        logger.info("Thread {} take element {}", Thread.currentThread().getName(), take);
                    }
                } catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        };

        Thread producer1 = new Thread(produceRun);
        producer1.start();
        Thread producer2 = new Thread(produceRun);
        producer2.start();

        Thread consumer1 = new Thread(consumerRun);
        consumer1.start();
        Thread consumer2 = new Thread(consumerRun);
        consumer2.start();
    }
}