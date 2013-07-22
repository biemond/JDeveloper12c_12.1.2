package nl.amis.hr.restful;

import com.sun.jersey.api.json.JSONWithPadding;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import nl.amis.hr.model.entities.Departments;
import nl.amis.hr.model.services.HrSessionEJBLocal;

@Path("departments")
@Produces( value = { "application/x-javascript",
                     MediaType.APPLICATION_JSON, 
                     MediaType.APPLICATION_XML})
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
    public  JSONWithPadding getDepartmentsById(  @PathParam("id") Integer departmentId,
                                                 @QueryParam("callback") String callback){

        List<Departments> dept = hrBean.getDepartmentsFindById(departmentId);
        if ( dept != null && dept.size() >0 ) {
           if (null == callback) {
              return new JSONWithPadding(new GenericEntity<Departments>(dept.get(0)) {});

            } else {
              return new JSONWithPadding(new GenericEntity<Departments>(dept.get(0)) {
                        }, callback);
            }

        }

        return null;
    }

    @GET
    public JSONWithPadding  getDepartments( @QueryParam("callback") String callback) {
        List<Departments> depts = hrBean.getDepartmentsFindAll();
        if (null == callback) {
             return new JSONWithPadding(new GenericEntity<List<Departments>>(depts) {});
         } else {
           return new JSONWithPadding(new GenericEntity<List<Departments>>(depts) {}, callback);
         }
    }



}
