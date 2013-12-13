package br.ufsc.ramonfacchin.service.util.test;

import javax.validation.ValidationException;

import junit.framework.Assert;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Ignore;
import org.junit.Test;

import br.ufsc.ramonfacchin.entity.DatabaseConfiguration;
import br.ufsc.ramonfacchin.entity.base.BaseEntity;
import br.ufsc.ramonfacchin.service.IDatabaseConfigurationService;
import br.ufsc.ramonfacchin.service.IDatabaseConfigurationServiceLocal;
import br.ufsc.ramonfacchin.service.ISampleAuthenticationService;
import br.ufsc.ramonfacchin.service.IService;
import br.ufsc.ramonfacchin.service.IServiceLocal;
import br.ufsc.ramonfacchin.service.util.ServiceLocator;

/**
 * To run this test you should remove the {@link Ignore} annotation from the
 * methods, check 'service-configuration.properties' file inside source folder
 * to adjust the jndi lookup parameters, and your Application Server should be
 * started with your EAR/WAR published.
 * 
 * @author ramon
 * @version $Id: $
 * @since 0.0.0.0
 * 
 */
//@RunWith(Arquillian.class)
public class ServiceLocatorTest {

	//@Deployment
	public static JavaArchive deployment() {
		JavaArchive javaArchive = ShrinkWrap.create(JavaArchive.class).addClasses(ServiceLocator.class,IService.class,IServiceLocal.class,IDatabaseConfigurationService.class,IDatabaseConfigurationServiceLocal.class,DatabaseConfiguration.class,BaseEntity.class).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
		System.out.println(javaArchive.toString(true));
		return javaArchive;
	}

	@Ignore
	@Test
	public void testLookupDatabaseConfigurationService() throws Exception {
		IService<DatabaseConfiguration> service = (IDatabaseConfigurationService) ServiceLocator.getInstance().getService(IDatabaseConfigurationService.class);
		Assert.assertNotNull(service);
		boolean canRun = true;
		try {
			service.listAll();
		} catch (Exception e) {
			canRun = false;
		}
		Assert.assertTrue(canRun);
	}

	@Ignore
	@Test
	public void testLookupSampleAuthenticationService() throws Exception {
		ISampleAuthenticationService service = (ISampleAuthenticationService) ServiceLocator.getInstance().getService(ISampleAuthenticationService.class);
		Assert.assertNotNull(service);
		boolean canRun = true;
		try {
			service.authenticate("master", "Pudim@2012");
		} catch (Exception e) {
			if (!(e instanceof ValidationException)) {
				canRun = false;
			}
		}
		Assert.assertTrue(canRun);
	}

}
