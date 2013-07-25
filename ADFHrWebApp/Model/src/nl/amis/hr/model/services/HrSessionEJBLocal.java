package nl.amis.hr.model.services;

import java.util.List;

import javax.ejb.Local;

import nl.amis.hr.model.entities.Departments;
import nl.amis.hr.model.entities.Employees;

@Local
public interface HrSessionEJBLocal {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    <T> T persistEntity(T entity);

    <T> T mergeEntity(T entity);

    List<Employees> getEmployeesFindAll();

    List<Departments> getDepartmentsFindAll();

    List<Departments> getDepartmentsFindByLocationId(Integer locationId);
}
