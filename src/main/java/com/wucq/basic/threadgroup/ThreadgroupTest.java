package com.wucq.basic.threadgroup;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ThreadgroupTest
 */
public class ThreadgroupTest {

    private static final Logger logger = LoggerFactory.getLogger(ThreadgroupTest.class);

    public static void main(String[] args) throws InterruptedException {

        BasicConfigurator.configure();
        ThreadGroup threadGroup = new ThreadGroup(Thread.currentThread().getThreadGroup() + "main2");
        logger.info("active threadgroup is {} ", Thread.currentThread().getThreadGroup().activeGroupCount());

        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {

                @Override
                public void run() {
                    logger.info("Thread {} is in dead circle", Thread.currentThread().getName());
                    while (!Thread.currentThread().isInterrupted()) {

                    }
                    logger.info("Thread {} finish dead circle", Thread.currentThread().getName());
                }
            }).start();
        }

        Thread.sleep(2000);
        threadGroup.interrupt();
    }
}