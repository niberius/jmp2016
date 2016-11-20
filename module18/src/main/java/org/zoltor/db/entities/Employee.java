package org.zoltor.db.entities;

import org.zoltor.constants.EmployeeStatus;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Pavel Ordenko on 12/11/2016, 17:39.
 */
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_info_id", nullable = false, referencedColumnName = "id")
    private EmployeePersonalInfo employeePersonalInfo;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "country", column = @Column(name = "country")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "zip_code")),
            @AttributeOverride(name = "address", column = @Column(name = "address"))
    })
    private Address address;

    @Enumerated
    private EmployeeStatus employeeStatus;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "rel_employee_project",
            joinColumns = {
                    @JoinColumn(name = "employee_id", nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "project_id", nullable = false)
            }
    )
    private List<Project> projects;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public EmployeePersonalInfo getEmployeePersonalInfo() {
        return employeePersonalInfo;
    }

    public void setEmployeePersonalInfo(EmployeePersonalInfo employeePersonalInfo) {
        this.employeePersonalInfo = employeePersonalInfo;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public EmployeeStatus getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(EmployeeStatus employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
