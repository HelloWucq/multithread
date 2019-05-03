package com.wucq.basic.reentranlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ReentranlockTest
 */
public class ReentranlockTest {

    private static final Logger logger = LoggerFactory.getLogger(ReentranlockTest.class);
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void await() {

        try {
            lock.lock();
            logger.info("Thread {} start await", Thread.currentThread().getName());
            condition.await();
            logger.info("Thread {} end await", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            logger.info("Thread {} unlock", Thread.currentThread().getName());
            lock.unlock();
        }
        logger.info("Thread {} exit await", Thread.currentThread().getName());
    }

    public void signal() {

        try {
            lock.lock();
            logger.info("Thread {} start signal", Thread.currentThread().getName());
            condition.signal();
            logger.info("Thread {} end signal", Thread.currentThread().getName());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            logger.info("Thread {} unlock", Thread.currentThread().getName());
            lock.unlock();
        }
        logger.info("Thread {} exit signal", Thread.currentThread().getName());
    }

    public static void main(String[] args) {

        BasicConfigurator.configure();
        final ReentranlockTest reentranlockTest = new ReentranlockTest();
        new Thread(new Runnable() {

            @Override
            public void run() {
                reentranlockTest.await();
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                reentranlockTest.signal();
            }
        }).start();
    }
}