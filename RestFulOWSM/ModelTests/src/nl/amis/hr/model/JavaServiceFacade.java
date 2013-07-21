package nl.amis.hr.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import nl.amis.hr.model.entities.Departments;
import nl.amis.hr.model.entities.Employees;

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

    public void removeEmployees(Employees employees) {
        employees = em.find(Employees.class, employees.getEmployeeId());
        em.remove(employees);
        commitTransaction();
    }

    /** <code>select o from Employees o</code> */
    public List<Employees> getEmployeesFindAll() {
        return em.createNamedQuery("Employees.findAll", Employees.class).getResultList();
    }

    public void removeDepartments(Departments departments) {
        departments = em.find(Departments.class, departments.getDepartmentId());
        em.remove(departments);
        commitTransaction();
    }

    /** <code>select o from Departments o</code> */
    public List<Departments> getDepartmentsFindAll() {
        return em.createNamedQuery("Departments.findAll", Departments.class).getResultList();
    }

    /** <code>select o from Departments o</code> */
    public Departments getDepartmentsFindById(Integer departmentId) {
        return em.find(Departments.class, departmentId);
    }

    public List<Employees> getEmployeeFindByLastname(String lastName) {
        return em.createNamedQuery("Employees.findByLastname", Employees.class)
            .setParameter("lastName",lastName)
            .getResultList();
    }

}
