package nl.amis.hr.model.entities;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Department.class)
public class Department_ {

	public static volatile SingularAttribute<Department, Long> serialVersionUID;

	public static volatile SingularAttribute<Department, Integer> departmentId;

	public static volatile SingularAttribute<Department, String> departmentName;

	public static volatile SingularAttribute<Department, Integer> locationId;

	public static volatile SingularAttribute<Department, Employee> manager;

	public static volatile ListAttribute<Department, Employee> employeesList;

}