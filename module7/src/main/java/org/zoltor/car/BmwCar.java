package org.zoltor.car;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zoltor on 19/08/16.
 */
public class BmwCar extends BaseCar {
    public BmwCar(CountDownLatch starter) {
        super("BMW", 103L, starter);
    }
}
