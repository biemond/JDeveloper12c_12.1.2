package nl.amis.hr.model.entities;

import java.util.Date;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Employee.class)
public class Employee_ {

	public static volatile SingularAttribute<Employee, Long> serialVersionUID;

	public static volatile SingularAttribute<Employee, Integer> commissionPct;

	public static volatile SingularAttribute<Employee, String> email;

	public static volatile SingularAttribute<Employee, Integer> employeeId;

	public static volatile SingularAttribute<Employee, String> firstName;

	public static volatile SingularAttribute<Employee, Date> hireDate;

	public static volatile SingularAttribute<Employee, String> jobId;

	public static volatile SingularAttribute<Employee, String> lastName;

	public static volatile SingularAttribute<Employee, String> phoneNumber;

	public static volatile SingularAttribute<Employee, Integer> salary;

	public static volatile SingularAttribute<Employee, Employee> manager;

	public static volatile ListAttribute<Employee, Employee> managerEmployeesList;

	public static volatile ListAttribute<Employee, Department> managerDepartmentsList;

	public static volatile SingularAttribute<Employee, Department> department;

}