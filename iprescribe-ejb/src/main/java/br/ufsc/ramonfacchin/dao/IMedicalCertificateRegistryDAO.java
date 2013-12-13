package br.ufsc.ramonfacchin.dao;

import br.ufsc.ramonfacchin.entity.MedicalCertificateRegistry;

public interface IMedicalCertificateRegistryDAO extends IDAO<MedicalCertificateRegistry> {
	
	MedicalCertificateRegistry findByHash(String hash);
	
	MedicalCertificateRegistry findByCertificateId(Long certificateId);

}
