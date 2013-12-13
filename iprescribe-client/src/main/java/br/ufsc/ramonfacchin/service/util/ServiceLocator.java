package br.ufsc.ramonfacchin.service.util;

import java.io.InputStream;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.commons.lang.StringUtils;

import br.ufsc.ramonfacchin.common.annotation.DefaultBeanName;
import br.ufsc.ramonfacchin.common.exception.ServiceLocationException;
import br.ufsc.ramonfacchin.entity.base.BaseEntity;
import br.ufsc.ramonfacchin.service.IService;

/**
 * A Service Locator example for looking up Remote EJBs.
 * This is a sample and, for using it in production, 
 * refactoring of the class name is recommended.
 * 
 * A good approach is to rename this class to [ApplicationClassifier]ServiceLocator
 * 
 * @author ramon
 * @version $Id: $
 * @since 0.0.0.0
 *
 */
public class ServiceLocator {

	public static final String APP_NAME_KEY = "path.appname";
	public static final String MODULE_NAME_KEY = "path.modulename";
	public static final String DISTINCT_NAME_KEY = "path.distinctname";
	
	private static ServiceLocator instance;

	private ServiceLocator() {
	}

	public static ServiceLocator getInstance() {
		if (instance == null) {
			instance = new ServiceLocator();
		}
		return instance;
	}

	/**
	 * Retrieves EJB instance based on the given interface class.
	 * @param interfaceClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends IService<? extends BaseEntity>> IService<? extends BaseEntity> getService(Class<T> interfaceClass) throws ServiceLocationException {
		T iService = null;
		Context ctx = null;
		
		try {
			Properties jndiProperties = getJNDIProperties();
			
			String appName = getAppName(interfaceClass, jndiProperties);
			String moduleName = getModuleName(interfaceClass, jndiProperties);
			String distinctName = getDistinctName(interfaceClass, jndiProperties);
			boolean hasAppName = StringUtils.isNotBlank(appName);
			boolean hasDistinctName = StringUtils.isNotBlank(distinctName);

			StringBuilder lookupPathBuilder = new StringBuilder();
			//lookupPathBuilder.append("java:global/");
			//If the EJB is not inside an EAR or WAR application, appName should be omitted
			lookupPathBuilder.append(hasAppName ? appName+"/" : "");
			lookupPathBuilder.append(moduleName+"/");
			//If the EJB doesn't have a distinct name, distinctName should be omitted
			lookupPathBuilder.append(hasDistinctName ? distinctName+"/" : "");
			lookupPathBuilder.append(getBeanName(interfaceClass, jndiProperties));
			lookupPathBuilder.append(getViewClassName(interfaceClass));
			
			ctx = new InitialContext(jndiProperties);
			
			iService = (T) ctx.lookup(lookupPathBuilder.toString());
		} catch (ServiceLocationException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace(); //Replacing this line with a Logger call is recommended
			throw new ServiceLocationException("No EJB found matching given Interface.");
		}
		return iService;
	}

	/**
	 * <b>NOTE:</b> User this method for looking up EJB when EJB client and the EJB service both run on the same JVM.
	 * Retrieves EJB instance based on the given interface class.
	 * @param interfaceClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends IService<? extends BaseEntity>> IService<? extends BaseEntity> getLocalService(Class<T> interfaceClass) throws ServiceLocationException {
		T iService = null;
		Context ctx = null;
		
		try {
			Properties jndiProperties = getJNDIProperties();
			
			String appName = getAppName(interfaceClass, jndiProperties);
			String moduleName = getModuleName(interfaceClass, jndiProperties);
			String distinctName = getDistinctName(interfaceClass, jndiProperties);
			boolean hasAppName = StringUtils.isNotBlank(appName);
			boolean hasDistinctName = StringUtils.isNotBlank(distinctName);

			StringBuilder lookupPathBuilder = new StringBuilder();
			lookupPathBuilder.append("java:global/");
			//If the EJB is not inside an EAR or WAR application, appName should be omitted
			lookupPathBuilder.append(hasAppName ? appName+"/" : "");
			lookupPathBuilder.append(moduleName+"/");
			//If the EJB doesn't have a distinct name, distinctName should be omitted
			lookupPathBuilder.append(hasDistinctName ? distinctName+"/" : "");
			lookupPathBuilder.append(getBeanName(interfaceClass, jndiProperties));
			lookupPathBuilder.append(getViewClassName(interfaceClass));
			
			ctx = new InitialContext();
			
			iService = (T) ctx.lookup(lookupPathBuilder.toString());
		} catch (ServiceLocationException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace(); //Replacing this line with a Logger call is recommended
			throw new ServiceLocationException("No EJB found matching given Interface.");
		}
		return iService;
	}

	/**
	 * Retrieve JNDI properties for EJB lookup from the service-configuration.propeties.
	 * If the file doesn't exist or any error occurs during the reading, a default configuration is retrieved.
	 */
	private Properties getJNDIProperties() {
		Properties jndiProperties = new Properties();
		try {
			InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("service-configuration.properties");
			jndiProperties.load(resourceAsStream);
		} catch (Exception e) {
			System.err.println("Erro recuperando propriedades JNDI...");
			e.printStackTrace();
			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			jndiProperties.put(Context.PROVIDER_URL, "remote://0.0.0.0:4447");
			jndiProperties.put("jboss.naming.client.ejb.context", "true");
			jndiProperties.put(Context.SECURITY_PRINCIPAL, "admin");
			jndiProperties.put(Context.SECURITY_CREDENTIALS, "Pudim@2012");
		}
		return jndiProperties;
	}
	
