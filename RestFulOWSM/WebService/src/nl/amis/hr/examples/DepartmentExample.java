package nl.amis.hr.examples;

import nl.amis.hr.model.entities.Department;
import nl.amis.hr.model.entities.Employee;


public class DepartmentExample {
    public DepartmentExample() {
    }

    public static final Department SAMPLE_ITEM = new Department();

    static {
       SAMPLE_ITEM.setDepartmentId(100);
       SAMPLE_ITEM.setDepartmentName("Sales");
       Employee manager = new Employee();
       manager.setEmployeeId(100);
       manager.setFirstName("Edwin");
       manager.setLastName("Biemond");
       SAMPLE_ITEM.setManager(manager);
       SAMPLE_ITEM.setLocationId(1700);
    }

}
