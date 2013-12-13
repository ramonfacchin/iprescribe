package br.ufsc.ramonfacchin.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;

import br.ufsc.ramonfacchin.common.constant.ConfigurationConstants;
import br.ufsc.ramonfacchin.common.exception.HashAlreadyExistsException;
import br.ufsc.ramonfacchin.common.utils.IPrescribeDateUtils;
import br.ufsc.ramonfacchin.common.utils.IPrescribeStringUtils;
import br.ufsc.ramonfacchin.dao.IMedicalCertificateDAO;
import br.ufsc.ramonfacchin.dao.IMedicalCertificateRegistryDAO;
import br.ufsc.ramonfacchin.entity.Identity;
import br.ufsc.ramonfacchin.entity.MedicalCertificate;
import br.ufsc.ramonfacchin.entity.MedicalCertificateRegistry;
import br.ufsc.ramonfacchin.entity.MedicalLicense;
import br.ufsc.ramonfacchin.enumeration.HashAlgorithm;
import br.ufsc.ramonfacchin.service.IDatabaseConfigurationServiceLocal;
import br.ufsc.ramonfacchin.service.IIdentityServiceLocal;
import br.ufsc.ramonfacchin.service.IMedicalCertificateService;
import br.ufsc.ramonfacchin.service.IMedicalCertificateServiceLocal;
import br.ufsc.ramonfacchin.service.IMedicalLicenseServiceLocal;
import br.ufsc.ramonfacchin.service.IUserServiceLocal;
import br.ufsc.ramonfacchin.util.IPrescribePDFUtils;
import br.ufsc.ramonfacchin.vo.MedicalCertificateVO;

@Stateless
@Local(IMedicalCertificateServiceLocal.class)
@Remote(IMedicalCertificateService.class)
public class MedicalCertificateService extends BaseService<MedicalCertificate> implements IMedicalCertificateServiceLocal {

	private static final long serialVersionUID = -3261966640762980045L;
	
	@EJB
	IMedicalCertificateDAO medicalCertificateDAO;
	
	@EJB
	IMedicalCertificateRegistryDAO medicalCertificateRegistryDAO;
	
	@EJB
	IMedicalLicenseServiceLocal medicalLicenseService;
	
	@EJB
	IIdentityServiceLocal identityService;
	
	@EJB
	IUserServiceLocal userService;
	
	@EJB
	IDatabaseConfigurationServiceLocal databaseConfigurationService;

	@Override
	public List<MedicalCertificate> listByIssuerId(Long issuerId, boolean validOnly) {
		return getDAO().listByIssuerId(issuerId, validOnly);
	}

	@Override
	public List<MedicalCertificate> listByIssuerCitizenRegistry(String issuerCitizenRegistry, boolean validOnly) {
		return getDAO().listByIssuerCitizenRegistry(issuerCitizenRegistry, validOnly);
	}

	@Override
	public List<MedicalCertificate> listByCertifiedId(Long certifiedId, boolean validOnly) {
		return getDAO().listByCertifiedId(certifiedId, validOnly);
	}

	@Override
	public List<MedicalCertificate> listByCertifiedCitizenRegistry(String certifiedCitizenRegistry, boolean validOnly) {
		return getDAO().listByCertifiedCitizenRegistry(certifiedCitizenRegistry, validOnly);
	}

	@Override
	public List<MedicalCertificate> list(int maxResults, int firstResultIndex, Boolean listOnlyActive) {
		return getDAO().list(maxResults, firstResultIndex, listOnlyActive);
	}

	@Override
	protected IMedicalCertificateDAO getDAO() {
		return medicalCertificateDAO;
	}
	
