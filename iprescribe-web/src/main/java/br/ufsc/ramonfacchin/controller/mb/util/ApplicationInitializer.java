/**
 * 
 */
package br.ufsc.ramonfacchin.controller.mb.util;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * TODO Descrição da classe.
 *
 * <h3>Bry Tecnologia - 2013</h3>
 * 
 * @author TODO Nome / TODO Email
 * @since Aug 20, 2013
 *
 */
@WebListener("applicationInitializer")
public class ApplicationInitializer implements ServletContextListener {
	
	//private Factory<SecurityManager> factory;
	
	//private SecurityManager securityManager;
	
	@PostConstruct
	private void initialize() {
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		//factory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//securityManager = factory.getInstance();
		//SecurityUtils.setSecurityManager(securityManager);
	}
	
}
