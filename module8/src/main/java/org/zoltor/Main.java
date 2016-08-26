package org.zoltor;

/**
 * Created by Pavel Ordenko on 26/08/2016, 00:38.
 */
public class Main {

    private static final Car carVw = new Car("VW Passat B3");
    private static final Car carMercedes = new Car("Mercedes S500 W140");
    private static final Car carLada = new Car("LADA Priora Low-Rider");
    private static final Car carKraz = new Car("KrAZ 256");

    public static void main(String[] args) {
        // The cars go one by one in the squared cross-road
        new Thread(new CrossRoadRunnable(carVw, carMercedes)).start();
        new Thread(new CrossRoadRunnable(carMercedes, carLada)).start();
        new Thread(new CrossRoadRunnable(carLada, carKraz)).start();
        new Thread(new CrossRoadRunnable(carKraz, carVw)).start();
    }

}
