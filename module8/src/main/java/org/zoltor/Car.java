package org.zoltor;

/**
 * Created by Pavel Ordenko on 26/08/2016, 00:26.
 * A car (shared resource) can pass several cross roads
 */
public class Car {

    private final String name;

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
