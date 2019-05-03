package com.wucq.basic.reentranlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ReentranlockTest3
 */
public class ReentranlockTest3 {

    private static final Logger logger = LoggerFactory.getLogger(ReentranlockTest3.class);
    private ReentrantLock lock = new ReentrantLock();

    public void awaitA() {
        try {
            if (lock.tryLock(2000, TimeUnit.MILLISECONDS)) {
                Thread.sleep(1000);

                logger.info("isFair {}", lock.isFair());
                logger.info("isLocked {}", lock.isLocked());
                logger.info("isHeldByCurrentThread " + lock.isHeldByCurrentThread());
                lock.unlock();
            } else {
                logger.info("Thread {} has no lock", Thread.currentThread().getName());
            }

        } catch (InterruptedException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        BasicConfigurator.configure();
        final ReentranlockTest3 reentranlockTest3 = new ReentranlockTest3();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                reentranlockTest3.awaitA();
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
    }
}