package nl.amis.hr.model.entities;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * To create ID generator sequence "DEPARTMENTS_ID_SEQ_GEN":
 * CREATE SEQUENCE "DEPARTMENTS_ID_SEQ_GEN" INCREMENT BY 50 START WITH 50;
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Department.findAll", query = "select o from Department o") ,
                
                @NamedQuery(name = "Department.findById",
                          query = "select o from Department o where o.departmentId = :departmentId",
                          hints = { @QueryHint(name = "eclipselink.left-join-fetch", value = "o.employeesList")}) 
              })
@SequenceGenerator(name = "Departments_Id_Seq_Gen", sequenceName = "DEPARTMENTS_SEQ", allocationSize = 1,
                   initialValue = 50)
@Table(name = "DEPARTMENTS"  )
@XmlRootElement(name = "department")
public class Department implements Serializable {
    @SuppressWarnings("oracle.jdeveloper.java.serialversionuid-stale")
    private static final long serialVersionUID = -1L;


    @Id
    @Column(name = "DEPARTMENT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Departments_Id_Seq_Gen")
    private Integer departmentId;

    @Column(name = "DEPARTMENT_NAME", nullable = false, length = 30)
    private String departmentName;
    @Column(name = "LOCATION_ID")
    private Integer locationId;

    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    private Employee manager;

    @OneToMany(mappedBy = "department", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Employee> employeesList;

    public Department() {
    }

    public Department(Integer departmentId, String departmentName, Integer locationId, Employee manager) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.locationId = locationId;
        this.manager = manager;
    }


    public Integer getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }


    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public void setEmployeesList(List<Employee> employeesList) {
        this.employeesList = employeesList;
    }

    public Employee addEmployees(Employee employees) {
        getEmployeesList().add(employees);
        employees.setDepartment(this);
        return employees;
    }

    public Employee removeEmployees(Employee employees) {
        getEmployeesList().remove(employees);
        employees.setDepartment(null);
        return employees;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("departmentId=");
        buffer.append(getDepartmentId());
        buffer.append(',');
        buffer.append("departmentName=");
        buffer.append(getDepartmentName());
        buffer.append(',');
        buffer.append("locationId=");
        buffer.append(getLocationId());
        buffer.append(',');
        buffer.append(']');
        return buffer.toString();
    }
}
