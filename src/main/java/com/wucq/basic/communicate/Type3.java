package com.wucq.basic.communicate;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Type3
 */
public class Type3 {

    private static final Logger logger = LoggerFactory.getLogger(Type3.class);

    public static void main(String[] args) {

        BasicConfigurator.configure();

        final List<String> list = new ArrayList<String>();

        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (list) {
                    try {
                        for (int i = 0; i < 10; i++) {
                            list.add("this is " + i);
                            logger.debug("adding {}", i);
                            if (list.size() == 5) {
                                logger.debug("waiting ...");
                                list.wait();
                            }
                        }
                    } catch (InterruptedException e) {
                        // TODO: handle exception
                        logger.error("InterruptedException");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (list) {
                    logger.debug("doing something, theadName is {}", Thread.currentThread().getName());
                    list.notify();
                    logger.debug("threadName is {} notify", Thread.currentThread().getName());
                }
            }
        }).start();
    }
}