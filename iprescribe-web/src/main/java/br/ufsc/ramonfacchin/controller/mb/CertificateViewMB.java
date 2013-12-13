package br.ufsc.ramonfacchin.controller.mb;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufsc.ramonfacchin.common.utils.IPrescribeDateUtils;
import br.ufsc.ramonfacchin.controller.mb.util.BaseConversationMB;
import br.ufsc.ramonfacchin.controller.mb.util.UrlMB;
import br.ufsc.ramonfacchin.entity.MedicalCertificateRegistry;
import br.ufsc.ramonfacchin.service.IMedicalCertificateServiceLocal;
import br.ufsc.ramonfacchin.vo.MedicalCertificateVO;

@ConversationScoped
@Named("certificateViewMB")
public class CertificateViewMB extends BaseConversationMB {

	private static final long serialVersionUID = 4681264275758167826L;
	
	@Inject
	UrlMB urlMB;
	
	@EJB
	IMedicalCertificateServiceLocal medicalCertificateService;
	
	private MedicalCertificateRegistry certificateRegistry;
	
	@PostConstruct
	public void init() {
	}
	
	public String viewCertificate(Long certificateId) {
		String url = urlMB.getViewUserCertificate(1);
		setCertificateRegistry(medicalCertificateService.findRegistryByCertificateId(certificateId));
		return url;
	}

	public MedicalCertificateRegistry getCertificateRegistry() {
		return certificateRegistry;
	}

	public void setCertificateRegistry(MedicalCertificateRegistry certificateRegistry) {
		this.certificateRegistry = certificateRegistry;
	}
	
	public String downloadPdf() {
	    ByteArrayOutputStream outputStream = medicalCertificateService.generatePDFForMedicalCertificateVO(new MedicalCertificateVO(getCertificateRegistry().getMedicalCertificate()));
	    String filename = getCertificateRegistry().getMedicalCertificate().getCertified().getName().concat("-").concat(IPrescribeDateUtils.formatDate("dd-MM-yyyy", new Date()).concat(".pdf"));
		return downloadPdf(filename, outputStream, null);
	}

}
