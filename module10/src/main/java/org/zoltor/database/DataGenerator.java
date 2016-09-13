package org.zoltor.database;

import org.apache.commons.lang3.RandomUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

/**
 * Created by zoltor on 13/09/16.
 */
public class DataGenerator {

    private Database db = new Database();
    private static final Random RANDOM = new Random();
    private static final long JAN_1_2015 = 1420070400000L;
    private static final long JAN_1_2016 = 1451606400000L;

    public void createTables() {
        db.update("CREATE TABLE IF NOT EXISTS user (" +
                "id IDENTITY PRIMARY KEY," +
                "name VARCHAR(100) NOT NULL," +
                "surname VARCHAR(100) NOT NULL," +
                "birthdate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP" +
                ");");
        db.update("CREATE TABLE IF NOT EXISTS friendship (" +
                "user_id_1 BIGINT NOT NULL," +
                "user_id_2 BIGINT NOT NULL," +
                "timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (user_id_1) REFERENCES user (id) ON DELETE CASCADE," +
                "FOREIGN KEY (user_id_2) REFERENCES user (id) ON DELETE CASCADE" +
                ");");
        db.update("CREATE TABLE IF NOT EXISTS post (" +
                "id IDENTITY PRIMARY KEY," +
                "user_id BIGINT NOT NULL," +
                "text VARCHAR (10000) NOT NULL," +
                "timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE" +
                ");");
        db.update("CREATE TABLE IF NOT EXISTS vote (" +
                "post_id BIGINT NOT NULL," +
                "user_id BIGINT NOT NULL," +
                "timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (post_id) REFERENCES post (id) ON DELETE CASCADE," +
                "FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE" +
                ");");
    }

    /**
     * Fill tables with random data in the right order
     */
    public void fillTables() {
        fillUserTable();
        fillFriendshipTable();
        fillPostTable();
        fillVoteTable();
    }

    /**
     * Print the list of users who have more than 75 friends and more than 1 like in March of 2015
     */
    public void printUsers() {
        ResultSet resultSet = db.select("SELECT u.name || ' ' || u.surname AS name FROM user u WHERE (u.id IN (\n" +
                "\tSELECT f.user_id_1 FROM friendship f\n" +
                "\tGROUP BY f.user_id_1\n" +
                "\tHAVING COUNT(f.user_id_1) > 75\n" +
                ") OR u.id IN (\n" +
                "\tSELECT f.user_id_2 FROM friendship f\n" +
                "\tGROUP BY f.user_id_2\n" +
                "\tHAVING COUNT(f.user_id_2) > 75\n" +
                ")) AND u.id IN (\n" +
                "\tSELECT p.user_id FROM post p WHERE p.id IN (\n" +
                "\t\tSELECT p1.id FROM post p1 \n" +
                "\t\tJOIN vote v ON v.post_id = p1.id\n" +
                "\t\tWHERE v.timestamp BETWEEN '2015-03-01' AND '2015-04-01'\n" +
                "\t\tGROUP BY p1.id\n" +
                "\t\tHAVING COUNT(p1.id) > 3\n" +
                "\t)\n" +
                ");");
        try {
            boolean isUserFound = false;
            while (resultSet.next()) {
                isUserFound = true;
                System.out.println(resultSet.getString(1));
            }

            // Let user know that nothing was found
            if (!isUserFound) {
                System.out.println("No users found :(");
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println("SQL Exception occurred");
            e.printStackTrace();
        }
    }

    private void fillUserTable() {
        String[] names = new String[]{"Vasya", "Pasha", "Alex", "Yuri", "Mamuka", "Sergey", "Anton", "John", "Chris", "Gogi"};
        String[] sureNames = new String[]{"Pupken", "Ivanov", "Rubashnikov", "Blinkov", "Javin", "Smith", "Petrov", "Sidorov", "Zheleznyak", "Korobasov"};
        // Create 1000 rows with users (index will be 1-1000)
        for (int i = 0; i <= 1000; i++) {
            db.update("INSERT INTO user (name, surname, birthdate) VALUES (?, ?, ?);",
                    names[RANDOM.nextInt(names.length)],
                    sureNames[RANDOM.nextInt(sureNames.length)],
                    getPseudoRandomDate());
        }
    }

    private void fillFriendshipTable() {
        // Create 70 000 rows in friendship
        for (int i = 0; i <= 70000; i++) {
            db.update("INSERT INTO friendship (user_id_1, user_id_2, timestamp) VALUES (?, ?, ?);",
                    getPositivePseudoRandomInt(1002),
                    getPositivePseudoRandomInt(1002),
                    getPseudoRandomDate());
        }
    }

    private void fillPostTable() {
        // Create 50 000 rows in user post
        for (int i = 0; i <= 50_000; i++) {
            db.update("INSERT INTO post (user_id, text, timestamp) VALUES (?, ?, ?);",
                    getPositivePseudoRandomInt(1002),
                    "Blablablaba",
                    getPseudoRandomDate());
        }
    }

    private void fillVoteTable() {
        // Create 300 000 likes for posts
        for (int i = 0; i <= 300000; i++) {
            db.update("INSERT INTO vote (post_id, user_id, timestamp) VALUES (?, ?, ?);",
                    getPositivePseudoRandomInt(50002),
                    getPositivePseudoRandomInt(1002),
                    getRandomDateInRange(JAN_1_2015, JAN_1_2016));
        }
    }

    /**
     * Get Pseudo-Random date starting from beginning of UNIX epoch till current day
     *
     * @return Pseudo-Random date starting from beginning of UNIX epoch till current day
     */
    private Date getPseudoRandomDate() {
        return new Date((long) (RANDOM.nextDouble() * System.currentTimeMillis()));
    }

    /**
     * Get random date in range of milliseconds
     *
     * @param startMillisInclusive Start millis inclusive
     * @param endMillisExclusive nd millis inclusive
     * @return Date with random date in desired borders
     */
    private Date getRandomDateInRange(long startMillisInclusive, long endMillisExclusive) {
        return new Date(RandomUtils.nextLong(startMillisInclusive, endMillisExclusive));
    }

    /**
     * Get positive integer starting from 1 inclusive (values <= 0 are excluded)
     *
     * @param border Top border of integer (exclusive)
     * @return Pseudo-Random integer in borders [1,border)
     */
    private int getPositivePseudoRandomInt(int border) {
        int result;
        do {
            result = RANDOM.nextInt(border);
        } while (result <= 0);
        return result;
    }

    public void shutdown() {
        db.shutdown();
    }
}
