package nl.amis.hr.model.services;

import java.util.List;

import javax.ejb.Remote;

import nl.amis.hr.model.entities.Departments;
import nl.amis.hr.model.entities.Employees;

@Remote
public interface HrSessionEJB {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    <T> T persistEntity(T entity);

    <T> T mergeEntity(T entity);

    List<Employees> getEmployeesFindAll();

    List<Employees> getEmployeesFindByLastname(String lastName);

    List<Departments> getDepartmentsFindAll();

    List<Departments> getDepartmentsFindById(Integer departmentId);
}
