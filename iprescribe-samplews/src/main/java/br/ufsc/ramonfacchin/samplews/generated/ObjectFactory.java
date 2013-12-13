
package br.ufsc.ramonfacchin.samplews.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the br.ufsc.ramonfacchin.samplews.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _FindByKey_QNAME = new QName("http://impl.service.ramonfacchin.ufsc.br/", "findByKey");
    private final static QName _GetResponse_QNAME = new QName("http://impl.service.ramonfacchin.ufsc.br/", "getResponse");
    private final static QName _FindByKeyResponse_QNAME = new QName("http://impl.service.ramonfacchin.ufsc.br/", "findByKeyResponse");
    private final static QName _Get_QNAME = new QName("http://impl.service.ramonfacchin.ufsc.br/", "get");
    private final static QName _DatabaseConfiguration_QNAME = new QName("http://impl.service.ramonfacchin.ufsc.br/", "databaseConfiguration");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: br.ufsc.ramonfacchin.samplews.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DatabaseConfiguration }
     * 
     */
    public DatabaseConfiguration createDatabaseConfiguration() {
        return new DatabaseConfiguration();
    }

    /**
     * Create an instance of {@link FindByKey }
     * 
     */
    public FindByKey createFindByKey() {
        return new FindByKey();
    }

    /**
     * Create an instance of {@link GetResponse }
     * 
     */
    public GetResponse createGetResponse() {
        return new GetResponse();
    }

    /**
     * Create an instance of {@link FindByKeyResponse }
     * 
     */
    public FindByKeyResponse createFindByKeyResponse() {
        return new FindByKeyResponse();
    }

    /**
     * Create an instance of {@link Get }
     * 
     */
    public Get createGet() {
        return new Get();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByKey }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.ramonfacchin.ufsc.br/", name = "findByKey")
    public JAXBElement<FindByKey> createFindByKey(FindByKey value) {
        return new JAXBElement<FindByKey>(_FindByKey_QNAME, FindByKey.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.ramonfacchin.ufsc.br/", name = "getResponse")
    public JAXBElement<GetResponse> createGetResponse(GetResponse value) {
        return new JAXBElement<GetResponse>(_GetResponse_QNAME, GetResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindByKeyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.ramonfacchin.ufsc.br/", name = "findByKeyResponse")
    public JAXBElement<FindByKeyResponse> createFindByKeyResponse(FindByKeyResponse value) {
        return new JAXBElement<FindByKeyResponse>(_FindByKeyResponse_QNAME, FindByKeyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Get }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.ramonfacchin.ufsc.br/", name = "get")
    public JAXBElement<Get> createGet(Get value) {
        return new JAXBElement<Get>(_Get_QNAME, Get.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatabaseConfiguration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://impl.service.ramonfacchin.ufsc.br/", name = "databaseConfiguration")
    public JAXBElement<DatabaseConfiguration> createDatabaseConfiguration(DatabaseConfiguration value) {
        return new JAXBElement<DatabaseConfiguration>(_DatabaseConfiguration_QNAME, DatabaseConfiguration.class, null, value);
    }

}
