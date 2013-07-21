package nl.amis.hr.restful;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.amis.hr.model.entities.Employees;
import nl.amis.hr.model.services.HrSessionEJBLocal;

@Path("employees")
@Consumes(value = { "application/json", "application/xml" })
@Produces(value = { "application/json", "application/xml" })
public class Employee {
    public Employee() {
        try {
          iniCtx = new InitialContext();
          hrBean = (HrSessionEJBLocal) iniCtx.lookup("java:comp/env/ejb/local/Hr");  
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    InitialContext iniCtx = null;
    HrSessionEJBLocal hrBean = null;

    @GET
    @Path("lastname/{lastName}")
    public  List<Employees> getEmployeeByLastName(  @PathParam("lastName") String lastName) {

        List<Employees> emps = hrBean.getEmployeesFindByLastname(lastName);
        return emps;
    }
}
