package org.zoltor.car;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zoltor on 19/08/16.
 */
public class MustangCar extends BaseCar {
    public MustangCar(CountDownLatch starter) {
        super("Mustang", 101L, starter);
    }
}
