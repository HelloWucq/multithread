package com.wucq.basic.basicThread;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ThreadType2
 */
public class ThreadType2 implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(ThreadType1.class);

    @Override
    public void run() {

        try {
            Thread.sleep(5000);
            System.out.println("this is new thread!");
            log.debug("new thread run, name is -> {}", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        BasicConfigurator.configure();
        ThreadType2 threadType2 = new ThreadType2();
        Thread thread1 = new Thread(threadType2);
        Thread thread2 = new Thread(threadType2);

        thread1.start();
        thread2.start();

        log.debug("game is over, threadname is -> {}", Thread.currentThread().getName());
    }
}