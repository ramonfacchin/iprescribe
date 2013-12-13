package br.ufsc.ramonfacchin.service;

import javax.ejb.Local;

import br.ufsc.ramonfacchin.common.annotation.DefaultBeanName;
import br.ufsc.ramonfacchin.entity.DatabaseConfiguration;

@Local
@DefaultBeanName("DatabaseConfigurationService")
public interface IDatabaseConfigurationServiceLocal extends IServiceLocal<DatabaseConfiguration> {
	
	/**
	 * Find the {@link DatabaseConfiguration} contanining informed key.
	 * @param key
	 * @return 
	 */
	DatabaseConfiguration findByKey(String key);
	
	/**
	 * 
	 * @param key
	 * @return The value found for the {@link DatabaseConfiguration} with the given key. Returns <code>null</code> if that configuration doesn't exist.
	 */
	String get(String key);

}
