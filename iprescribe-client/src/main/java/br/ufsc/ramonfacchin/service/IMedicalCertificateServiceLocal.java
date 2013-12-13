package br.ufsc.ramonfacchin.service;

import javax.ejb.Local;

import br.ufsc.ramonfacchin.common.annotation.DefaultBeanName;
import br.ufsc.ramonfacchin.entity.MedicalCertificate;

@Local
@DefaultBeanName("MedicalCertificateService")
public interface IMedicalCertificateServiceLocal extends IMedicalCertificateService,IServiceLocal<MedicalCertificate> {
	
}
