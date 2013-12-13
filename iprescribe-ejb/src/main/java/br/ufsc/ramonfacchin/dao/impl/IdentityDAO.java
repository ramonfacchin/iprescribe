package br.ufsc.ramonfacchin.dao.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ufsc.ramonfacchin.dao.IIdentityDAO;
import br.ufsc.ramonfacchin.entity.Identity;

@Stateless
public class IdentityDAO extends BaseDAO<Identity> implements IIdentityDAO {

	private static final long serialVersionUID = -3329332326823852401L;

	@SuppressWarnings("unchecked")
	@Override
	public List<Identity> findByName(String name) {
		Query query = entityManager.createNamedQuery("Identity.findByName");
		name = "%".concat(name.toUpperCase()).concat("%");
		query.setParameter("name_arg", name);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Identity> findByBirth(Date birth) {
		Query query = entityManager.createNamedQuery("Identity.findByBirth");
		query.setParameter("birth_arg", birth);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Identity> findByNameAndBirth(String name, Date birth) {
		Query query = entityManager.createNamedQuery("Identity.findByNameAndBirth");
		name = "%".concat(name.toUpperCase()).concat("%");
		query.setParameter("name_arg", name);
		query.setParameter("birth_arg", birth);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Identity findByUsername(String username) {
		Query query = entityManager.createNamedQuery("Identity.findByUsername");
		query.setParameter("username_arg", username);
		List<Identity> list = query.getResultList();
		return (list != null) && !list.isEmpty() ? list.get(0) : null;
	}

	@Override
	protected Class<Identity> getPersistentClass() {
		return Identity.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Identity findByCitizenRegistry(String citizenRegistry) {
		Query query = entityManager.createNamedQuery("Identity.findByCitizenRegistry");
		query.setParameter("citizenregistry_arg", citizenRegistry);
		List<Identity> list = query.getResultList();
		return (list != null) && !list.isEmpty() ? list.get(0) : null;
	}

}
