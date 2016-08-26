package org.zoltor;

/**
 * Created by Pavel Ordenko on 26/08/2016, 15:27.
 * One cross road (thread) for two cars
 */
public class CrossRoadRunnable implements Runnable {

    private static final long RED_LIGHT_MILLIS = 100;
    private final Car currentCar;
    private final Car nextCar;


    /**
     * Create crossroad with left-to-right and top-to-bottom directions
     * @param currentCar A car which goes on left-to-right direction
     * @param nextCar A car which goes on top-to-bottom direction
     */
    public CrossRoadRunnable(Car currentCar, Car nextCar) {
        this.currentCar = currentCar;
        this.nextCar = nextCar;
    }

    @Override
    public void run() {
        synchronized (currentCar) {
            // Stop the cars from left-to-right direction. It is a time to get appropriate car lock by the thread
            turnRedLight(RED_LIGHT_MILLIS);

            // Ok, now the car can move
            System.out.println("The car '" + currentCar.getName() + "' has passed a cross-road on left-to-right direction");
            synchronized (nextCar) {
                // Stop the cars from top-to-bottom direction. It is a time to get appropriate car lock by the thread
                turnRedLight(RED_LIGHT_MILLIS);

                // Ok, now the car can move
                System.out.println("The car '" + nextCar.getName() + "' has passed a cross-road on top-to-bottom direction");
            }
        }
    }

    /**
     * Turn red light (the car will not move) for milliseconds
     *
     * @param timeMillis Milliseconds to wait
     */
    private void turnRedLight(long timeMillis) {
        try {
            Thread.sleep(timeMillis);
        } catch (InterruptedException e) {
            System.out.println("Some car goes on RED LIGHT!");
        }
    }
}
