package br.ufsc.ramonfacchin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ufsc.ramonfacchin.dao.IDatabaseConfigurationDAO;
import br.ufsc.ramonfacchin.entity.DatabaseConfiguration;

@Stateless
public class DatabaseConfigurationDAO extends BaseDAO<DatabaseConfiguration> implements IDatabaseConfigurationDAO {

	private static final long serialVersionUID = 1689405846821129951L;

	@Override
	protected Class<DatabaseConfiguration> getPersistentClass() {
		return DatabaseConfiguration.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DatabaseConfiguration findByKey(String key) {
		Query query = entityManager.createQuery("select dbc from "+getPersistentClass().getSimpleName()+" dbc where dbc.key = :key_arg");
		query.setParameter("key_arg", key);
		query.setMaxResults(1);
		List<DatabaseConfiguration> resultList = query.getResultList();
		if (resultList != null && !resultList.isEmpty()) {
			return resultList.get(0);
		}
		return null;
	}

	@Override
	public String get(String key) {
		DatabaseConfiguration dbc = findByKey(key);
		return dbc == null ? null : dbc.getValue();
	}

}
