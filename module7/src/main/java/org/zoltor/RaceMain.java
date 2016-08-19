package org.zoltor;

import org.zoltor.car.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by zoltor on 19/08/16.
 */
public class RaceMain {

    private static CountDownLatch starter = new CountDownLatch(1);
    private static List<BaseCar> CARS = new ArrayList<>();
    private static Logger log = Logger.getLogger(RaceMain.class.getName());

    static {
        CARS.add(new BmwCar(starter));
        CARS.add(new FerrariCar(starter));
        CARS.add(new MercedesCar(starter));
        CARS.add(new MustangCar(starter));
        CARS.add(new VolvoCar(starter));
    }

    public static void main(String[] args) {
        startTheEngines();
        // Begin the race (start all cars threads at the same time)
        starter.countDown();
        // Wait while race will be finished
        while (!isRaceFinished()) {
            // Do nothing, just wait
        }
        BaseCar winner = getWinner();
        log.log(Level.INFO, "Winner is " + winner.getCarName() + "!");
    }

    /**
     * Start cars engines (invoke {@link BaseCar#start()} for each car)
     */
    private static void startTheEngines() {
        for (BaseCar car : CARS) {
            car.start();
        }
    }

    /**
     * Check all cars thread in {@link #CARS} list to make sure that every thread is dead
     * @return True - if all cars finished the race (all threads are dead). False - otherwise.
     */
    private static boolean isRaceFinished() {
        for (BaseCar car : CARS) {
            if (car.isAlive()) {
                // Thread is not dead. Race in process
                return false;
            }
        }
        return true;
    }

    /**
     * Check every car in {@link #CARS} list to find out who won the race (based on time end millis for a car)
     *
     * @return The winner object {@link BaseCar}, who was not disqualified and who has a minimal end time millis.
     */
    private static BaseCar getWinner() {
        BaseCar winner = null;
        // Current millis is always greater than race time end millis for any car
        long currentTimeMillis = System.currentTimeMillis();
        for (BaseCar car : CARS) {
            // If our car was disqualified then it doesn't finished the race. End time wasn't initialized and == 0
            if (car.getTimeEndMillis() != 0L && car.getTimeEndMillis() < currentTimeMillis) {
                currentTimeMillis = car.getTimeEndMillis();
                winner = car;
            }
        }
        return winner;
    }
}
