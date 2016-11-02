package org.zoltor.rest.controller;

import org.springframework.web.bind.annotation.*;
import org.zoltor.pojo.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Pavel Ordenko on 31/10/2016, 23:17.
 */
@RestController
public class UserController {

    private static final Map<String, User> userMap = new HashMap<>();

    @RequestMapping(value = "/user/create", method = RequestMethod.POST, consumes = "application/xml")
    public User create(@RequestBody User user) {
        if (userMap.containsKey(user.getLogin())) {
            throw new RuntimeException("User with the login '" + user.getLogin() + "' already exists");
        }
        userMap.put(user.getLogin(), user);
        return user;
    }

    @RequestMapping(value = "/user/{login}", method = RequestMethod.GET)
    public User read(@PathVariable("login") String login) {
        return userMap.get(login);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Collection<User> readAll() {
        return userMap.values();
    }

    @RequestMapping(value = "/user/{login}/update", method = RequestMethod.PUT, consumes = "application/json")
    public User update(@PathVariable("login") String login, @RequestBody User user) {
        if (!userMap.containsKey(login)) {
            throw new RuntimeException("User with the login '" + user.getLogin() + "' does not exist");
        }
        userMap.put(login, user);
        return user;
    }

    @RequestMapping(value = "/user/{login}/delete", method = RequestMethod.DELETE)
    public String delete(@PathVariable("login") String login) {
        userMap.remove(login);
        return "DELETED";
    }

}
