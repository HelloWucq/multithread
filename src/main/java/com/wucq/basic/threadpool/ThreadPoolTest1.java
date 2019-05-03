package com.wucq.basic.threadpool;

import org.apache.log4j.BasicConfigurator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadPoolTest1
 */
public class ThreadPoolTest1 {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        for (int i = 0; i < 3; i++) {
            executorService.execute(new MyTask());
        }
    }
}