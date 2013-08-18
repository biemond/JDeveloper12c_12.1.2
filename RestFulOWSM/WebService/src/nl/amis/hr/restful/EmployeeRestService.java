package nl.amis.hr.restful;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.amis.hr.model.entities.Employee;
import nl.amis.hr.model.services.HrSessionEJBLocal;

@Path("employee")
@Produces( value = { MediaType.APPLICATION_XML,
                     MediaType.APPLICATION_JSON
                     })
@Stateless
public class EmployeeRestService {
    public EmployeeRestService() {
    }

    @EJB( name = "HrSessionEJB")
    HrSessionEJBLocal hrBean;

    @GET
    @Path("/")
    public List<Employee>  getEmployees() {
        return hrBean.getEmployeesFindAll();
    }

    @GET
    @Path("/{id}")
    public  Employee getEmployeeById( @PathParam("id") Integer employeeId){
       List<Employee> emps = hrBean.getEmployeeFindById(employeeId);
       if ( emps != null && emps.size() >0 ) {
          return emps.get(0);
       }
       return null;
    }


}
