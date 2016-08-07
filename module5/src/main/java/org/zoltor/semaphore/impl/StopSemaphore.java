package org.zoltor.semaphore.impl;

import org.zoltor.semaphore.Semaphore;

/**
 * Created by zoltor on 07/08/16.
 */
public class StopSemaphore implements Semaphore {

    @Override
    public void lever() {
        System.out.println(getClass() + ": Stop! Red semaphore.");
    }
}
