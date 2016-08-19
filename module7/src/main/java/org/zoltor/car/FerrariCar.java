package org.zoltor.car;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zoltor on 19/08/16.
 */
public class FerrariCar extends BaseCar {
    public FerrariCar(CountDownLatch starter) {
        super("Ferrari", 99L, starter);
    }
}
