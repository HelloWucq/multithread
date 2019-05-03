package com.wucq.basic.synctool;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * CountDownLatchTest :若干个线程等待其他的线程执行完毕
 */
public class CountDownLatchTest {

    private static final Logger logger=LoggerFactory.getLogger(CountDownLatchTest.class);
    public static void main(String[] args) {   

        BasicConfigurator.configure();
        final CountDownLatch latch = new CountDownLatch(2);
         
        new Thread(){
            public void run() {
                try {
                    logger.info("Thread {} is running",Thread.currentThread().getName());
                   Thread.sleep(000);
                   logger.info("Thread {} finish running",Thread.currentThread().getName());
                   latch.countDown();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
            };
        }.start();
         
        new Thread(){
            public void run() {
                try {
                    logger.info("Thread {} is running",Thread.currentThread().getName());
                    Thread.sleep(000);
                    logger.info("Thread {} finish running",Thread.currentThread().getName());
                    latch.countDown();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
            };
        }.start();
         
        try {
            logger.info("waiting for the two child thread");
           latch.await();
           logger.info("two child thread finish");
           logger.info("main thread continuing running");
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
    }
}