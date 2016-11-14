package org.zoltor.db.entities;

import javax.persistence.Embeddable;

/**
 * Created by Pavel Ordenko on 12/11/2016, 17:29.
 */
@Embeddable
public class Address {

    private String country;
    private String city;
    private String zipCode;
    private String address;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
