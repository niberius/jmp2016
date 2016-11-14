package org.zoltor.db.entities;

import org.zoltor.constants.EmployeeGender;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Pavel Ordenko on 12/11/2016, 17:31.
 */
public class EmployeePersonalInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Enumerated
    private EmployeeGender gender;

    @Column(name = "additional_info")
    private String additionalInfo;

    @OneToOne(mappedBy = "employeePersonalInfo")
    private Employee employee;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public EmployeeGender getGender() {
        return gender;
    }

    public void setGender(EmployeeGender gender) {
        this.gender = gender;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
