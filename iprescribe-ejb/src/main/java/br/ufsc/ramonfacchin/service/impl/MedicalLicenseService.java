package br.ufsc.ramonfacchin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import br.ufsc.ramonfacchin.dao.IMedicalLicenseDAO;
import br.ufsc.ramonfacchin.entity.MedicalLicense;
import br.ufsc.ramonfacchin.service.IMedicalLicenseService;
import br.ufsc.ramonfacchin.service.IMedicalLicenseServiceLocal;

@Stateless
@Remote(IMedicalLicenseService.class)
@Local(IMedicalLicenseServiceLocal.class)
public class MedicalLicenseService extends BaseService<MedicalLicense> implements IMedicalLicenseServiceLocal {

	private static final long serialVersionUID = 4743391284982416334L;

	@EJB
	IMedicalLicenseDAO medicalLicenseDAO;
	
	@Override
	public List<MedicalLicense> listByUsername(String username) {
		return getDAO().listByUsername(username);
	}

	@Override
	public List<MedicalLicense> listByUserId(Long userId) {
		return getDAO().listByUserId(userId);
	}

	@Override
	public MedicalLicense findByProfessionalId(String professionalId) {
		return getDAO().findByProfessionalId(professionalId);
	}

	@Override
	public List<MedicalLicense> list(int maxResults, int firstResultIndex, Boolean listOnlyActive) {
		return getDAO().list(maxResults, firstResultIndex, listOnlyActive);
	}

	@Override
	protected IMedicalLicenseDAO getDAO() {
		return medicalLicenseDAO;
	}

	@Override
	public List<MedicalLicense> listNotVerified() {
		return getDAO().listNotVerified();
	}

}
