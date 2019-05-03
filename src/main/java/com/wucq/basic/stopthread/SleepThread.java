package com.wucq.basic.stopthread;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SleepThread
 */
public class SleepThread extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(SleepThread.class);

    @Override
    public void run() {
        super.run();
        try {
            Thread.sleep( 1000);
        } catch (InterruptedException e) {
            // TODO: handle exception
            logger.error("step into catch");
            logger.info("sleepThreaf isInterupted -2> {}", this.isInterrupted());
        }
    }

    public static void main(String[] args) {

        BasicConfigurator.configure();
        SleepThread sleepThread = new SleepThread();
        sleepThread.start();
        sleepThread.interrupt();
        logger.info("sleepThread.isInterrupted 1->{}", sleepThread.isInterrupted());
        logger.debug("end");
    }
}