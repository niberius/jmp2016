package org.zoltor.dao.impl;

import org.springframework.stereotype.Repository;
import org.zoltor.dao.EmployeeDao;
import org.zoltor.db.entities.Employee;

import javax.ejb.Stateful;

/**
 * Created by Pavel Ordenko on 14/11/2016, 23:03.
 */
@Stateful
@Repository
public class EmployeeDaoImpl extends AbstractGenericDaoImpl<Employee> implements EmployeeDao {
}
