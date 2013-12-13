package br.ufsc.ramonfacchin.service;

import javax.ejb.Remote;

import br.ufsc.ramonfacchin.common.annotation.DefaultBeanName;
import br.ufsc.ramonfacchin.entity.DatabaseConfiguration;

/**
 * Sample Remote interface for EJB.
 * 
 * Notice that EVERY Remote EJB interface must be annotated with
 * {@link DefaultBeanName}
 * 
 * @author ramon
 * @version $Id: $
 * @since 0.0.0.0
 * 
 */
@DefaultBeanName("DatabaseConfigurationService")
@Remote
public interface IDatabaseConfigurationService extends IService<DatabaseConfiguration> {

	/**
	 * Find the {@link DatabaseConfiguration} contanining informed key.
	 * 
	 * @param key
	 * @return
	 */
	DatabaseConfiguration findByKey(String key);

	/**
	 * 
	 * @param key
	 * @return The value found for the {@link DatabaseConfiguration} with the
	 *         given key. Returns <code>null</code> if that configuration
	 *         doesn't exist.
	 */
	String get(String key);

}
