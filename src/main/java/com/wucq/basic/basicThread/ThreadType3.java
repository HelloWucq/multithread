package com.wucq.basic.basicThread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ThreadType3
 */
public class ThreadType3 implements Callable<String> {

    private static final Logger log = LoggerFactory.getLogger(ThreadType1.class);

    @Override
    public String call() throws Exception {
        try {
            Thread.sleep(3000);
            System.out.println("this is new thread!");
            log.debug("new thread run, name is -> {}", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "done";
    }

    public static void main(String[] args) {

        BasicConfigurator.configure();

        Callable<String> callableThread = new ThreadType3();
        FutureTask<String> task = new FutureTask<>(callableThread);
        Thread thread = new Thread(task);
        thread.start();

        log.debug("game is over, threadname is -> {}", Thread.currentThread().getName());
    }

}