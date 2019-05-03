package com.wucq.basic.reentranlock;

import java.util.Date;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ReentranlockTest4
 */
public class ReentranlockTest4 {

    private static final Logger logger = LoggerFactory.getLogger(ReentranlockTest4.class);
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void awaitA() {

        logger.info("Thread {} entering, waiting the lock", Thread.currentThread().getName());
        try {
            lock.lock();
            logger.info("Thread {} begain await", Thread.currentThread().getName());
            Date dateline = new Date();
            dateline.setSeconds(dateline.getSeconds() + 5);
            condition.awaitUntil(dateline);
            logger.info("time has pass {}", dateline.getSeconds());
            logger.info("Thrad {} end await", Thread.currentThread().getName());
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {

        BasicConfigurator.configure();
        final ReentranlockTest4 reentranlockTest4 = new ReentranlockTest4();
        new Thread(new Runnable() {

            @Override
            public void run() {
                reentranlockTest4.awaitA();
            }
        }).start();
    }
}