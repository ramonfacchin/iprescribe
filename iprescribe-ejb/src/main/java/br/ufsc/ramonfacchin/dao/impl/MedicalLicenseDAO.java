package br.ufsc.ramonfacchin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ufsc.ramonfacchin.dao.IMedicalLicenseDAO;
import br.ufsc.ramonfacchin.entity.MedicalLicense;

@Stateless
public class MedicalLicenseDAO extends BaseDAO<MedicalLicense> implements IMedicalLicenseDAO {

	private static final long serialVersionUID = -7323351983390193882L;

	@SuppressWarnings("unchecked")
	@Override
	public List<MedicalLicense> listByUsername(String username) {
		String namedQuery = "MedicalLicense.listByUsername";
		Query query = entityManager.createNamedQuery(namedQuery);
		query.setParameter("username_arg", username);
		List<MedicalLicense> resultList = query.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MedicalLicense> listByUserId(Long userId) {
		String namedQuery = "MedicalLicense.listByUserId";
		Query query = entityManager.createNamedQuery(namedQuery);
		query.setParameter("userid_arg", userId);
		List<MedicalLicense> resultList = query.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MedicalLicense findByProfessionalId(String professionalId) {
		String namedQuery = "MedicalLicense.findByProfessionalId";
		Query query = entityManager.createNamedQuery(namedQuery);
		query.setParameter("professionalid_arg", professionalId);
		List<MedicalLicense> resultList = query.getResultList();
		if (resultList != null && !resultList.isEmpty()) {
			return resultList.get(0);
		}
		return null;
	}

	@Override
	protected Class<MedicalLicense> getPersistentClass() {
		return MedicalLicense.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MedicalLicense> listNotVerified() {
		String namedQuery = "MedicalLicense.listNotVerified";
		Query query = entityManager.createNamedQuery(namedQuery);
		List<MedicalLicense> resultList = query.getResultList();
		return resultList;
	}

}
