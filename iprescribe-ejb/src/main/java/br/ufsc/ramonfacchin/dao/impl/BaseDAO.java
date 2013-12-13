package br.ufsc.ramonfacchin.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.Table;

import br.ufsc.ramonfacchin.dao.IDAO;
import br.ufsc.ramonfacchin.entity.base.BaseEntity;

/**
 * Basic implementation for DAO.
 * Every DAO should extend this class and implement {@link IDAO}.
 * 
 * @author Ramon Facchin
 * @version $Id: $
 * @since 0.0.0.0
 *
 * @param <T>
 */
public abstract class BaseDAO<T extends BaseEntity> implements IDAO<T>{
	
	private static final long serialVersionUID = 2320839949115798256L;

	@PersistenceContext(type=PersistenceContextType.TRANSACTION)
	EntityManager entityManager;
	
	@Override
	public T findById(Long id) {
		return entityManager.find(getPersistentClass(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listAll() {
		Query query = entityManager.createQuery("select obj from " + getPersistentClass().getSimpleName() + " obj order by obj.id");
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> list(int maxResults, int firstResultIndex, Boolean listOnlyActive) {
		Query query;
		if (listOnlyActive != null) {
			query = entityManager.createQuery("select obj from " + getPersistentClass().getSimpleName() + " obj where obj.active = :active_arg order by obj.id");
			query.setParameter("active_arg", listOnlyActive);
		} else {
			query = entityManager.createQuery("select obj from " + getPersistentClass().getSimpleName() + " obj order by obj.id");
		}
		query.setMaxResults(maxResults);
		query.setFirstResult(firstResultIndex);
		return query.getResultList();
	}
	
	@Override
	public Long listCount(Boolean listOnlyActive) {
		Query query;
		if (listOnlyActive != null) {
			query = entityManager.createQuery("select count(obj.id) from " + getPersistentClass().getSimpleName() + " obj where obj.active = :active_arg");
			query.setParameter("active_arg", listOnlyActive);
		} else {
			query = entityManager.createQuery("select count(obj.id) from " + getPersistentClass().getSimpleName() + " obj");
		}
		query.setMaxResults(1);
		Object count = query.getSingleResult();
		return (Long) count;
	}

	@Override
	public void update(T entity) {
		entityManager.merge(entity);
	}

	@Override
	public void persist(T entity) {
		entityManager.persist(entity);
	}

	@Override
	public void remove(T entity) {
		entityManager.remove(entity);
	}

	@Override
	public String getTableName() {
		Table tableAnnotation = getPersistentClass().getAnnotation(Table.class);
		return tableAnnotation.name();
	}

	@Override
	public String getSchemaName() {
		Table tableAnnotation = getPersistentClass().getAnnotation(Table.class);
		return tableAnnotation.schema();
	}
	
	protected abstract Class<T> getPersistentClass();

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listActive() {
		Query query = entityManager.createQuery("select obj from " + getPersistentClass().getSimpleName() + " obj where obj.active = :active_arg order by obj.id");
		return query.setParameter("active_arg", true).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listInactive() {
		Query query = entityManager.createQuery("select obj from " + getPersistentClass().getSimpleName() + " obj where obj.active = :active_arg order by obj.id");
		return query.setParameter("active_arg", false).getResultList();
	}
	
}