package nl.amis.hr.model.test;

import java.util.List;

import nl.amis.hr.model.JavaServiceFacade;
import nl.amis.hr.model.entities.Departments;
import nl.amis.hr.model.entities.Employees;

import org.junit.Assert;
import org.junit.Test;

public class TestDepartments  {
    public TestDepartments() {
        super();
    }

    final JavaServiceFacade javaServiceFacade = new JavaServiceFacade();

    @Test
    public void findDepartment() {
        Departments dept = javaServiceFacade.getDepartmentsFindById(170);
        Integer departmentId = dept.getDepartmentId();
        Assert.assertEquals(new Integer(170), departmentId);
    }
 
    @Test
    public void findManagerDepartments() {
        List<Employees> mgr = javaServiceFacade.getEmployeeFindByLastname("Whalen");
        List<Departments> managerDepartments = mgr.get(0).getManagerDepartmentsList();
        Assert.assertEquals(1, managerDepartments.size() );
    }

}
