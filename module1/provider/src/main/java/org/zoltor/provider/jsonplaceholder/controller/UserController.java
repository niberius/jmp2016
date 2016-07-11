package org.zoltor.provider.jsonplaceholder.controller;

import org.zoltor.communicator.config.HttpMethod;
import org.zoltor.provider.jsonplaceholder.entity.User;

/**
 * Created by zoltor on 11/07/16.
 */
public class UserController extends BaseController {

    public User getUserById(int id) {
        return getEntity("/users/" + id, HttpMethod.GET, "", User.class);
    }

}
