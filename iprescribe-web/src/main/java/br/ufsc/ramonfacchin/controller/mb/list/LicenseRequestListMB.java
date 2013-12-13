package br.ufsc.ramonfacchin.controller.mb.list;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;

import br.ufsc.ramonfacchin.controller.mb.util.BaseListMB;
import br.ufsc.ramonfacchin.entity.MedicalLicense;
import br.ufsc.ramonfacchin.service.IMedicalLicenseServiceLocal;

@Named("licenseRequestListMB")
@ConversationScoped
public class LicenseRequestListMB extends BaseListMB<MedicalLicense> {

	private static final long serialVersionUID = 5691641790877506807L;
	
	@EJB
	IMedicalLicenseServiceLocal medicalLicenseService;
	
	@Override
	protected IMedicalLicenseServiceLocal getService() {
		return medicalLicenseService;
	}
	
	@Override
	public List<MedicalLicense> list() {
		return getService().listNotVerified();
	}
	
	public String verify(MedicalLicense license) {
		license.setVerified(true);
		getService().save(license);
		addMessage(FacesMessage.SEVERITY_INFO, null, getI18nMessage("licenserequest.msg.verify.success.summary", new String[] {license.getProfessionalId()}), getI18nMessage("licenserequest.msg.verify.success.detail", new String[] {license.getProfessionalId()}));
		return null;
	}

	public String disapprove(MedicalLicense license) {
		getService().remove(getService().findById(license.getId()));
		addMessage(FacesMessage.SEVERITY_WARN, null, getI18nMessage("licenserequest.msg.disapprove.success.summary", new String[] {license.getProfessionalId()}), getI18nMessage("licenserequest.msg.disapprove.success.detail", new String[] {license.getProfessionalId()}));
		return null;
	}

}
