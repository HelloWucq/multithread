package com.wucq.basic.communicate;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Type1
 */
public class Type1 {

    private static final Logger logger = LoggerFactory.getLogger(Type1.class);
    private static volatile List list = new ArrayList<>();

    public static void main(String[] args) {

        BasicConfigurator.configure();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        logger.debug("add element ->{}, threadName ->{}", i, Thread.currentThread().getName());
                        list.add(String.valueOf(i));
                        Thread.sleep(1);
                    }
                } catch (InterruptedException e) {
                    // TODO: handle exception
                    logger.error("InterruptedException", e);
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    while (true) {
                        if (list.size() == 5) {
                            logger.debug("list size is 5, thread will quit!");
                            throw new InterruptedException();
                        }
                    }
                } catch (InterruptedException e) {
                    // TODO: handle exception
                    logger.error("throw exception, thread quit ", e);
                }
            }
        }).start();
    }
}