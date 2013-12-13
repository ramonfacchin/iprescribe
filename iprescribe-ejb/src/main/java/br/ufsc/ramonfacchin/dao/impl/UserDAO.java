package br.ufsc.ramonfacchin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ufsc.ramonfacchin.common.utils.IPrescribeStringUtils;
import br.ufsc.ramonfacchin.dao.IUserDAO;
import br.ufsc.ramonfacchin.entity.User;

@Stateless
public class UserDAO extends BaseDAO<User> implements IUserDAO {

	private static final long serialVersionUID = -6598077822437835273L;

	@SuppressWarnings("unchecked")
	@Override
	public User findByUsername(String username) {
		Query query = entityManager.createNamedQuery("User.findByUsername");
		query.setParameter("username_arg", username);
		List<User> resultList = query.getResultList();
		if ((resultList != null) && !resultList.isEmpty()) {
			return resultList.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public User findByCitizenRegistry(String citizenRegistry) {
		Query query = entityManager.createNamedQuery("User.findByCitizenRegistry");
		query.setParameter("citizenregistry_arg", citizenRegistry);
		List<User> resultList = query.getResultList();
		if ((resultList != null) && !resultList.isEmpty()) {
			return resultList.get(0);
		}
		return null;
	}

	@Override
	protected Class<User> getPersistentClass() {
		return User.class;
	}

	@Override
	public void persist(User entity) {
		treatPasswordAndUsername(entity);
		super.persist(entity);
	}

	private void treatPasswordAndUsername(User entity) {
		if (entity != null) {
			if (IPrescribeStringUtils.isNotBlank(entity.getPassword())) {
				entity.setPassword(IPrescribeStringUtils.encryptPassword(entity.getPassword()));
			}
			if (IPrescribeStringUtils.isNotBlank(entity.getUsername())) {
				entity.setUsername(entity.getUsername().toLowerCase());
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findByEmail(String email) {
		Query query = entityManager.createNamedQuery("User.findByEmail");
		query.setParameter("email_arg", email);
		List<User> resultList = query.getResultList();
		if ((resultList != null) && !resultList.isEmpty()) {
			return resultList.get(0);
		}
		return null;
	}

}
