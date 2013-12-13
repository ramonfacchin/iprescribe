package br.ufsc.ramonfacchin.dao.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.Query;

import br.ufsc.ramonfacchin.dao.IMedicalCertificateDAO;
import br.ufsc.ramonfacchin.entity.MedicalCertificate;

@Stateless
public class MedicalCertificateDAO extends BaseDAO<MedicalCertificate> implements IMedicalCertificateDAO {

	private static final long serialVersionUID = -1724420471296767689L;

	@Override
	protected Class<MedicalCertificate> getPersistentClass() {
		return MedicalCertificate.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MedicalCertificate> listByIssuerId(Long issuerId, boolean validOnly) {
		String namedQuery;
		if (validOnly) {
			namedQuery = "MedicalCertificate.listByIssuerIdValidOnly";
		} else {
			namedQuery = "MedicalCertificate.listByIssuerId";
		}
		Query query = entityManager.createNamedQuery(namedQuery);
		query.setParameter("issuerid_arg", issuerId);
		if (validOnly) {
			query.setParameter("now", new Date());
		}
		List<MedicalCertificate> resultList = query.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MedicalCertificate> listByIssuerCitizenRegistry(String issuerCitizenRegistry, boolean validOnly) {
		String namedQuery;
		if (validOnly) {
			namedQuery = "MedicalCertificate.listByIssuerCitizenRegistryValidOnly";
		} else {
			namedQuery = "MedicalCertificate.listByIssuerCitizenRegistry";
		}
		Query query = entityManager.createNamedQuery(namedQuery);
		query.setParameter("issuerregistry_arg", issuerCitizenRegistry);
		if (validOnly) {
			query.setParameter("now", new Date());
		}
		List<MedicalCertificate> resultList = query.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MedicalCertificate> listByCertifiedId(Long certifiedId, boolean validOnly) {
		String namedQuery;
		if (validOnly) {
			namedQuery = "MedicalCertificate.listByCertifiedIdValidOnly";
		} else {
			namedQuery = "MedicalCertificate.listByCertifiedId";
		}
		Query query = entityManager.createNamedQuery(namedQuery);
		query.setParameter("certifiedid_arg", certifiedId);
		if (validOnly) {
			query.setParameter("now", new Date());
		}
		List<MedicalCertificate> resultList = query.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MedicalCertificate> listByCertifiedCitizenRegistry(String certifiedCitizenRegistry, boolean validOnly) {
		String namedQuery;
		if (validOnly) {
			namedQuery = "MedicalCertificate.listByCertifiedCitizenRegistryValidOnly";
		} else {
			namedQuery = "MedicalCertificate.listByCertifiedCitizenRegistry";
		}
		Query query = entityManager.createNamedQuery(namedQuery);
		query.setParameter("certifiedregistry_arg", certifiedCitizenRegistry);
		if (validOnly) {
			query.setParameter("now", new Date());
		}
		List<MedicalCertificate> resultList = query.getResultList();
		return resultList;
	}

}
