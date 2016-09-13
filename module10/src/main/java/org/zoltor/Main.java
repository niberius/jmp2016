package org.zoltor;

import org.zoltor.database.DataGenerator;

/**
 * Created by zoltor on 14/09/16.
 */
public class Main {

    public static void main(String[] args) {
        DataGenerator dataGenerator = new DataGenerator();
        try {
            dataGenerator.createTables();
            dataGenerator.fillTables();
            System.out.println("Users, who have more than 75 friends and more than 3 likes in March of 2015:");
            dataGenerator.printUsers();
        } finally {
            dataGenerator.shutdown();
        }
    }

}
