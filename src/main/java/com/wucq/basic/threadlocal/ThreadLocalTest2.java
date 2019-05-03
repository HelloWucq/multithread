package com.wucq.basic.threadlocal;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ThreadLocalTest2
 */
public class ThreadLocalTest2<T> extends ThreadLocal<String> {

    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalTest2.class);
    public static ThreadLocalTest2<String> threadLocal = new ThreadLocalTest2<>();

    public static void main(String[] args) {

        BasicConfigurator.configure();

        new Thread(new Runnable() {

            @Override
            public void run() {
                if (threadLocal.get() == null) {
                    logger.info("Thread {} no value", Thread.currentThread().getName());
                    threadLocal.set("Add value " + Thread.currentThread().getName());
                }
                logger.info("Thread {} value {}", Thread.currentThread().getName(), threadLocal.get());
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                if (threadLocal.get() == null) {
                    logger.info("Thread {} no value", Thread.currentThread().getName());
                    threadLocal.set("Add value " + Thread.currentThread().getName());
                }
                logger.info("Thread {} value {}", Thread.currentThread().getName(), threadLocal.get());
            }
        }).start();
    }

    @Override
    protected String initialValue() {
        return "the init value is " + Thread.currentThread().getName();
    }
}