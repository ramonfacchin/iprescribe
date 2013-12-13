package br.ufsc.ramonfacchin.dao;

import java.util.List;

import br.ufsc.ramonfacchin.entity.MedicalLicense;

public interface IMedicalLicenseDAO extends IDAO<MedicalLicense> {
	
	List<MedicalLicense> listByUsername(String username);
	
	List<MedicalLicense> listByUserId(Long userId);
	
	List<MedicalLicense> listNotVerified();
	
	MedicalLicense findByProfessionalId(String professionalId);

}
