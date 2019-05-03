package com.wucq.basic.threadpool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MyTask
 */
public class MyTask implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(MyTask.class);

    @Override
    public void run() {
        for (int i = 0; i < 2; i++) {
            log.info("threadName->{},i->{}", Thread.currentThread(), i);
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}