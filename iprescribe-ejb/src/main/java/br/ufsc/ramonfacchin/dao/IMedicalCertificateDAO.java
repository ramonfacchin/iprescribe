package br.ufsc.ramonfacchin.dao;

import java.util.List;

import br.ufsc.ramonfacchin.entity.MedicalCertificate;

public interface IMedicalCertificateDAO extends IDAO<MedicalCertificate> {
	
	List<MedicalCertificate> listByIssuerId(Long issuerId, boolean validOnly);
	
	List<MedicalCertificate> listByIssuerCitizenRegistry(String issuerCitizenRegistry, boolean validOnly);
	
	List<MedicalCertificate> listByCertifiedId(Long certifiedId, boolean validOnly);
	
	List<MedicalCertificate> listByCertifiedCitizenRegistry(String certifiedCitizenRegistry, boolean validOnly);

}
