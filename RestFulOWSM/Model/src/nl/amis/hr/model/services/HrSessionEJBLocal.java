package nl.amis.hr.model.services;

import java.util.List;

import javax.ejb.Local;

import nl.amis.hr.model.entities.Department;
import nl.amis.hr.model.entities.Employee;

@Local
public interface HrSessionEJBLocal {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);

    <T> T persistEntity(T entity);

    <T> T mergeEntity(T entity);

    List<Employee> getEmployeesFindAll();

    List<Employee> getEmployeesFindByLastname(String lastName);

    List<Department> getDepartmentsFindAll();

    List<Department> getDepartmentFindById(Integer departmentId);

    List<Employee> getEmployeeFindById(Integer employeeId);
}
