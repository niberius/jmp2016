package org.zoltor;

import com.sun.jersey.api.client.Client;
import com.sun.org.apache.regexp.internal.RE;
import org.junit.Test;
import org.zoltor.pojo.User;

import javax.ws.rs.core.MediaType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by Pavel Ordenko on 02/11/2016, 23:31.
 */
public class UserControllerTest {

    private static final String BASE_HOST = "http://" + System.getProperty("tomcatAddress", "localhost:8080") + "/module17";
    private static final String CREATE_URI = BASE_HOST + "/user/create";
    private static final String READ_URI = BASE_HOST + "/user/%s";
    private static final String UPDATE_URI = BASE_HOST + "/user/%s/update";
    private static final String DELETE_URI = BASE_HOST + "/user/%s/delete";
    private final Client jerseyClient = Client.create();

    @Test
    public void testCreate() throws Exception {
        String login = "vpupken";
        User userForRequest = getTestUser(login);
        User userFromResponse = createUserByRest(login);
        assertEquals(userForRequest, userFromResponse);
    }

    @Test
    public void testRead() throws Exception {
        String login = "vpupkenRead";
        User expectedUser = getTestUser(login);
        createUserByRest(login);
        User userFromResponse = jerseyClient
                .resource(String.format(READ_URI, login))
                .accept(MediaType.APPLICATION_XML)
                .get(User.class);
        assertEquals(expectedUser, userFromResponse);
    }

    @Test
    public void testUpdate() throws Exception {
        String login = "vpupkenUpdate";
        User userForUpdate = createUserByRest(login);
        userForUpdate.setLogin("vpupkenUpdate1");
        userForUpdate.setFirstName("VasyaVasya");
        userForUpdate.setLastName("PupkenPupken");
        userForUpdate.setEmail("pupa@hax0r.com");
        userForUpdate.setAvatar(new byte[]{1,0,0,1});
        jerseyClient
                .resource(String.format(UPDATE_URI, login))
                .type(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .put(User.class, userForUpdate);
        User updatedUser = jerseyClient
                .resource(String.format(READ_URI, login))
                .accept(MediaType.APPLICATION_XML)
                .get(User.class);
        assertEquals(userForUpdate, updatedUser);
    }
    @Test
    public void testDelete() throws Exception {
        String login = "vpupkenDelete";
        createUserByRest(login);
        String response = jerseyClient
                .resource(String.format(DELETE_URI, login))
                .type(MediaType.APPLICATION_XML)
                .delete(String.class);
        assertEquals("DELETED", response);
    }

    private User createUserByRest(String userLogin) {
        User userForRequest = getTestUser(userLogin);
        return jerseyClient
                .resource(CREATE_URI)
                .type(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_XML)
                .post(User.class, userForRequest);
    }

    private User getTestUser(String userLogin) {
        User user = new User();
        user.setLogin(userLogin);
        user.setFirstName("Vasya");
        user.setLastName("Pupken");
        user.setEmail("vpupkn@local.host");
        try (InputStream avatarInputStream = this.getClass().getClassLoader().getResourceAsStream("userAvatar.png")) {
            int nextByte;
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            while ((nextByte = avatarInputStream.read()) != -1) {
                buffer.write(nextByte);
            }
            user.setAvatar(buffer.toByteArray());
            buffer.close();
            avatarInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

}
