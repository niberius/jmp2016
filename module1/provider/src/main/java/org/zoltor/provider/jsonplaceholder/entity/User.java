package org.zoltor.provider.jsonplaceholder.entity;

import org.zoltor.communicator.annotation.JsonItem;

/**
 * Created by zoltor on 11/07/16.
 */
public class User extends BaseEntity {

    @JsonItem("id")
    private int id;

    @JsonItem("name")
    private String name;

    @JsonItem("username")
    private String userName;

    @JsonItem("email")
    private String email;

    @JsonItem("phone")
    private String phone;

    @JsonItem("website")
    private String webSite;

    @JsonItem("company")
    private Company company;

    @JsonItem("address")
    private Address address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebSite() {
        return webSite;
    }

    public void setWebSite(String webSite) {
        this.webSite = webSite;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
