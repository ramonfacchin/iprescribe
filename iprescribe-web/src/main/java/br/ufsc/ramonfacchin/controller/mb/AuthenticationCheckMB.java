package br.ufsc.ramonfacchin.controller.mb;

import java.io.ByteArrayOutputStream;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufsc.ramonfacchin.common.utils.IPrescribeDateUtils;
import br.ufsc.ramonfacchin.controller.mb.util.BaseConversationMB;
import br.ufsc.ramonfacchin.controller.mb.util.UrlMB;
import br.ufsc.ramonfacchin.entity.MedicalCertificate;
import br.ufsc.ramonfacchin.service.IMedicalCertificateServiceLocal;
import br.ufsc.ramonfacchin.vo.MedicalCertificateVO;

@ConversationScoped
@Named("authenticationCheckMB")
public class AuthenticationCheckMB extends BaseConversationMB {

	private static final long serialVersionUID = 4681264275758167826L;
	
	@Inject
	UrlMB urlMB;
	
	@EJB
	IMedicalCertificateServiceLocal medicalCertificateService;

	private String hash;
	
	@PostConstruct
	public void init() {
	}
	
	public String downloadPdf() {
		MedicalCertificate certificate = medicalCertificateService.findByHash(getHash());
		if (certificate != null) {
			ByteArrayOutputStream outputStream = medicalCertificateService.generatePDFForMedicalCertificateVO(new MedicalCertificateVO(certificate));
			String filename = certificate.getCertified().getName().concat("-").concat(IPrescribeDateUtils.formatDate("dd-MM-yyyy", new Date()).concat(".pdf"));
			return downloadPdf(filename, outputStream, null);
		} else {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, getI18nMessage("user.authcheck.error.hashnotfound.summary"), getI18nMessage("user.authcheck.error.hashnotfound.summary"));
			FacesContext.getCurrentInstance().addMessage(null, msg);
			return null;
		}
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

}
