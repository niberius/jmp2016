package org.zoltor.car;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zoltor on 19/08/16.
 */
public class MercedesCar extends BaseCar {
    public MercedesCar(CountDownLatch starter) {
        super("Mercedes", 100L, starter);
    }
}