	/**
	 * Infers the View Class of the EJB.
	 * @param interfaceClass
	 * @return
	 */
	private <T extends IService<? extends BaseEntity>> String getViewClassName(Class<T> interfaceClass) {
		return "!"+interfaceClass.getName();
	}
	
	/**
	 * Infers the EJB app name, in the following priority:
	 * <br/>1) service-configuration.properties (global distinct name)
	 * <br/>1) service-configuration.properties (specific distinct name for the Interface given)
	 * @param interfaceClass
	 * @param properties
	 * @return
	 */
	private <T extends IService<? extends BaseEntity>> String getAppName(Class<T> interfaceClass, Properties properties) {
		String appName = properties.getProperty(interfaceClass.getName()+".appname");
		if (StringUtils.isBlank(appName)) {
			appName = properties.getProperty(APP_NAME_KEY);
		}
		return appName;
	}
	
	/**
	 * Infers the EJB module name, in the following priority:
	 * <br/>1) service-configuration.properties (global distinct name)
	 * <br/>1) service-configuration.properties (specific distinct name for the Interface given)
	 * <br/>If none of the above configurations is found, the lookup fails.
	 * @param interfaceClass
	 * @param properties
	 * @return
	 */
	private <T extends IService<? extends BaseEntity>> String getModuleName(Class<T> interfaceClass, Properties properties) {
		String moduleName = properties.getProperty(interfaceClass.getName()+".modulename");
		if (StringUtils.isBlank(moduleName)) {
			moduleName = properties.getProperty(MODULE_NAME_KEY);
		}
		return moduleName;
	}
	
	/**
	 * Infers the EJB distinct name, in the following priority:
	 * <br/>1) service-configuration.properties (global distinct name)
	 * <br/>1) service-configuration.properties (specific distinct name for the Interface given)
	 * @param interfaceClass
	 * @param properties
	 * @return
	 */
	private <T extends IService<? extends BaseEntity>> String getDistinctName(Class<T> interfaceClass, Properties properties) {
		String distinctName = properties.getProperty(interfaceClass.getName()+".distinctname");
		if (StringUtils.isBlank(distinctName)) {
			distinctName = properties.getProperty(DISTINCT_NAME_KEY);
		}
		return distinctName;
	}
	
	/**
	 * Infers the EJB name, in the following priority:
	 * <br/>1) service-configuration.properties
	 * <br/>2) DefaultBeanName annotation on interface.
	 * <br/>If none of the above configurations is found, the lookup fails.
	 * @param interfaceClass
	 * @param properties
	 * @return
	 */
	private  <T extends IService<? extends BaseEntity>> String getBeanName(Class<T> interfaceClass, Properties properties) throws ServiceLocationException {
		String beanName = properties.getProperty(interfaceClass.getName()+".beanname");
		if (StringUtils.isBlank(beanName)) {
			try {
				DefaultBeanName defaultBeanNameAnnotation = interfaceClass.getAnnotation(DefaultBeanName.class);
				beanName = defaultBeanNameAnnotation.value();
			} catch (Exception e) {
				throw new ServiceLocationException("Remote EJB Interface must be annotated with @DefaultBeanName");
			}
		}
		if (StringUtils.isBlank(beanName)) {
			throw new ServiceLocationException("Could not infer EJB name: no @DefaultBeanName annotation on the interface nor mapping found in service-configuration.properties for the given interface.");
		}
		return beanName;
	}

}
