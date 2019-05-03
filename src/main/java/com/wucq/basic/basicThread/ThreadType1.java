package com.wucq.basic.basicThread;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ThreadType1
 */
public class ThreadType1 extends Thread {

    private static final Logger log = LoggerFactory.getLogger(ThreadType1.class);

    @Override
    public void run() {

        try {
            Thread.sleep(1000);
            System.out.println("this is new thread!");
            log.debug("new thread run, name is -> {}", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        BasicConfigurator.configure();
        ThreadType1 threadType1 = new ThreadType1();
        threadType1.start();
        log.debug("game is over, threadname is -> {}", Thread.currentThread().getName());
    }

}