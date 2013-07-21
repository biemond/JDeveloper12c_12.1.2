package nl.amis.hr.model.entities;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Departments.class)
public class Departments_ {

	public static volatile SingularAttribute<Departments, Long> serialVersionUID;

	public static volatile SingularAttribute<Departments, Integer> departmentId;

	public static volatile SingularAttribute<Departments, String> departmentName;

	public static volatile SingularAttribute<Departments, Integer> locationId;

	public static volatile SingularAttribute<Departments, Employees> manager;

	public static volatile ListAttribute<Departments, Employees> employeesList;

}