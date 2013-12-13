
package br.ufsc.ramonfacchin.samplews.generated;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.7-b01-
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "DatabaseConfigurationServiceService", targetNamespace = "http://impl.service.ramonfacchin.ufsc.br/", wsdlLocation = "http://0.0.0.0:8080/archetype-javaee6-ejb/DatabaseConfigurationService?wsdl")
public class DatabaseConfigurationServiceService
    extends Service
{

    private final static URL DATABASECONFIGURATIONSERVICESERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(br.ufsc.ramonfacchin.samplews.generated.DatabaseConfigurationServiceService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = br.ufsc.ramonfacchin.samplews.generated.DatabaseConfigurationServiceService.class.getResource(".");
            url = new URL(baseUrl, "http://0.0.0.0:8080/archetype-javaee6-ejb/DatabaseConfigurationService?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://0.0.0.0:8080/archetype-javaee6-ejb/DatabaseConfigurationService?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        DATABASECONFIGURATIONSERVICESERVICE_WSDL_LOCATION = url;
    }

    public DatabaseConfigurationServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public DatabaseConfigurationServiceService() {
        super(DATABASECONFIGURATIONSERVICESERVICE_WSDL_LOCATION, new QName("http://impl.service.ramonfacchin.ufsc.br/", "DatabaseConfigurationServiceService"));
    }

    /**
     * 
     * @return
     *     returns DatabaseConfigurationService
     */
    @WebEndpoint(name = "DatabaseConfigurationServicePort")
    public DatabaseConfigurationService getDatabaseConfigurationServicePort() {
        return super.getPort(new QName("http://impl.service.ramonfacchin.ufsc.br/", "DatabaseConfigurationServicePort"), DatabaseConfigurationService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns DatabaseConfigurationService
     */
    @WebEndpoint(name = "DatabaseConfigurationServicePort")
    public DatabaseConfigurationService getDatabaseConfigurationServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://impl.service.ramonfacchin.ufsc.br/", "DatabaseConfigurationServicePort"), DatabaseConfigurationService.class, features);
    }

}
