package br.ufsc.ramonfacchin.service;

import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.ejb.Remote;

import br.ufsc.ramonfacchin.common.annotation.DefaultBeanName;
import br.ufsc.ramonfacchin.entity.MedicalCertificate;
import br.ufsc.ramonfacchin.entity.MedicalCertificateRegistry;
import br.ufsc.ramonfacchin.vo.MedicalCertificateVO;

@Remote
@DefaultBeanName("MedicalCertificateService")
public interface IMedicalCertificateService extends IService<MedicalCertificate> {
	
	List<MedicalCertificate> listByIssuerId(Long issuerId, boolean validOnly);
	
	List<MedicalCertificate> listByIssuerCitizenRegistry(String issuerCitizenRegistry, boolean validOnly);
	
	List<MedicalCertificate> listByCertifiedId(Long certifiedId, boolean validOnly);
	
	List<MedicalCertificate> listByCertifiedCitizenRegistry(String certifiedCitizenRegistry, boolean validOnly);
	
	MedicalCertificate findByHash(String hash);
	
	MedicalCertificateRegistry findRegistryByCertificateId(Long medicalCertificateId);
	
	MedicalCertificateVO findVOByHash(String hash);

	ByteArrayOutputStream generatePDFForMedicalCertificateVO(MedicalCertificateVO certificateVO);
}
