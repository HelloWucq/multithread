package com.wucq.basic.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;

/**
 * ScheduledThreadTest
 */
public class ScheduledThreadTest {

    private static final ScheduledExecutorService service = Executors.newScheduledThreadPool(2);

    public static void main(String[] args) {
        BasicConfigurator.configure();
        // service.schedule(new MyTask(), 3, TimeUnit.SECONDS);
        service.scheduleAtFixedRate(new MyTask(), 3, 3, TimeUnit.SECONDS);
        // service.scheduleWithFixedDelay(new MyTask(), 3, 5, TimeUnit.SECONDS);

    }
}