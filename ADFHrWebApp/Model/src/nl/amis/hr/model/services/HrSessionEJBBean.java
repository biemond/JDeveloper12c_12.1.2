package nl.amis.hr.model.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import nl.amis.hr.model.entities.Departments;
import nl.amis.hr.model.entities.Employees;


@Stateless(name = "HrSessionEJB")
public class HrSessionEJBBean implements HrSessionEJBLocal {

    @PersistenceContext(unitName = "HrServer")
    private EntityManager em;


    public HrSessionEJBBean() {
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Employees> getEmployeesFindAll() {
        return em.createNamedQuery("Employees.findAll", Employees.class).getResultList();
    }


    /** <code>select o from Departments o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Departments> getDepartmentsFindAll() {
        return em.createNamedQuery("Departments.findAll", Departments.class).getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Departments> getDepartmentsFindByLocationId(Integer locationId) {
        System.out.println("called");
        if ( locationId == null ) {
          locationId = 1800;     
        } 
        return em.createNamedQuery("Departments.findByLocationId", Departments.class)
            .setParameter("locationId", locationId).getResultList();
    }
}
