package nl.amis.hr.model.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import nl.amis.hr.model.entities.Department;
import nl.amis.hr.model.entities.Employee;


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
    public List<Employee> getEmployeesFindAll() {
        return em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    /** <code>select o from Employees o where o.lastName = :lastName </code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Employee> getEmployeesFindByLastname(String lastName) {
        return em.createNamedQuery("Employee.findByLastname", Employee.class).setParameter("lastName",
                                                                                             lastName).getResultList();
    }

    /** <code>select o from Departments o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Department> getDepartmentsFindAll() {
        return em.createNamedQuery("Department.findAll", Department.class).getResultList();
    }

    /** <code>select o from Departments o where o.departmentId = :departmentId</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Department> getDepartmentFindById(Integer departmentId) {
        return em.createNamedQuery("Department.findById", Department.class).setParameter("departmentId",
                                                                                           departmentId).getResultList();
    }
}
