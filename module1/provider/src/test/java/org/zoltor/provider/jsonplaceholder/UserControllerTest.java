package org.zoltor.provider.jsonplaceholder;

import org.junit.Test;
import org.zoltor.provider.jsonplaceholder.controller.UserController;
import org.zoltor.provider.jsonplaceholder.entity.User;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by zoltor on 11/07/16.
 */
// TODO Think about mocks
public class UserControllerTest {

    private UserController userController = new UserController();

    @Test
    public void testGetUserById() throws IOException {
        User user = userController.getUserById(1);
        assertEquals(1, user.getId());
        assertEquals("Leanne Graham", user.getName());
        assertEquals("Bret", user.getUserName());
        assertEquals("Sincere@april.biz", user.getEmail());
        assertEquals("1-770-736-8031 x56442", user.getPhone());
        assertEquals("hildegard.org", user.getWebSite());
        assertEquals("Romaguera-Crona", user.getCompany().getName());
        assertEquals("Multi-layered client-server neural-net", user.getCompany().getCatchPhrase());
        assertEquals("harness real-time e-markets", user.getCompany().getBs());
        assertEquals("Kulas Light", user.getAddress().getStreet());
        assertEquals("Apt. 556", user.getAddress().getSuite());
        assertEquals("Gwenborough", user.getAddress().getCity());
        assertEquals("92998-3874", user.getAddress().getZipCode());
        assertEquals("-37.3159", user.getAddress().getGeoLocation().getLatitude());
        assertEquals("81.1496", user.getAddress().getGeoLocation().getLongitude());
    }

}
