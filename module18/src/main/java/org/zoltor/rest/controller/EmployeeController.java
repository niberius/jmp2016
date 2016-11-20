package org.zoltor.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zoltor.constants.EmployeeGender;
import org.zoltor.dao.EmployeeDao;
import org.zoltor.dao.impl.EmployeeDaoImpl;
import org.zoltor.db.entities.Address;
import org.zoltor.db.entities.Employee;
import org.zoltor.db.entities.EmployeePersonalInfo;

import javax.ejb.EJB;
import java.util.Date;

/**
 * Created by Pavel Ordenko on 19/11/2016, 14:49.
 */
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @RequestMapping(value = "/employee/1", method = RequestMethod.GET)
    public Employee read() {
        Employee employee = new Employee();
        employee.setLogin("v.pupken");
        employee.setPassword("122345");
        employee.setEmail("v.pupken@erat.com");
        Address employeeAddress = new Address();
        employeeAddress.setAddress("Prospekt str., the biggest house");
        employeeAddress.setCity("Mensk");
        employeeAddress.setCountry("Lukmore Kingdom");
        employeeAddress.setZipCode("220123");
        employee.setAddress(employeeAddress);
        EmployeePersonalInfo personalInfo = new EmployeePersonalInfo();
        personalInfo.setAdditionalInfo("Add info");
        personalInfo.setBirthDate(new Date(1L));
        personalInfo.setFirstName("Vasya");
        personalInfo.setLastName("Pupken");
        personalInfo.setGender(EmployeeGender.MALE);
        employee.setEmployeePersonalInfo(personalInfo);
        employeeDao.create(employee);
        System.out.println(employeeDao.getAll());
        return employee;
    }

}
