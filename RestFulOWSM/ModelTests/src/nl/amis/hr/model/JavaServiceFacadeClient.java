package nl.amis.hr.model;

import java.util.List;

import nl.amis.hr.model.entities.Departments;
import nl.amis.hr.model.entities.Employees;

public class JavaServiceFacadeClient {
    public static void main(String[] args) {
        try {
            final JavaServiceFacade javaServiceFacade = new JavaServiceFacade();
//            for (Employees employees : (List<Employees>) javaServiceFacade.getEmployeesFindAll()) {
//                printEmployees(employees);
//            }
            for (Departments departments : (List<Departments>) javaServiceFacade.getDepartmentsFindAll()) {
                printDepartments(departments);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void printEmployees(Employees employees) {
        System.out.println("commissionPct = " + employees.getCommissionPct());
        System.out.println("email = " + employees.getEmail());
        System.out.println("employeeId = " + employees.getEmployeeId());
        System.out.println("firstName = " + employees.getFirstName());
        System.out.println("hireDate = " + employees.getHireDate());
        System.out.println("jobId = " + employees.getJobId());
        System.out.println("lastName = " + employees.getLastName());
        System.out.println("phoneNumber = " + employees.getPhoneNumber());
        System.out.println("salary = " + employees.getSalary());
        System.out.println("manager = " + employees.getManager());
        System.out.println("managerEmployeesList = " + employees.getManagerEmployeesList());
        System.out.println("managerDepartmentsList = " + employees.getManagerDepartmentsList());
        System.out.println("department = " + employees.getDepartment());
    }

    private static void printDepartments(Departments departments) {
        System.out.println("departmentId = " + departments.getDepartmentId());
        System.out.println("departmentName = " + departments.getDepartmentName());
        System.out.println("locationId = " + departments.getLocationId());
        System.out.println("manager = " + departments.getManager());
        System.out.println("employeesList = " + departments.getEmployeesList());
    }
}
