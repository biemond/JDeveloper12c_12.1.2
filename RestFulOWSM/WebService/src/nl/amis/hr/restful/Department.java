package nl.amis.hr.restful;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import nl.amis.hr.model.entities.Departments;
import nl.amis.hr.model.services.HrSessionEJBLocal;

@Path("departments")
@Consumes(value = { "application/json", "application/xml" })
@Produces(value = { "application/json", "application/xml" })
public class Department {
    public Department() {

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
    @Path("department/{id}")
    public  Departments getDepartmentsById(  @PathParam("id") Integer departmentId) {

        List<Departments> dept = hrBean.getDepartmentsFindById(departmentId);
        if ( dept != null && dept.size() >0 ) {
          return dept.get(0);
        }  
        return null;
    }

    @GET
    public List<Departments> getDepartments() {

        List<Departments> depts = hrBean.getDepartmentsFindAll();
        return depts;
    }



}
