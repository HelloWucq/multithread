package com.wucq.basic.communicate;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Type2
 */
public class Type2 {

    private static final Logger logger = LoggerFactory.getLogger(Type1.class);

    public static void main(String[] args) throws InterruptedException {

        BasicConfigurator.configure();
        final Object object = new Object();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (object) {
                    try {
                        logger.debug("prepare wait, threadName -> {}", Thread.currentThread().getName());
                        Thread.sleep(1000);
                        object.wait();
                        logger.debug("wait is over, threadName ->{}", Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        // TODO: handle exception
                        logger.error("InterruptedException error!");
                    }
                }
            }
        });
        thread1.start();

        Thread.sleep(1);
        Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (object) {
                    try {
                        logger.debug("prepare wait, threadName -> {}", Thread.currentThread().getName());
                        Thread.sleep(1000);
                        object.wait();
                        logger.debug("waiting is over, threadName -> {}", Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        logger.error("InterruptedException error!");
                    }

                }
            }
        });
        thread2.start();

        Thread.sleep(1);
        Thread thread3 = new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (object) {
                    try {
                        logger.debug("prepare notify, threadName -> {}", Thread.currentThread().getName());
                        Thread.sleep(1000);
                        // object.notify();
                        object.notifyAll();
                        logger.debug("waiting is over, threadName -> {}", Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        logger.error("InterruptedException error!");
                    }

                }
            }
        });
        thread3.start();

        Thread.sleep(5000);
        logger.debug("thread1 state is ->{}", thread1.getState());
        logger.debug("thread2 state is ->{}", thread2.getState());
        logger.debug("thread3 state is ->{}", thread3.getState());
    }
}