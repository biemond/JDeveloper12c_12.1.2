package nl.amis.hr.model.test;

import java.util.Hashtable;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import nl.amis.hr.model.entities.Departments;
import nl.amis.hr.model.services.HrSessionEJB;

public class HrSessionEJBClient {
    public static void main(String[] args) {
        try {
            final Context context = getInitialContext();
            HrSessionEJB hrSessionEJB =
                (HrSessionEJB) context.lookup("RestFulOWSM-Model-HrSessionEJB#nl.amis.hr.model.services.HrSessionEJB");
            List<Departments> dept =  hrSessionEJB.getDepartmentsFindById(170);

        } catch (CommunicationException ex) {
            System.out.println(ex.getClass().getName());
            System.out.println(ex.getRootCause().getLocalizedMessage());
            System.out.println("\n*** A CommunicationException was raised.  This typically\n*** occurs when the target WebLogic server is not running.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Context getInitialContext() throws NamingException {
        Hashtable env = new Hashtable();
        // WebLogic Server 10.x/12.x connection details
        env.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
        env.put(Context.PROVIDER_URL, "t3://127.0.0.1:7101");
        return new InitialContext(env);
    }
}
