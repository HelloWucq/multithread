package com.wucq.basic.communicate;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Type4
 */
public class Type4 {

    private static final Logger logger = LoggerFactory.getLogger(Type4.class);
    private volatile static boolean isOddThread = true;

    public static void main(String[] args) throws InterruptedException {

        BasicConfigurator.configure();

        final Object obj = new Object();
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        synchronized (obj) {
                            while (!isOddThread) {
                                obj.wait();
                            }
                            logger.debug("ThreadName is {}, print {}", Thread.currentThread(), "+++");
                            isOddThread = false;
                            obj.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                }
            }).start();

        }
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        synchronized (obj) {
                            // 注意使用while,而不是if
                            while (isOddThread) {
                                obj.wait();
                            }
                            logger.debug("ThreadName is {}, print {}", Thread.currentThread(), "***");
                            isOddThread = true;
                            obj.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}