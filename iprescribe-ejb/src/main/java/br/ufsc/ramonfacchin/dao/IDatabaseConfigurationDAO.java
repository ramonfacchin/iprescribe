package br.ufsc.ramonfacchin.dao;

import br.ufsc.ramonfacchin.entity.DatabaseConfiguration;

public interface IDatabaseConfigurationDAO extends IDAO<DatabaseConfiguration> {
	
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
