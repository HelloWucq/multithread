package com.wucq.basic.stopthread;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * StopThread
 */
public class StopThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(StopThread.class);

    @Override
    public void run() {
        super.run();
        try {
            for (int i = 0; i < 10; i++) {
                if (Thread.interrupted()) {
                    logger.debug("Thread has stopped! I will quit.");
                    throw new InterruptedException();
                }
                logger.debug("i->{}", i);
            }
        } catch (InterruptedException e) {
            logger.error("step into catch ---", e);
        }
    }

    public static void main(String[] args) {

        BasicConfigurator.configure();

        try {
            StopThread stopThread = new StopThread();
            stopThread.start();
            Thread.sleep(3);
            stopThread.interrupt();
        } catch (InterruptedException e) {
            // TODO: handle exception
            logger.debug("InterruptedException ", e);
        }
        logger.debug("end");
    }
}