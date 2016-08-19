package org.zoltor.car;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zoltor on 19/08/16.
 */
public abstract class BaseCar extends Thread implements Runnable {

    private static final long MAX_DISTANCE = 10000;
    private final long TIME_START_MILLIS = System.currentTimeMillis();
    private volatile long timeEndMillis;
    // Does any car was disqualified? Flag for all threads (SHOULDN'T BE THREAD SAFE!)
    private static boolean hasDisqualified = false;
    // Starter should be an instance of the same CountDownLatch object in every car thread
    private final CountDownLatch starter;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d.MM.Y, HH:mm:ss.SSS");
    private Logger log = Logger.getLogger(getClass().getName());

    private long friction;
    private long distance;

    private final String name;

    public BaseCar(String name, long friction, CountDownLatch starter) {
        this.name = name;
        this.friction = friction;
        this.starter = starter;
    }

    public BaseCar(String name, CountDownLatch starter) {
        this(name, 100, starter);
    }

    @Override
    public void run() {
        try {
            // Wait the global latch to run the threads at the same time
            starter.await();
            Date timeStart = new Date(TIME_START_MILLIS);
            log.log(Level.INFO, "The car '" + name + "' has started the race on " + simpleDateFormat.format(timeStart));
            while (distance < MAX_DISTANCE) {
                if (!hasDisqualified && System.currentTimeMillis() - TIME_START_MILLIS > 5000) {
                    hasDisqualified = true;
                    this.interrupt();
                }
                Thread.sleep(friction);
                distance += 100;
                //log.info(name + " " + distance);
            }
            timeEndMillis = System.currentTimeMillis();
            Date timeEnd = new Date(timeEndMillis);
            log.log(Level.INFO, "The car '" + name + "' has finished the race on " + simpleDateFormat.format(timeEnd));
        } catch (InterruptedException e) {
            log.log(Level.INFO, "The car '" + name + "' was disqualified after 5 seconds");
        }
    }

    public String getCarName() {
        return name;
    }

    public long getTimeEndMillis() {
        return timeEndMillis;
    }

}
