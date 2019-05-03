package com.wucq.basic.pipe;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PipeTest1
 */
public class PipeTest1 {

    private static final Logger logger = LoggerFactory.getLogger(PipeTest1.class);

    public static void main(String[] args) throws IOException {

        BasicConfigurator.configure();

        final PipedInputStream inputStream = new PipedInputStream();
        final PipedOutputStream outputStream = new PipedOutputStream();

        outputStream.connect(inputStream);

        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("Thread {} entering run", Thread.currentThread().getName());
                try {
                    for (int i = 0; i < 8; i++) {
                        String data = String.valueOf(i);
                        outputStream.write(data.getBytes());
                        logger.info("Thread {} write data {}", Thread.currentThread().getName(), data);
                        outputStream.flush();
                    }
                    outputStream.close();
                    logger.info("Thread {} is over!", Thread.currentThread().getName());
                } catch (IOException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    logger.info("Thread {} entering", Thread.currentThread().getName());
                    byte[] buffer = new byte[20];
                    int readLength = inputStream.read(buffer);
                    while (readLength != -1) {
                        String data = new String(buffer, 0, readLength);
                        logger.info("Thread {} read data {}", Thread.currentThread().getName(), data);
                        readLength = inputStream.read(buffer);
                    }
                    inputStream.close();
                    logger.info("Thread {} is over", Thread.currentThread().getName());
                } catch (IOException e) {
                    // TODO: handle exception
                }
            }
        }).start();
    }
}