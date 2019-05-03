package com.wucq.basic.produceAndconsume;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Achievement2
 */
public class Achievement2 {

    private static final Logger logger = LoggerFactory.getLogger(Achievement2.class);
    private Lock lock = new ReentrantLock();
    private Condition producerCon = lock.newCondition();
    private Condition consumerCon = lock.newCondition();

    private volatile List<String> list = new ArrayList<>();
    private volatile int capacity = 10;

    public void addElement() {
        try {
            lock.lock();
            for (int i = 0; i < 15; i++) {
                Thread.sleep(100);
                while (list.size() >= capacity) {
                    producerCon.await();
                }
                logger.info("threadName {} produce the element {}", Thread.currentThread().getName(), i);
                list.add("this is " + i);
                consumerCon.signalAll();

            }
        } catch (InterruptedException e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void delElement() {
        try {
            lock.lock();
            for (int i = 0; i < 15; i++) {
                Thread.sleep(100);
                while (list.size() == 0) {
                    consumerCon.await();
                }
                logger.info("threadName {} consume {}", Thread.currentThread().getName(), list.get(0));
                list.remove(0);
                producerCon.signalAll();
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) throws InterruptedException {

        BasicConfigurator.configure();
        final Achievement2 achievement2 = new Achievement2();

        new Thread(new Runnable() {

            @Override
            public void run() {
                achievement2.delElement();
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                achievement2.delElement();
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                achievement2.addElement();
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                achievement2.addElement();
            }
        }).start();
    }
}