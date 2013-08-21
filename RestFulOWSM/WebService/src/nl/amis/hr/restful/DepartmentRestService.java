package nl.amis.hr.restful;

import com.sun.jersey.api.json.JSONWithPadding;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;

import nl.amis.hr.examples.DepartmentExample;
import nl.amis.hr.model.entities.Department;
import nl.amis.hr.model.services.HrSessionEJBLocal;


@Path("department")
@Produces( value = { MediaType.APPLICATION_XML,
                     MediaType.APPLICATION_JSON,
                     "application/x-javascript"})
@Stateless
public class DepartmentRestService {
    public DepartmentRestService() {

    }
    
    @EJB( name = "HrSessionEJB")
    HrSessionEJBLocal hrBean;


    /**
    * Returns the item if existing. Please be aware that this method is extremely
    * expensive, so we can't guarantee that getting items is all the time possible.
    *
    * @response.representation.200.qname departments
    * @response.representation.200.mediaType application/xml,application/json,application/x-javascript
    * @response.representation.200.doc This is the representation returned by default
    * @response.representation.200.example {@link DepartmentExample#SAMPLE_ITEM}
    *
    * @return the requested item if this service is available
    */
    @GET
    public JSONWithPadding  getDepartments( @QueryParam("callback") String callback) {

        List<Department> depts = hrBean.getDepartmentsFindAll();
        if (null == callback) {
             return new JSONWithPadding(new GenericEntity<List<Department>>(depts) {});
         } else {
           return new JSONWithPadding(new GenericEntity<List<Department>>(depts) {}, callback);
         }
    }

    /**
    * Returns the item if existing. 
    * 
    * @response.representation.200.qname department
    * @response.representation.200.mediaType application/xml,application/json,application/x-javascript
    * @response.representation.200.doc This is the representation returned by default
    * @response.representation.200.example {@link DepartmentExample#SAMPLE_ITEM}
    *
    * @return the requested item if this service is available
    */
    @GET
    @Path("/{id}")
    public  JSONWithPadding getDepartmentById( @PathParam("id") Integer departmentId,
                                               @QueryParam("callback") String callback){
       List<Department> dept = hrBean.getDepartmentFindById(departmentId);
        if ( dept != null && dept.size() >0 ) {
           if (null == callback) {
              return new JSONWithPadding(new GenericEntity<Department>(dept.get(0)) {});

            } else {
              return new JSONWithPadding(new GenericEntity<Department>(dept.get(0)) {
                        }, callback);
            }

        }
        return null;
    }


}
