package org.zoltor.application.main;

import org.zoltor.application.main.user.UserProcessor;
import org.zoltor.provider.jsonplaceholder.entity.User;

import java.util.Scanner;

/**
 * Created by zoltor on 12/07/16.
 */
public class Main {

    private static final UserProcessor userProcessor = new UserProcessor();

    public static void main(String[] args) {
        System.out.println("WELCOME! This is a user viewer for http://jsonplaceholder.typicode.com/");
        Scanner scanner = new Scanner(System.in);
        // Forever loop. Press Ctrl+D to abort
        // TODO Any protection from incorrect input
        while (true) {
            System.out.println("Enter user ID: ");
            int userId = scanner.nextInt();
            userProcessor.printUserInfo(userId);
        }
    }

}
