package com.wucq.basic.pipe;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PipeTest2
 */
public class PipeTest2 {
    private static final Logger logger = LoggerFactory.getLogger(PipeTest1.class);

    public static void main(String[] args) throws IOException {

        BasicConfigurator.configure();

        final PipedWriter writer = new PipedWriter();
        final PipedReader reader = new PipedReader();

        reader.connect(writer);

        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info("Thread {} entering run", Thread.currentThread().getName());
                try {
                    for (int i = 0; i < 8; i++) {
                        String data = String.valueOf(i);
                        writer.write(data);
                        logger.info("Thread {} write data {}", Thread.currentThread().getName(), data);
                        writer.flush();
                    }
                    writer.close();
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
                    char[] buffer = new char[20];
                    int readLength = reader.read(buffer);
                    while (readLength != -1) {
                        String data = new String(buffer, 0, readLength);
                        logger.info("Thread {} read data {}", Thread.currentThread().getName(), data);
                        readLength = reader.read(buffer);
                    }
                    reader.close();
                    logger.info("Thread {} is over", Thread.currentThread().getName());
                } catch (IOException e) {
                    // TODO: handle exception
                }
            }
        }).start();
    }
}