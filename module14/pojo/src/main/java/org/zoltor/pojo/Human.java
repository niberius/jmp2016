package org.zoltor.pojo;

/**
 * Created by org.zoltor on 04/09/16.
 */
public class Human {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstAndLastName() {
        return firstName + " " + lastName;
    }
}
