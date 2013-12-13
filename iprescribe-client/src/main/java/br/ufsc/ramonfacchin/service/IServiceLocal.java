package br.ufsc.ramonfacchin.service;


import java.rmi.Remote;

import br.ufsc.ramonfacchin.entity.base.BaseEntity;

/**
 * Interface defining methods every Local Service Bean should have. <br>
 * 
 * @author Ramon Facchin
 * @version $Id: $
 * @since 0.0.0.0
 * 
 * @param <T>
 */
public interface IServiceLocal<T extends BaseEntity> extends IService<T>,Remote {

	/**
	 * Inserts or Updates given object in database.
	 * 
	 * @param entity
	 */
	void save(T entity);

	/**
	 * Removes given object from the database.
	 * 
	 * @param entity
	 */
	void remove(T entity);
}
