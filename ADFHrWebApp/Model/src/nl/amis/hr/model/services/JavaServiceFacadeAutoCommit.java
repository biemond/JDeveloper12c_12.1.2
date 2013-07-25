package nl.amis.hr.model.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import nl.amis.hr.model.entities.Departments;
import nl.amis.hr.model.entities.Employees;

import oracle.binding.AttributeContext;
import oracle.binding.RowContext;

public class JavaServiceFacadeAutoCommit {
    private final EntityManager em;

    public JavaServiceFacadeAutoCommit() {
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

    /** <code>select o from Employees o</code> */
    public List<Employees> getEmployeesFindAll() {
        return em.createNamedQuery("Employees.findAll", Employees.class).getResultList();
    }

    /** <code>select o from Departments o</code> */
    public List<Departments> getDepartmentsFindAll() {
        return em.createNamedQuery("Departments.findAll", Departments.class).getResultList();
    }

    /** <code>select o from Departments o where o.locationId = :locationId</code> */
    public List<Departments> getDepartmentsFindByLocationId(Integer locationId) {
        return em.createNamedQuery("Departments.findByLocationId", Departments.class).setParameter("locationId",
                                                                                                   locationId).getResultList();
    }

    public boolean isTransactionDirty() {
        return false;
    }

    public void rollbackTransaction() {
    }

    public boolean setAttributeValue(AttributeContext p0, Object p1) {
        return false;
    }

    public Object createRowData(RowContext p0) {
        return null;
    }

    public Object registerDataProvider(RowContext p0) {
        return null;
    }

    public boolean removeRowData(RowContext p0) {
        return false;
    }

    public void validate() {
    }
}
