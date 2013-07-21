package nl.amis.hr.model.entities;

import java.io.Serializable;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * To create ID generator sequence "EMPLOYEES_ID_SEQ_GEN":
 * CREATE SEQUENCE "EMPLOYEES_ID_SEQ_GEN" INCREMENT BY 50 START WITH 50;
 */
@Entity
@NamedQueries({
              @NamedQuery(name = "Employees.findAll", query = "select o from Employees o"),
              @NamedQuery(name = "Employees.findByLastname", query = "select o from Employees o where o.lastName = :lastName ",
                          hints = { @QueryHint(name = "eclipselink.left-join-fetch", value = "o.managerDepartmentsList") })
    })
@SequenceGenerator(name = "Employees_Id_Seq_Gen", sequenceName = "EMPLOYEES_SEQ", allocationSize = 1,
                   initialValue = 50)

@Table(name = "EMPLOYEES")
@XmlRootElement
public class Employees implements Serializable {
    @SuppressWarnings("oracle.jdeveloper.java.serialversionuid-stale")
    private static final long serialVersionUID = -1L;
    @Column(name = "COMMISSION_PCT")
    private Integer commissionPct;
    @Column(nullable = false, unique = true, length = 25)
    private String email;
    @Id
    @Column(name = "EMPLOYEE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Employees_Id_Seq_Gen")
    private Integer employeeId;
    @Column(name = "FIRST_NAME", length = 20)
    private String firstName;
    @Temporal(TemporalType.DATE)
    @Column(name = "HIRE_DATE", nullable = false)
    private Date hireDate;
    @Column(name = "JOB_ID", nullable = false, length = 10)
    private String jobId;
    @Column(name = "LAST_NAME", nullable = false, length = 25)
    private String lastName;
    @Column(name = "PHONE_NUMBER", length = 20)
    private String phoneNumber;
    private Integer salary;
    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    private Employees manager;
    @XmlTransient
    @OneToMany(mappedBy = "manager", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Employees> managerEmployeesList;
    @XmlTransient
    @OneToMany(mappedBy = "manager", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Departments> managerDepartmentsList;
    @XmlTransient
    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Departments department;

    public Employees() {
    }

    public Employees(Integer commissionPct, Departments department, String email, Integer employeeId, String firstName,
                     Date hireDate, String jobId, String lastName, Employees manager, String phoneNumber,
                     Integer salary) {
        this.commissionPct = commissionPct;
        this.department = department;
        this.email = email;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.hireDate = hireDate;
        this.jobId = jobId;
        this.lastName = lastName;
        this.manager = manager;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }


    public Integer getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(Integer commissionPct) {
        this.commissionPct = commissionPct;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
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
    public List<Employees> getManagerEmployeesList() {
        return managerEmployeesList;
    }

    @XmlTransient
    public void setManagerEmployeesList(List<Employees> managerEmployeesList) {
        this.managerEmployeesList = managerEmployeesList;
    }

    public Employees addEmployees(Employees employees) {
        getManagerEmployeesList().add(employees);
        employees.setManager(this);
        return employees;
    }

    public Employees removeEmployees(Employees employees) {
        getManagerEmployeesList().remove(employees);
        employees.setManager(null);
        return employees;
    }

    @XmlTransient
    public List<Departments> getManagerDepartmentsList() {
        return managerDepartmentsList;
    }

    @XmlTransient
    public void setManagerDepartmentsList(List<Departments> managerDepartmentsList) {
        this.managerDepartmentsList = managerDepartmentsList;
    }

    public Departments addDepartments(Departments departments) {
        getManagerDepartmentsList().add(departments);
        departments.setManager(this);
        return departments;
    }

    public Departments removeDepartments(Departments departments) {
        getManagerDepartmentsList().remove(departments);
        departments.setManager(null);
        return departments;
    }

    @XmlTransient
    public Departments getDepartment() {
        return department;
    }

    @XmlTransient
    public void setDepartment(Departments department) {
        this.department = department;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getClass().getName() + "@" + Integer.toHexString(hashCode()));
        buffer.append('[');
        buffer.append("commissionPct=");
        buffer.append(getCommissionPct());
        buffer.append(',');
        buffer.append("email=");
        buffer.append(getEmail());
        buffer.append(',');
        buffer.append("employeeId=");
        buffer.append(getEmployeeId());
        buffer.append(',');
        buffer.append("firstName=");
        buffer.append(getFirstName());
        buffer.append(',');
        buffer.append("hireDate=");
        buffer.append(getHireDate());
        buffer.append(',');
        buffer.append("jobId=");
        buffer.append(getJobId());
        buffer.append(',');
        buffer.append("lastName=");
        buffer.append(getLastName());
        buffer.append(',');
        buffer.append("phoneNumber=");
        buffer.append(getPhoneNumber());
        buffer.append(',');
        buffer.append("salary=");
        buffer.append(getSalary());
        buffer.append(']');
        return buffer.toString();
    }
}
