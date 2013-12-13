package br.ufsc.ramonfacchin.service;


import java.io.Serializable;
import java.rmi.Remote;
import java.util.List;

import br.ufsc.ramonfacchin.common.annotation.DefaultBeanName;
import br.ufsc.ramonfacchin.entity.base.BaseEntity;

/**
 * Interface defining methods every Service Bean should have. <br>
 * 
 * EVERY Remote EJB interface that inherits this interface must be annotated with {@link DefaultBeanName}
 * 
 * @author Ramon Facchin
 * @version $Id: $
 * @since 0.0.0.0
 * 
 * @param <T>
 */
public interface IService<T extends BaseEntity> extends Remote,Serializable {

	/**
	 * Finds a single object with the specified id.
	 * 
	 * @param id
	 *            Primary Key.
	 * @return the object found in database or <code>null</code>, if there isn't any object with the specified id.
	 */
	T findById(Long id);

	/**
	 * Count objects of the given {@link Class}.
	 * 
	 * @param listOnlyActive <code>true</code> shows only active objects, <code>false</code> shows only inactive objects and <code>null</code> shows both.
	 * @return
	 */
	Long listCount(Boolean listOnlyActive);

	/**
	 * Lists objects of the given {@link Class}.
	 * 
	 * @param maxResults limits the number of objects returned.
	 * @param firstResultIndex object list offset.
	 * @param listOnlyActive <code>true</code> shows only active objects, <code>false</code> shows only inactive objects and <code>null</code> shows both.
	 * @return
	 */
	List<T> list(int maxResults, int firstResultIndex, Boolean listOnlyActive);

	/**
	 * Lists every object of the given {@link Class}.
	 * 
	 * @return
	 */
	List<T> listAll();

	/**
	 * Lists objects of the given {@link Class} with the column ACTIVE == <code>true</code>.
	 * 
	 * @return
	 */
	List<T> listActive();

	/**
	 * Lists objects of the given {@link Class} with the column ACTIVE == <code>false</code>.
	 * 
	 * @return
	 */
	List<T> listInactive();
}
