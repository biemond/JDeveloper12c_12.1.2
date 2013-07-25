package nl.amis.hr.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * To create ID generator sequence "DEPARTMENTS_ID_SEQ_GEN":
 * CREATE SEQUENCE "DEPARTMENTS_ID_SEQ_GEN" INCREMENT BY 50 START WITH 50;
 */
@Entity
@NamedQueries({ @NamedQuery(name = "Departments.findAll", 
                            query = "select o from Departments o" ),
                
                @NamedQuery(name = "Departments.findByLocationId", 
                            query = "select o from Departments o where o.locationId = :locationId") 
              })
@SequenceGenerator(name = "Departments_Id_Seq_Gen", sequenceName = "DEPARTMENTS_SEQ", allocationSize = 1,
                   initialValue = 50)
@Table(name = "DEPARTMENTS"  )
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


    @Column(name = "MANAGER_ID")
    private Integer managerId;


    public Departments() {
    }

    public Departments(Integer departmentId, String departmentName, Integer locationId, Integer managerId) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.locationId = locationId;
        this.managerId = managerId;
    }



    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getManagerId() {
        return managerId;
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
