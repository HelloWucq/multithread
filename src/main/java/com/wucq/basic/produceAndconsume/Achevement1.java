package com.wucq.basic.produceAndconsume;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Achevement1
 */
public class Achevement1 {

    private static final Logger logger = LoggerFactory.getLogger(Achevement1.class);

    public static void main(String[] args) throws InterruptedException {

        BasicConfigurator.configure();

        final List<String> list = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (list) {
                        while (true) {
                            while (list.size() == 0) {
                                list.wait();
                            }
                            logger.info("list.remove -> {}, threadName->{}", list.get(0),
                                    Thread.currentThread().getName());
                            list.remove(0);
                            list.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    synchronized (list) {
                        while (true) {
                            while (list.size() == 0) {
                                list.wait();
                            }
                            logger.info("list.remove -> {}, threadName->{}", list.get(0),
                                    Thread.currentThread().getName());
                            list.remove(0);
                            list.notifyAll();
                        }
                    }
                } catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    for (int i = 0; i < 5; i++) {
                        synchronized (list) {
                            list.add("this is " + i);
                            logger.info("element {} added, threadName is {}", i, Thread.currentThread().getName());
                            list.notifyAll();
                            list.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }

            }
        }).start();
    }
}