	@Override
	@WebMethod(exclude = true)
	public void save(MedicalCertificate entity) {
		super.save(entity);
		
		//After saving the certificate, a registry must be generated.
		MedicalCertificateRegistry registry = new MedicalCertificateRegistry();
		//setting up the medical certificate for the registry being generated
		registry.setMedicalCertificate(entity);
		
		/*
		 * Generating a hash for the medical certificate.
		 * The generated hash will be used for performing authenticity check.
		 * The generation must be non collision-tolerant.
		 * After a max number of attempts, dynamically set up, the system stops 
		 * trying to generate the hash and causes an error.  
		 */
		int attempts = 0;
		int maxAttempts = 15;
		boolean success = false;
		
		HashAlgorithm algorithm = HashAlgorithm.SHA1;
		String maxAttemptsStr = databaseConfigurationService.get(ConfigurationConstants.SECURITY.HASH_GENERATION_ATTEMPTS);
		if (IPrescribeStringUtils.isNotBlank(maxAttemptsStr)) {
			maxAttempts = new Integer(maxAttemptsStr);
		}
		while (attempts <= maxAttempts && !success) {
			attempts++;
			if (attempts > 5) {
				algorithm = HashAlgorithm.SHA256;
			}
			if (attempts > 10) {
				algorithm = HashAlgorithm.SHA512;
			}
			try {
				generateHash(registry,algorithm);
				success = true;
			} catch (HashAlreadyExistsException e) {
				if (attempts >= maxAttempts) {
					throw e;
				}
			}
		}
		medicalCertificateRegistryDAO.persist(registry);
		
	}
	
	/**
	 * Performs an attempt to generate a Hash for the medical certificate.
	 * @throws HashAlreadyExistsException in case of collision.
	 * @param registry
	 * @param algorithm
	 */
	private void generateHash(MedicalCertificateRegistry registry, HashAlgorithm algorithm) {
		/*
		 * Now, a XML representation for the MedicalCertificate must be generated and hashed.
		 * The generated hash will be used for perform online medical certificate authenticity check.
		 */
		String certificateXML = IPrescribeStringUtils.parseEntityToXmlString(new MedicalCertificateVO(registry.getMedicalCertificate()));
		
		System.out.println("Calculating HASH for the following Medical Certificate: " + certificateXML);
		switch (algorithm) {
		case SHA256:
			registry.setHash(IPrescribeStringUtils.sha256Hex(certificateXML.getBytes()));
			registry.setAlgorithm(HashAlgorithm.SHA256);
			break;
		case SHA512:
			registry.setHash(IPrescribeStringUtils.sha512Hex(certificateXML.getBytes()));
			registry.setAlgorithm(HashAlgorithm.SHA512);
			break;
		case SHA1:
		default:
			registry.setHash(IPrescribeStringUtils.sha1Hex(certificateXML.getBytes()));
			registry.setAlgorithm(HashAlgorithm.SHA1);
			break;
		}
		
		MedicalCertificateRegistry foundRegistry = medicalCertificateRegistryDAO.findByHash(registry.getHash());
		if (foundRegistry != null) {
			registry.setHash(null);
			registry.setAlgorithm(null);
			throw new HashAlreadyExistsException(registry.getHash(), registry.getAlgorithm().toString());
		}
	}
	
