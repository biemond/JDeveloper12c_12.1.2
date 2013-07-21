package nl.amis.hr.model.entities;

import java.util.Date;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Employees.class)
public class Employees_ {

	public static volatile SingularAttribute<Employees, Long> serialVersionUID;

	public static volatile SingularAttribute<Employees, Integer> commissionPct;

	public static volatile SingularAttribute<Employees, String> email;

	public static volatile SingularAttribute<Employees, Integer> employeeId;

	public static volatile SingularAttribute<Employees, String> firstName;

	public static volatile SingularAttribute<Employees, Date> hireDate;

	public static volatile SingularAttribute<Employees, String> jobId;

	public static volatile SingularAttribute<Employees, String> lastName;

	public static volatile SingularAttribute<Employees, String> phoneNumber;

	public static volatile SingularAttribute<Employees, Integer> salary;

	public static volatile SingularAttribute<Employees, Employees> manager;

	public static volatile ListAttribute<Employees, Employees> managerEmployeesList;

	public static volatile ListAttribute<Employees, Departments> managerDepartmentsList;

	public static volatile SingularAttribute<Employees, Departments> department;

}