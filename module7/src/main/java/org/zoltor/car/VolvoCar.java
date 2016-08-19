package org.zoltor.car;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zoltor on 19/08/16.
 */
public class VolvoCar extends BaseCar {
    public VolvoCar(CountDownLatch starter) {
        super("Volvo 850 T5-R", 102L, starter);
    }
}
