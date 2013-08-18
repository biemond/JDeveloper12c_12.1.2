package nl.amis.hr.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import nl.amis.hr.model.entities.Department;
import nl.amis.hr.model.entities.Employee;

public class JavaServiceFacade {
    private final EntityManager em;

    public JavaServiceFacade() {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("HrLocal");
        em = emf.createEntityManager();
    }

    /**
     * All changes that have been made to the managed entities in the
     * persistence context are applied to the database and committed.
     */
    private void commitTransaction() {
        final EntityTransaction entityTransaction = em.getTransaction();
        if (!entityTransaction.isActive()) {
            entityTransaction.begin();
        }
        entityTransaction.commit();
    }

    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public <T> T persistEntity(T entity) {
        em.persist(entity);
        commitTransaction();
        return entity;
    }

    public <T> T mergeEntity(T entity) {
        entity = em.merge(entity);
        commitTransaction();
        return entity;
    }

    public void removeEmployees(Employee employees) {
        employees = em.find(Employee.class, employees.getEmployeeId());
        em.remove(employees);
        commitTransaction();
    }

    /** <code>select o from Employees o</code> */
    public List<Employee> getEmployeesFindAll() {
        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    public void removeDepartments(Department departments) {
        departments = em.find(Department.class, departments.getDepartmentId());
        em.remove(departments);
        commitTransaction();
    }

    /** <code>select o from Departments o</code> */
    public List<Department> getDepartmentsFindAll() {
        return em.createNamedQuery("Department.findAll", Department.class).getResultList();
    }

    /** <code>select o from Departments o</code> */
    public Department getDepartmentsFindById(Integer departmentId) {
        return em.find(Department.class, departmentId);
    }

    public List<Employee> getEmployeeFindByLastname(String lastName) {
        return em.createNamedQuery("Employee.findByLastname", Employee.class)
            .setParameter("lastName",lastName)
            .getResultList();
    }

}
