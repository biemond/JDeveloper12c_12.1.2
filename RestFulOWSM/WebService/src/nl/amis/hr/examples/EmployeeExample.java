package nl.amis.hr.examples;

import nl.amis.hr.model.entities.Employee;

public class EmployeeExample {
    public EmployeeExample() {
        super();
    }

    public static final Employee SAMPLE_ITEM = new Employee();

    static {
       SAMPLE_ITEM.setEmployeeId(100);
       SAMPLE_ITEM.setFirstName("Edwin");
       SAMPLE_ITEM.setLastName("Biemond");;
    }


}
