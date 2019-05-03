package com.wucq.basic.basicThread;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ThreadFuture
 */
public class ThreadFuture implements Callable<Integer> {

    private static final Logger log = LoggerFactory.getLogger(ThreadType1.class);

    private Integer count = 0;

    @Override
    public Integer call() throws Exception {

        synchronized (this) {
            for (int i = 0; i < 1000; i++) {
                count = count + 1;
            }
        }
        System.out.println("this is new thread!");
        log.debug("new thread run, name is -> {}", Thread.currentThread().getName());
        return count;
    }

    public static void main(String[] args) {

        BasicConfigurator.configure();
        TestFuture();

    }

    public static void TestFuture() {
        int threadNum = 6;
        ExecutorService service = Executors.newFixedThreadPool(threadNum);
        ArrayList<Future<Integer>> futures = new ArrayList<Future<Integer>>(threadNum);
        for (int i = 0; i < threadNum * 2; i++) {
            Future<Integer> future = service.submit(new ThreadFuture());
            futures.add(future);
        }
        service.shutdown();

        for (Future<Integer> future : futures) {
            try {
                Integer result = future.get();
                System.out.println(Thread.currentThread().getName() + result);
            } catch (InterruptedException | ExecutionException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

}