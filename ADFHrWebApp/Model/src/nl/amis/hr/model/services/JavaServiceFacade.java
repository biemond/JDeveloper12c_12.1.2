package nl.amis.hr.model.services;

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
    public void commitTransaction() {
        final EntityTransaction entityTransaction = em.getTransaction();
        if (!entityTransaction.isActive()) {
            entityTransaction.begin();
        }
        entityTransaction.commit();
    }

    /**
     * The persistence context is cleared of all managed entities, rolling back
     * all pending changes in the persistence tier.
     */
    public void rollbackTransaction() {
        em.clear();
    }

    /**
     * There is currently no way to ask the EntityManager whether changes are
     * pending in the persistence context.  For now, we just always assume there
     * are pending changes whenever the EntityManager is active.
     *
     * @return true if the EntityManager is open for business.
     */
    public boolean isTransactionDirty() {
        return em.isOpen();
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
        return entity;
    }

    public <T> T mergeEntity(T entity) {
        return em.merge(entity);
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
}
