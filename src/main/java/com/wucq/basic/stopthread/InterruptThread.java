package com.wucq.basic.stopthread;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * InterruptThread
 */
public class InterruptThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(InterruptThread.class);

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            logger.debug("the i ->{}", i);
        }
    }

    public static void main(String[] args) {

        BasicConfigurator.configure();
        try {
            InterruptThread interruptedThread = new InterruptThread();
            interruptedThread.start();
            Thread.sleep(200);
            interruptedThread.interrupt();
            logger.debug("Stop or not? {}", interruptedThread.isInterrupted());
        } catch (InterruptedException e) {
            // TODO: handle exception
            logger.error("main catch", e);
        }
    }
}