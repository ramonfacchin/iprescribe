package br.ufsc.ramonfacchin.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ufsc.ramonfacchin.dao.IMedicalCertificateRegistryDAO;
import br.ufsc.ramonfacchin.entity.MedicalCertificateRegistry;

@Stateless
public class MedicalCertificateRegistryDAO extends BaseDAO<MedicalCertificateRegistry> implements IMedicalCertificateRegistryDAO {

	private static final long serialVersionUID = -8578300201877518568L;

	@Override
	protected Class<MedicalCertificateRegistry> getPersistentClass() {
		return MedicalCertificateRegistry.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MedicalCertificateRegistry findByHash(String hash) {
		String namedQuery = "MedicalCertificateRegistry.findByHash";
		Query query = entityManager.createNamedQuery(namedQuery);
		query.setParameter("hash_arg", hash);
		List<MedicalCertificateRegistry> resultList = query.getResultList();
		if (resultList != null && !resultList.isEmpty()) {
			return resultList.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public MedicalCertificateRegistry findByCertificateId(Long certificateId) {
		String namedQuery = "MedicalCertificateRegistry.findByCertificateId";
		Query query = entityManager.createNamedQuery(namedQuery);
		query.setParameter("certificateid_arg", certificateId);
		List<MedicalCertificateRegistry> resultList = query.getResultList();
		if (resultList != null && !resultList.isEmpty()) {
			return resultList.get(0);
		}
		return null;
	}

}
