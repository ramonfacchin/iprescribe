package br.ufsc.ramonfacchin.service;

import javax.ejb.Local;

import br.ufsc.ramonfacchin.common.annotation.DefaultBeanName;
import br.ufsc.ramonfacchin.entity.MedicalLicense;

@Local
@DefaultBeanName("MedicalLicenseService")
public interface IMedicalLicenseServiceLocal extends IMedicalLicenseService,IServiceLocal<MedicalLicense> {
	
}
