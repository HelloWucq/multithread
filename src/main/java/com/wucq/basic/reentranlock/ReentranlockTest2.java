package com.wucq.basic.reentranlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ReentranlockTest2
 */
public class ReentranlockTest2 {

    private static final Logger logger = LoggerFactory.getLogger(ReentranlockTest2.class);
    private ReentrantLock reentrantLock = new ReentrantLock();
    private Condition condition = reentrantLock.newCondition();

    private volatile int currentNum = 1;

    public void printName() {

        try {
            reentrantLock.lock();
            while (!String.valueOf(currentNum).equals(Thread.currentThread().getName())) {
                condition.await();
            }
            logger.info("Thread {} ", Thread.currentThread().getName());
            condition.signalAll();
            currentNum++;
        } catch (InterruptedException e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {

        BasicConfigurator.configure();
        final ReentranlockTest2 reentranlockTest2 = new ReentranlockTest2();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                reentranlockTest2.printName();
            }
        };

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable, (i + 1) + "");
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}