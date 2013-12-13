package br.ufsc.ramonfacchin.controller.mb.crud;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufsc.ramonfacchin.controller.mb.login.AuthenticationMB;
import br.ufsc.ramonfacchin.controller.mb.util.BaseCrudMB;
import br.ufsc.ramonfacchin.controller.mb.util.UrlMB;
import br.ufsc.ramonfacchin.entity.MedicalLicense;
import br.ufsc.ramonfacchin.service.IMedicalLicenseServiceLocal;

@Named("licenseCrudMB")
@ConversationScoped
public class LicenseCrudMB extends BaseCrudMB<MedicalLicense> {

	private static final long serialVersionUID = 2437439398070801106L;
	
	@Inject
	AuthenticationMB authenticationMB;
	
	@Inject
	UrlMB urls;
	
	@EJB
	IMedicalLicenseServiceLocal medicalLicenseService;

	@Override
	protected IMedicalLicenseServiceLocal getService() {
		return medicalLicenseService;
	}
	
	@PostConstruct
	public void init() {
		beginConversationIfTransient();
		setEntityForm(new MedicalLicense());
	}
	
	@Override
	protected void doBeforeCreate() {
		MedicalLicense medicalLicense = getService().findByProfessionalId(getEntityForm().getProfessionalId());
		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
		if (medicalLicense != null && medicalLicense.getId() != null) {
			getEntityForm().setProfessionalId(null);
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "medicaldoctor.license.error.professionalid.alreadyexists.summary", "medicaldoctor.license.error.professionalid.alreadyexists.detail");
			msgs.add(msg);
		}
		if (!msgs.isEmpty()) {
			throw new ValidatorException(msgs);
		}
		getEntityForm().setUser(authenticationMB.getLoggedUser());
	}
	
	@Override
	protected String getCreateURL() {
		return urlMB.getMdAddLicense(2L);
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

}
