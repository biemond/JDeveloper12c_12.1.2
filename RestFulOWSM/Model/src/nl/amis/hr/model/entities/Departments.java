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
import javax.xml.bind.annotation.XmlTransient;

/**
 * To create ID generator sequence "DEPARTMENTS_ID_SEQ_GEN":
 * CREATE SEQUENCE "DEPARTMENTS_ID_SEQ_GEN" INCREMENT BY 50 START WITH 50;
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Departments.findAll", 
                            query = "select o from Departments o") ,
                
                @NamedQuery(name = "Departments.findById", 
                            query = "select o from Departments o where o.departmentId = :departmentId",
                            hints = { @QueryHint(name = "eclipselink.left-join-fetch", value = "o.employeesList")}) 
              })
@SequenceGenerator(name = "Departments_Id_Seq_Gen", sequenceName = "DEPARTMENTS_SEQ", allocationSize = 1,
                   initialValue = 50)
@Table(name = "DEPARTMENTS"  )
@XmlRootElement
public class Departments implements Serializable {
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
    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    private Employees manager;
    @XmlTransient
    @OneToMany(mappedBy = "department", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Employees> employeesList;

    public Departments() {
    }

    public Departments(Integer departmentId, String departmentName, Integer locationId, Employees manager) {
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


    @XmlTransient
    public Employees getManager() {
        return manager;
    }

    @XmlTransient
    public void setManager(Employees manager) {
        this.manager = manager;
    }

    @XmlTransient
    public List<Employees> getEmployeesList() {
        return employeesList;
    }

    @XmlTransient
    public void setEmployeesList(List<Employees> employeesList) {
        this.employeesList = employeesList;
    }

    public Employees addEmployees(Employees employees) {
        getEmployeesList().add(employees);
        employees.setDepartment(this);
        return employees;
    }

    public Employees removeEmployees(Employees employees) {
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
