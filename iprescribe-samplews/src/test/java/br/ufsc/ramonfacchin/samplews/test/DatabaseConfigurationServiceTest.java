package br.ufsc.ramonfacchin.samplews.test;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import br.com.bry.samplews.generated.DatabaseConfiguration;
import br.com.bry.samplews.generated.DatabaseConfigurationService;
import br.com.bry.samplews.generated.DatabaseConfigurationServiceService;

/**
 * Test for the DatabaseConfiguration Web Service.
 * 
 * Uncomment the {@link Ignore} annotation to run the tests.
 * 
 * REMEMBER: To execute the test, you must, first, review your pom.xml,
 * then build the project with the profile generate-ws-stubs, then start
 * your Application Server with the Web Service deployed. Otherwise this
 * test will not work. 
 * 
 * <h3>Copyright: 2013, BRy Tecnologia S/A</h3> <br>
 * 
 * @author ramon
 * @version $Id: $
 * @since 0.0.0.0
 *
 */
public class DatabaseConfigurationServiceTest {
	
	@Ignore
	@Test
	public void testDatabaseConfigurationService() throws Exception {
		DatabaseConfigurationServiceService ws = new DatabaseConfigurationServiceService();
		DatabaseConfigurationService port = ws.getDatabaseConfigurationServicePort();
		DatabaseConfiguration databaseConfiguration = port.findByKey("key.1");
		String key2value = port.get("key.2");
		
		Assert.assertNotNull(databaseConfiguration);
		Assert.assertEquals(databaseConfiguration.getValue(), "value.1");
		Assert.assertEquals(key2value, "value.2");
	}

}
