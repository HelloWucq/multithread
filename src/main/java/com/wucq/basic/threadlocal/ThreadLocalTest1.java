package com.wucq.basic.threadlocal;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ThreadLocalTest1
 */
public class ThreadLocalTest1 {

    private static final Logger logger = LoggerFactory.getLogger(ThreadLocalTest1.class);

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        BasicConfigurator.configure();
        if (threadLocal.get() == null) {
            logger.info("there is no value!");
            threadLocal.set("value added");
        }
        logger.info("{}", threadLocal.get());
    }
}