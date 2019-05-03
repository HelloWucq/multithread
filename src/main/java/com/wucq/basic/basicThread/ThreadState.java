package com.wucq.basic.basicThread;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ThreadState
 */
public class ThreadState extends Thread {

    private static final Logger log = LoggerFactory.getLogger(ThreadState.class);

    @Override
    public void run() {
        try {

            Thread.sleep(3000);
            synchronized (ThreadState.class) {
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {

        BasicConfigurator.configure();
        ThreadState threadState = new ThreadState();
        threadState.start();
        Thread.sleep(2000);

        ThreadState threadState2 = new ThreadState();
        log.debug("state is ->{}", threadState2.getState());
        threadState2.start();
        log.debug("state is ->{}", threadState2.getState());
        Thread.sleep(1000);
        log.debug("state is ->{}", threadState2.getState());
        Thread.sleep(7000);
        log.debug("state is ->{}", threadState2.getState());
        Thread.sleep(15000);
        log.debug("state is ->{}", threadState2.getState());
    }

}