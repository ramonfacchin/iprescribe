package br.ufsc.ramonfacchin.controller.mb.list;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;

import br.ufsc.ramonfacchin.controller.mb.util.BaseListMB;
import br.ufsc.ramonfacchin.entity.MedicalCertificate;
import br.ufsc.ramonfacchin.entity.User;
import br.ufsc.ramonfacchin.service.IMedicalCertificateServiceLocal;
import br.ufsc.ramonfacchin.service.IUserServiceLocal;

@SessionScoped
@Named("userCertificateListMB")
public class UserCertificateListMB extends BaseListMB<MedicalCertificate> {

	private static final long serialVersionUID = -8807330515967301221L;

	@Inject
	IMedicalCertificateServiceLocal medicalCertificateService;
	
	@EJB
	IUserServiceLocal userService;
	
	private boolean listValidOnly = false;
	
	@PostConstruct
	private void init() {
		setListValidOnly(false);
	}
	
	@Override
	protected IMedicalCertificateServiceLocal getService() {
		return medicalCertificateService;
	}
	
	@Override
	public List<MedicalCertificate> list() {
		String principal = (String) SecurityUtils.getSubject().getPrincipal();
		User currentUser = userService.findByUsername(principal);
		List<MedicalCertificate> listByCertifiedId = medicalCertificateService.listByCertifiedId(currentUser.getIdentity().getId(),isListValidOnly());
		return listByCertifiedId;
	}

	public boolean isListValidOnly() {
		return listValidOnly;
	}

	public void setListValidOnly(boolean listValidOnly) {
		this.listValidOnly = listValidOnly;
	}
	
	public String downloadPdf(MedicalCertificate certificate) {
		return null;
	}

}
