package nl.amis.ws.jms;

import com.oracle.webservices.api.jms.JMSDeliveryMode;
import com.oracle.webservices.api.jms.JMSDestinationType;
import com.oracle.webservices.api.jms.JMSMessageType;
import com.oracle.webservices.api.jms.JMSTransportService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@JMSTransportService(destinationType = JMSDestinationType.QUEUE,
                     destinationName = "com.oracle.webservices.api.jms.RequestQueue", 
                     replyToName = "com.oracle.webservices.api.jms.ResponseQueue",
                     jndiURL = "t3://localhost:7001",
                     jndiConnectionFactoryName = "com.oracle.webservices.api.jms.ConnectionFactory",
                     jndiInitialContextFactory = "weblogic.jndi.WLInitialContextFactory",
                     messageType = JMSMessageType.TEXT, 
                     deliveryMode = JMSDeliveryMode.PERSISTENT, 
                     priority = 4,
                     activationConfig = "maxBeansInFreePool=1000", 
                     enableHttpWsdlAccess = true)

@WebService(serviceName = "HelloService")
public class HelloService {
    public HelloService() {
    }

    @WebMethod
    @WebResult( name = "responseMessage")
    public String sayHello(@WebParam(name = "message") String msg) {
        return "Hello back , got message: " +msg;
    }
}
