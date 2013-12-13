package br.ufsc.ramonfacchin.service;

import java.util.List;

import javax.ejb.Remote;

import br.ufsc.ramonfacchin.common.annotation.DefaultBeanName;
import br.ufsc.ramonfacchin.entity.MedicalLicense;

@Remote
@DefaultBeanName("MedicalLicenseService")
public interface IMedicalLicenseService extends IService<MedicalLicense> {
	
	List<MedicalLicense> listByUsername(String username);
	
	List<MedicalLicense> listByUserId(Long userId);
	
	List<MedicalLicense> listNotVerified();
	
	MedicalLicense findByProfessionalId(String professionalId);

}
