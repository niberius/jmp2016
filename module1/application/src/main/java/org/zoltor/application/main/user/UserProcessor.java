package org.zoltor.application.main.user;

import org.zoltor.provider.jsonplaceholder.controller.UserController;
import org.zoltor.provider.jsonplaceholder.entity.User;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by zoltor on 11/07/16.
 */
public class UserProcessor {

    private UserController userController = new UserController();

    public void printUserInfo(int id) {
        try {
            User user = userController.getUserById(id);
            String userIdString = "------ User ID: " + user.getId() + " ------";
            System.out.println(userIdString);
            System.out.println("Full Name: " + user.getName());
            System.out.println("User Name: " + user.getUserName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Phone Number: " + user.getPhone());
            System.out.println("Web Site: " + user.getWebSite());
            System.out.println("Company: " + user.getCompany().getName());
            System.out.println("Address: " + user.getAddress().getCity() + ", " + user.getAddress().getSuite() + ", " +
                    user.getAddress().getCity() + ", " + user.getAddress().getZipCode() + ". Latitude " +
                    user.getAddress().getGeoLocation().getLatitude() + ", longitude " +
                    user.getAddress().getGeoLocation().getLongitude());
            System.out.println(String.format("%0" + userIdString.length() + "d1", 0).replaceAll("\\d+", "-"));
        } catch (IOException e) {
            System.out.println("----- User not found with ID: " + id+ " -----");
        }
    }

}
