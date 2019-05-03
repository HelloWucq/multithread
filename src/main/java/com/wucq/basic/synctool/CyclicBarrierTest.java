package com.wucq.basic.synctool;

import java.util.concurrent.CyclicBarrier;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CyclicBarrierTest:线程间的相互等待，可以重用
 */
public class CyclicBarrierTest {

    private static final Logger logger=LoggerFactory.getLogger(CyclicBarrierTest.class);

    public static void main(String[] args) throws InterruptedException{

        BasicConfigurator.configure();
        
        final CyclicBarrier cyclicBarrier=new CyclicBarrier(2);
        for (int i = 0; i < 4; i++) {
            Thread.sleep(2000);
            new Thread(new Runnable(){
            
                @Override
                public void run() {
                    logger.info("Thread {}",Thread.currentThread().getName());
                    try {
                        cyclicBarrier.await();
                    } catch (Exception e) {
                        //TODO: handle exception
                        e.printStackTrace();
                    }
                    logger.info("Thread {}",Thread.currentThread().getName());
                }
            }).start();
        }
    }
}