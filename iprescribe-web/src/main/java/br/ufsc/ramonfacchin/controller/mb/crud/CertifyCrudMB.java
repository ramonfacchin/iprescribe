package br.ufsc.ramonfacchin.controller.mb.crud;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufsc.ramonfacchin.common.utils.IPrescribeDateUtils;
import br.ufsc.ramonfacchin.common.utils.IPrescribeStringUtils;
import br.ufsc.ramonfacchin.controller.mb.login.AuthenticationMB;
import br.ufsc.ramonfacchin.controller.mb.util.BaseCrudMB;
import br.ufsc.ramonfacchin.controller.mb.util.UrlMB;
import br.ufsc.ramonfacchin.entity.Identity;
import br.ufsc.ramonfacchin.entity.MedicalCertificate;
import br.ufsc.ramonfacchin.entity.MedicalCertificateRegistry;
import br.ufsc.ramonfacchin.entity.MedicalLicense;
import br.ufsc.ramonfacchin.service.IIdentityServiceLocal;
import br.ufsc.ramonfacchin.service.IMedicalCertificateServiceLocal;
import br.ufsc.ramonfacchin.service.IMedicalLicenseServiceLocal;
import br.ufsc.ramonfacchin.vo.MedicalCertificateVO;

@ConversationScoped
@Named("certifyCrudMB")
public class CertifyCrudMB extends BaseCrudMB<MedicalCertificate> {

	private static final long serialVersionUID = 7786263635679033682L;
	
	@Inject
	AuthenticationMB authenticationMB;
	
	@Inject
	UrlMB urlMB;
	
	@EJB
	IMedicalLicenseServiceLocal medicalLicenseService;
	
	@EJB
	IIdentityServiceLocal identityService;
	
	@EJB
	IMedicalCertificateServiceLocal medicalCertificateService;
	
	List<MedicalLicense> userLicenses;
	
	private MedicalCertificateRegistry registry;
	
	@PostConstruct
	public void init() {
		//beginConversationIfTransient();
		setEntityForm(new MedicalCertificate());
		getEntityForm().setCertified(new Identity());
	}
	
	public List<MedicalLicense> listUserLicenses() {
		if (userLicenses == null && authenticationMB.isAuthenticated()) {
			userLicenses = medicalLicenseService.listByUsername(authenticationMB.getLoggedUser().getUsername());
			if (userLicenses == null || userLicenses.isEmpty()) {
				addFacesMessage(FacesMessage.SEVERITY_WARN, null, "medicaldoctor.certify.error.nolicense.summary", "medicaldoctor.certify.error.nolicense.detail");
			}
		}
		return userLicenses;
	}
	
	@Override
	protected void doBeforeCreate() {
		if (!isMock()) {
			identityService.save(getEntityForm().getCertified());
		}
		getEntityForm().setIssued(new Date());
		super.doBeforeCreate();
	}
	
	@Override
	protected void doAfterCreate() {
		// TODO Auto-generated method stub
		super.doAfterCreate();
		setRegistry(medicalCertificateService.findRegistryByCertificateId(getEntityForm().getId()));
	}
	
	@Override
	protected IMedicalCertificateServiceLocal getService() {
		return medicalCertificateService;
	}

	@Override
	protected String getCreateURL() {
		return urlMB.getViewCertificate();
	}

	@Override
	protected String getReadURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getUpdateURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getDeleteURL() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void findByCitizenRegistry(AjaxBehaviorEvent event) {
		String citizenRegistryArg = getEntityForm().getCertified().getCitizenRegistry();
		//CPFValidator cpfValidator = new CPFValidator();
		//boolean valid = cpfValidator.isValid(citizenRegistryArg, null);
		
		boolean valid = IPrescribeStringUtils.removerLettersAndSpecialChars(citizenRegistryArg).length() == 11;
		
		if (valid) {
			Identity identity = identityService.findByCitizenRegistry(citizenRegistryArg);
			if (identity == null) {
				addFacesMessage(FacesMessage.SEVERITY_WARN, "certify:citizenregistry", "medicaldoctor.certify.event.citizenregistry.notfound.summary", "medicaldoctor.certify.event.citizenregistry.notfound.detail");
			} else {
				getEntityForm().setCertified(identity);
			}
		} else {
			getEntityForm().getCertified().setCitizenRegistry(null);
			addFacesMessage(FacesMessage.SEVERITY_ERROR, "certify:citizenregistry", "medicaldoctor.certify.event.citizenregistry.invalid.summary", "medicaldoctor.certify.event.citizenregistry.invalid.detail");
		}
	}
	
	public void addFacesMessage(Severity severity, String clientId, String summaryI18n, String detailI18n) {
		FacesMessage fm = new FacesMessage(severity, summaryI18n, detailI18n);
		FacesContext.getCurrentInstance().addMessage(clientId, fm);
	}

	public MedicalCertificateRegistry getRegistry() {
		return registry;
	}

	public void setRegistry(MedicalCertificateRegistry registry) {
		this.registry = registry;
	}
	
	public String downloadPdf() {
	    ByteArrayOutputStream outputStream = medicalCertificateService.generatePDFForMedicalCertificateVO(new MedicalCertificateVO(getEntityForm()));
	    String filename = getEntityForm().getCertified().getName().concat("-").concat(IPrescribeDateUtils.formatDate("dd-MM-yyyy", new Date()).concat(".pdf"));
		return downloadPdf(filename, outputStream, null);
	}
}