	/*
	 * FIXME: method shouldnt be here -> I18n issues -> text hardcoded
	 */
	public ByteArrayOutputStream generatePDFForMedicalCertificateVO(MedicalCertificateVO certificateVO) {
		Map<String, String> map = new HashMap<String, String>();
		if (IPrescribeStringUtils.isNotBlank(certificateVO.getCertifiedId())) {
			Long certifiedId = new Long(certificateVO.getCertifiedId());
			Identity identity = identityService.findById(certifiedId);
			map.put(IPrescribePDFUtils.CERTIFIED_LABEL, "Certified:");
			map.put(IPrescribePDFUtils.CERTIFIED_VALUE, identity.getName() + (IPrescribeStringUtils.isBlank(identity.getCitizenRegistry()) ? "" : " ("+identity.getCitizenRegistry()+")"));
		}
		
		if (IPrescribeStringUtils.isNotBlank(certificateVO.getComments())) {
			map.put(IPrescribePDFUtils.COMMENTS, certificateVO.getComments());
		}
		
		if (IPrescribeStringUtils.isNotBlank(certificateVO.getComplementaryExamsResults())) {
			map.put(IPrescribePDFUtils.COMPLEMENTARY_EXAMS_RESULTS_LABEL, "Complementary exams results:");
			map.put(IPrescribePDFUtils.COMPLEMENTARY_EXAMS_RESULTS_VALUE, certificateVO.getComplementaryExamsResults());
		}
		
		if (IPrescribeStringUtils.isNotBlank(certificateVO.getDiagnosys())) {
			map.put(IPrescribePDFUtils.DIAGNOSYS_LABEL, "Diagnosys:");
			map.put(IPrescribePDFUtils.DIAGNOSYS_VALUE, certificateVO.getDiagnosys());
		}
		
		if (IPrescribeStringUtils.isNotBlank(certificateVO.getGoal())) {
			//TODO: Purpose or Goal? Refactor!
			map.put(IPrescribePDFUtils.PURPOSE_LABEL, "Purpose:");
			map.put(IPrescribePDFUtils.PURPOSE_VALUE, certificateVO.getGoal());
		}
		
		if (IPrescribeStringUtils.isNotBlank(certificateVO.getHealthConsequences())) {
			map.put(IPrescribePDFUtils.HEALTH_CONSEQUENCES_LABEL, "Health consequences:");
			map.put(IPrescribePDFUtils.HEALTH_CONSEQUENCES_VALUE, certificateVO.getHealthConsequences());
		}
		
		if (IPrescribeStringUtils.isNotBlank(certificateVO.getIcd())) {
			map.put(IPrescribePDFUtils.ICD_LABEL, "International Classification of Disease:");
			map.put(IPrescribePDFUtils.ICD_VALUE, certificateVO.getIcd());
		}
		
		if (IPrescribeStringUtils.isNotBlank(certificateVO.getIssuerLicenseId())) {
			Long issuerLicenseId = new Long(certificateVO.getIssuerLicenseId());
			MedicalLicense license = medicalLicenseService.findById(issuerLicenseId);
			map.put(IPrescribePDFUtils.MD_NAME, license.getUser().getIdentity().getName());
			map.put(IPrescribePDFUtils.MD_PROFESSIONAL_ID, license.getProfessionalId() + " ("+license.getCountry()+" - "+license.getState()+")");
			//FIXME: how to fill the specialty?
//			map.put(IPrescribePDFUtils.MD_SPECIALTY, null);
		}
		
		if (IPrescribeStringUtils.isNotBlank(certificateVO.getPrognosys())) {
			map.put(IPrescribePDFUtils.PROGNOSYS_LABEL, "Prognosys:");
			map.put(IPrescribePDFUtils.PROGNOSYS_VALUE, certificateVO.getPrognosys());
		}
		
		if (IPrescribeStringUtils.isNotBlank(certificateVO.getTherapeuticBehavior())) {
			map.put(IPrescribePDFUtils.THERAPEUTIC_BEHAVIOR_LABEL, "Therapeutic behavior:");
			map.put(IPrescribePDFUtils.THERAPEUTIC_BEHAVIOR_VALUE, certificateVO.getTherapeuticBehavior());
		}
		
		if (certificateVO.getIssued() != null) {
			map.put(IPrescribePDFUtils.ISSUED_LABEL, "Date issued:");
			//FIXME: should check for locale
			map.put(IPrescribePDFUtils.ISSUED_VALUE, IPrescribeDateUtils.formatDate("dd/MM/yyyy", certificateVO.getIssued()));
		}
		
		if (certificateVO.getValidity() != null) {
			map.put(IPrescribePDFUtils.EXPIRES_LABEL, "Valid until:");
			map.put(IPrescribePDFUtils.EXPIRES_VALUE, IPrescribeDateUtils.formatDate("dd/MM/yyyy", certificateVO.getValidity()));
		}
		
		if (IPrescribeStringUtils.isNotBlank(certificateVO.getId())) {
			MedicalCertificateRegistry registry = findRegistryByCertificateId(new Long(certificateVO.getId()));
			String hash = registry.getHash();
			map.put(IPrescribePDFUtils.AUTHENTICITY_MESSAGE, "To verify the authenticity of this medical certificate, access the following link and enter the authenticity code \""+hash+"\" to view the original certificate.");
			map.put(IPrescribePDFUtils.AUTHENTICITY_URL, "https://someurl:8443/iprescribe-web/authenticity-check");
		}
		
		try {
			return IPrescribePDFUtils.generateMedicalCertificatePDFItext(map);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public MedicalCertificate findByHash(String hash) {
		MedicalCertificateRegistry registry = medicalCertificateRegistryDAO.findByHash(hash);
		return registry == null ? null : registry.getMedicalCertificate();
	}

	@Override
	public MedicalCertificateVO findVOByHash(String hash) {
		MedicalCertificate certificate = findByHash(hash);
		return certificate == null ? null : new MedicalCertificateVO(certificate);
	}

	@Override
	public MedicalCertificateRegistry findRegistryByCertificateId(Long medicalCertificateId) {
		return medicalCertificateRegistryDAO.findByCertificateId(medicalCertificateId);
	}

}
