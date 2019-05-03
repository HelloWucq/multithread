package com.wucq.basic.synctool;

import java.util.concurrent.Exchanger;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ExchangerTest
 */
public class ExchangerTest {

    private static final Logger logger=LoggerFactory.getLogger(ExchangerTest.class);

    public static void main(String[] args) throws InterruptedException{

        BasicConfigurator.configure();
        final Exchanger<String>exchanger=new Exchanger<>();
        for (int i = 0; i < 4; i++) {
            Thread.sleep(2000);
                new Thread(new Runnable(){

                @Override
                public void run() {
                    logger.info("Thread {}",Thread.currentThread().getName());
                    try {
                        String exchange=exchanger.exchange(Thread.currentThread().getName());
                        logger.error("Thread {} exchange {}", Thread.currentThread().getName(),exchange);
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