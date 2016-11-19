package org.zoltor.dao.impl;

import org.zoltor.dao.EmployeeDao;
import org.zoltor.db.entities.Employee;

import javax.ejb.Stateful;

/**
 * Created by Pavel Ordenko on 14/11/2016, 23:03.
 */
@Stateful
public class EmployeeDaoImpl extends AbstractGenericDaoImpl<Employee> implements EmployeeDao {
}
