package nl.amis.hr.model.entities;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * To create ID generator sequence "EMPLOYEES_ID_SEQ_GEN":
 * CREATE SEQUENCE "EMPLOYEES_ID_SEQ_GEN" INCREMENT BY 50 START WITH 50;
 */
@Entity
@NamedQueries({
              @NamedQuery(name = "Employees.findAll", 
                          query = "select o from Employees o"),
              @NamedQuery(name = "Employees.findByLastname", 
                          query = "select o from Employees o where o.lastName = :lastName ")
    })
@SequenceGenerator(name = "Employees_Id_Seq_Gen", sequenceName = "EMPLOYEES_SEQ", allocationSize = 1,
                   initialValue = 50)

@Table(name = "EMPLOYEES")
public class Employees implements Serializable {
    @SuppressWarnings("oracle.jdeveloper.java.serialversionuid-stale")
    private static final long serialVersionUID = -1L;

    @Id
    @Column(name = "EMPLOYEE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Employees_Id_Seq_Gen")
    private Integer employeeId;

    @Column(name = "COMMISSION_PCT")
    private Integer commissionPct;
    @Column(nullable = false, unique = true, length = 25)
    private String email;

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

    @Column(name = "MANAGER_ID", nullable = false)
    private Integer managerId;

    @Column(name = "DEPARTMENT_ID")
    private Integer departmentId;

    public Employees() {
    }

    public Employees(Integer commissionPct, Integer departmentId, String email, Integer employeeId, String firstName,
                     Date hireDate, String jobId, String lastName, Integer managerId, String phoneNumber,
                     Integer salary) {
        this.commissionPct = commissionPct;
        this.departmentId = departmentId;
        this.email = email;
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.hireDate = hireDate;
        this.jobId = jobId;
        this.lastName = lastName;
        this.managerId = managerId;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getManagerId() {
        return managerId;
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
