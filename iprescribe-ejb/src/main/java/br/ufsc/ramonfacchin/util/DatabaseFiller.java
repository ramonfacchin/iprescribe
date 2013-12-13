package br.ufsc.ramonfacchin.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.commons.io.FileUtils;

import br.ufsc.ramonfacchin.common.constant.ConfigurationConstants;
import br.ufsc.ramonfacchin.common.constant.GlobalConstants;
import br.ufsc.ramonfacchin.common.utils.IPrescribeDateUtils;
import br.ufsc.ramonfacchin.entity.DatabaseConfiguration;
import br.ufsc.ramonfacchin.entity.Identity;
import br.ufsc.ramonfacchin.entity.MedicalCertificate;
import br.ufsc.ramonfacchin.entity.MedicalLicense;
import br.ufsc.ramonfacchin.entity.User;
import br.ufsc.ramonfacchin.enumeration.GenderEnum;
import br.ufsc.ramonfacchin.service.IDatabaseConfigurationServiceLocal;
import br.ufsc.ramonfacchin.service.IIdentityServiceLocal;
import br.ufsc.ramonfacchin.service.IMedicalCertificateServiceLocal;
import br.ufsc.ramonfacchin.service.IMedicalLicenseServiceLocal;
import br.ufsc.ramonfacchin.service.ISampleAuthenticationServiceLocal;
import br.ufsc.ramonfacchin.service.IUserServiceLocal;
import br.ufsc.ramonfacchin.vo.MedicalCertificateVO;

/**
 * Fills Database as the Application starts.
 * 
 * @author ramon
 * @version $Id: $
 * @since 0.0.0.0
 * 
 */
@Startup
@Singleton
public class DatabaseFiller {

	@EJB
	IDatabaseConfigurationServiceLocal databaseConfigurationService;

	@EJB
	IUserServiceLocal userService;

	@EJB
	ISampleAuthenticationServiceLocal sampleAuthenticationService;

	@EJB
	IMedicalCertificateServiceLocal medicalCertificateService;

	@EJB
	IMedicalLicenseServiceLocal medicalLicenseService;

	@EJB
	IIdentityServiceLocal identityService;

	/**
	 * If you don't want to fill database when the application finishes starting
	 * please, comment the {@link PostConstruct} annotation.
	 */
	@PostConstruct
	private void fillDB() {

		/*
		 * Creating database configurations
		 */
		createDatabaseConfigurationIfDoesntExist(ConfigurationConstants.WEB.CONVERSATION_DURATION, (5 * GlobalConstants.TIME.MINUTE) + "");
		createDatabaseConfigurationIfDoesntExist(ConfigurationConstants.SECURITY.MEDICAL_CERTIFICATE_CHECK_HASH_ALGORITHM, "SHA1");

		/*
		 * Creating users, identities and medical licenses
		 */
		Identity identity;
		User user;
		MedicalLicense license;

		identity = new Identity();
		identity.setName("Dave Mustaine");
		identity.setBirth(IPrescribeDateUtils.createDate(13, Calendar.SEPTEMBER, 1961, 0, 0, 0, 1));
		identity.setCitizenRegistry("58317812243");
		identity.setGender(GenderEnum.MALE);

		user = new User();
		user.setUsername("mustaine");
		user.setPassword("mustaine");
		user.setEmail("mustaine@iprescribe.com");
		user.setIdentity(identity);
		userService.save(user);

		license = new MedicalLicense();
		license.setCountry("Brazil");
		license.setState("Santa Catarina");
		license.setVerified(true);
		license.setProfessionalId("0");
		license.setUser(user);
		medicalLicenseService.save(license);

		identity = new Identity();
		identity.setName("James Hetfield");
		identity.setBirth(IPrescribeDateUtils.createDate(3, Calendar.AUGUST, 1963, 0, 0, 0, 1));
		identity.setCitizenRegistry("41790031982");
		identity.setGender(GenderEnum.MALE);

		user = new User();
		user.setUsername("hetfield");
		user.setPassword("hetfield");
		user.setEmail("hetfield@iprescribe.com");
		user.setIdentity(identity);
		userService.save(user);

		license = new MedicalLicense();
		license.setCountry("Brazil");
		license.setState("Santa Catarina");
		license.setVerified(true);
		license.setProfessionalId("1");
		license.setUser(user);
		medicalLicenseService.save(license);

		identity = new Identity();
		identity.setName("John Schaffer");
		identity.setBirth(IPrescribeDateUtils.createDate(15, Calendar.MARCH, 1968, 0, 0, 0, 1));
		identity.setCitizenRegistry("38720675593");
		identity.setGender(GenderEnum.MALE);

		user = new User();
		user.setUsername("schaffer");
		user.setPassword("schaffer");
		user.setEmail("schaffer@iprescribe.com");
		user.setIdentity(identity);
		userService.save(user);

		license = new MedicalLicense();
		license.setCountry("Brazil");
		license.setState("Santa Catarina");
		license.setVerified(true);
		license.setProfessionalId("2");
		license.setUser(user);
		medicalLicenseService.save(license);

		identity = new Identity();
		identity.setName("Tobias Sammet");
		identity.setBirth(IPrescribeDateUtils.createDate(21, Calendar.NOVEMBER, 1977, 0, 0, 0, 1));
		identity.setCitizenRegistry("50503741973");
		identity.setGender(GenderEnum.MALE);

		user = new User();
		user.setUsername("sammet");
		user.setPassword("sammet");
		user.setEmail("sammet@iprescribe.com");
		user.setIdentity(identity);
		userService.save(user);

		license = new MedicalLicense();
		license.setCountry("Brazil");
		license.setState("Santa Catarina");
		license.setVerified(true);
		license.setProfessionalId("3");
		license.setUser(user);
		medicalLicenseService.save(license);

		identity = new Identity();
		identity.setName("Anders Friden");
		identity.setBirth(IPrescribeDateUtils.createDate(25, Calendar.MARCH, 1973, 0, 0, 0, 1));
		identity.setCitizenRegistry("36787788525");
		identity.setGender(GenderEnum.MALE);

		user = new User();
		user.setUsername("friden");
		user.setPassword("friden");
		user.setEmail("friden@iprescribe.com");
		user.setIdentity(identity);
		userService.save(user);

		license = new MedicalLicense();
		license.setCountry("Brazil");
		license.setState("Santa Catarina");
		license.setVerified(true);
		license.setProfessionalId("4");
		license.setUser(user);
		medicalLicenseService.save(license);

		identity = new Identity();
		identity.setName("Ramon Facchin");
		identity.setBirth(IPrescribeDateUtils.createDate(2, Calendar.MAY, 1988, 0, 0, 0, 1));
		String ramonCitizenRegistry = "00000000191";
		identity.setCitizenRegistry(ramonCitizenRegistry);
		identity.setGender(GenderEnum.MALE);
		identityService.save(identity);

		user = new User();
		user.setUsername("ramonfacchin");
		user.setPassword("ramonfacchin");
		user.setEmail("ramonfacchin@iprescribe.com");
		user.setIdentity(identity);
		userService.save(user);

		license = new MedicalLicense();
		license.setCountry("Brazil");
		license.setState("Santa Catarina");
		license.setVerified(true);
		license.setProfessionalId("5");
		license.setUser(user);
		medicalLicenseService.save(license);

		identity = new Identity();
		identity.setName("Mariane Flôr");
		identity.setBirth(IPrescribeDateUtils.createDate(22, Calendar.NOVEMBER, 1992, 0, 0, 0, 1));
		String mariCitizenRegistry = "20466481454";
		identity.setCitizenRegistry(mariCitizenRegistry);
		identity.setGender(GenderEnum.FEMALE);
		identityService.save(identity);

		user = new User();
		user.setUsername("marianeflor");
		user.setPassword("marianeflor");
		user.setEmail("marianeflor@iprescribe.com");
		user.setIdentity(identity);
		userService.save(user);

		license = new MedicalLicense();
		license.setCountry("Brazil");
		license.setState("Santa Catarina");
		license.setVerified(true);
		license.setProfessionalId("6");
		license.setUser(user);
		medicalLicenseService.save(license);

		/*
		 * Creating sample medical certificates
		 */
		MedicalCertificate certificate;
		MedicalLicense issuerLicense;
//		MedicalCertificateRegistry registry;
		Identity certified;
		String comments = "Campo de comentários sobre o atestado.";
		String goal = "Campo com o propósito/motivo da emissão do atestado.";
		Date issued = new Date();
		ByteArrayOutputStream pdf;
		int certificateCount = 0;

		certificateCount++;
		certified = identityService.findByCitizenRegistry(ramonCitizenRegistry);
		issuerLicense = medicalLicenseService.findByProfessionalId("0");
		certificate = new MedicalCertificate();
		certificate.setCertified(certified);
		certificate.setIssuerLicense(issuerLicense);
		certificate.setComments(comments);
		certificate.setIssued(issued);
		certificate.setGoal(goal);
		certificate.setValidity(IPrescribeDateUtils.addDays(issued, 5));
		medicalCertificateService.save(certificate);
		pdf = medicalCertificateService.generatePDFForMedicalCertificateVO(new MedicalCertificateVO(certificate));
		try {
			FileUtils.writeByteArrayToFile(new File("/tmp/cert0"+certificateCount+".pdf"), pdf.toByteArray());
		} catch (IOException e) {
			System.out.println("Error writing cert0"+certificateCount+".pdf");
		}

		certificateCount++;
		certified = identityService.findByCitizenRegistry(ramonCitizenRegistry);
		issuerLicense = medicalLicenseService.findByProfessionalId("0");
		certificate = new MedicalCertificate();
		certificate.setCertified(certified);
		certificate.setIssuerLicense(issuerLicense);
		certificate.setComments(comments);
		certificate.setIssued(IPrescribeDateUtils.addDays(issued, -5));
		certificate.setGoal(goal);
		certificate.setValidity(IPrescribeDateUtils.addDays(issued, 2));
		medicalCertificateService.save(certificate);
		pdf = medicalCertificateService.generatePDFForMedicalCertificateVO(new MedicalCertificateVO(certificate));
		try {
			FileUtils.writeByteArrayToFile(new File("/tmp/cert0"+certificateCount+".pdf"), pdf.toByteArray());
		} catch (IOException e) {
			System.out.println("Error writing cert0"+certificateCount+".pdf");
		}

		certificateCount++;
		certified = identityService.findByCitizenRegistry(ramonCitizenRegistry);
		issuerLicense = medicalLicenseService.findByProfessionalId("0");
		certificate = new MedicalCertificate();
		certificate.setCertified(certified);
		certificate.setIssuerLicense(issuerLicense);
		certificate.setComments(comments);
		certificate.setIssued(IPrescribeDateUtils.addDays(issued, -5));
		certificate.setGoal(goal);
		certificate.setValidity(IPrescribeDateUtils.addDays(issued, -1));
		medicalCertificateService.save(certificate);
		pdf = medicalCertificateService.generatePDFForMedicalCertificateVO(new MedicalCertificateVO(certificate));
		try {
			FileUtils.writeByteArrayToFile(new File("/tmp/cert0"+certificateCount+".pdf"), pdf.toByteArray());
		} catch (IOException e) {
			System.out.println("Error writing cert0"+certificateCount+".pdf");
		}

		certificateCount++;
		certified = identityService.findByCitizenRegistry(mariCitizenRegistry);
		issuerLicense = medicalLicenseService.findByProfessionalId("0");
		certificate = new MedicalCertificate();
		certificate.setCertified(certified);
		certificate.setIssuerLicense(issuerLicense);
		certificate.setComments(comments);
		certificate.setIssued(IPrescribeDateUtils.addDays(issued, -10));
		certificate.setGoal(goal);
		certificate.setValidity(IPrescribeDateUtils.addDays(issued, -6));
		medicalCertificateService.save(certificate);
		pdf = medicalCertificateService.generatePDFForMedicalCertificateVO(new MedicalCertificateVO(certificate));
		try {
			FileUtils.writeByteArrayToFile(new File("/tmp/cert0"+certificateCount+".pdf"), pdf.toByteArray());
		} catch (IOException e) {
			System.out.println("Error writing cert0"+certificateCount+".pdf");
		}

		certificateCount++;
		certified = identityService.findByCitizenRegistry(mariCitizenRegistry);
		issuerLicense = medicalLicenseService.findByProfessionalId("0");
		certificate = new MedicalCertificate();
		certificate.setCertified(certified);
		certificate.setIssuerLicense(issuerLicense);
		certificate.setComments(comments);
		certificate.setIssued(IPrescribeDateUtils.addDays(issued, -8));
		certificate.setGoal(goal);
		certificate.setValidity(IPrescribeDateUtils.addDays(issued, -3));
		medicalCertificateService.save(certificate);
		pdf = medicalCertificateService.generatePDFForMedicalCertificateVO(new MedicalCertificateVO(certificate));
		try {
			FileUtils.writeByteArrayToFile(new File("/tmp/cert0"+certificateCount+".pdf"), pdf.toByteArray());
		} catch (IOException e) {
			System.out.println("Error writing cert0"+certificateCount+".pdf");
		}

		certificateCount++;
		certified = identityService.findByCitizenRegistry(mariCitizenRegistry);
		issuerLicense = medicalLicenseService.findByProfessionalId("0");
		certificate = new MedicalCertificate();
		certificate.setCertified(certified);
		certificate.setIssuerLicense(issuerLicense);
		certificate.setComments(comments);
		certificate.setIssued(issued);
		certificate.setGoal(goal);
		certificate.setValidity(IPrescribeDateUtils.addDays(issued, 8));
		medicalCertificateService.save(certificate);
		pdf = medicalCertificateService.generatePDFForMedicalCertificateVO(new MedicalCertificateVO(certificate));
		try {
			FileUtils.writeByteArrayToFile(new File("/tmp/cert0"+certificateCount+".pdf"), pdf.toByteArray());
		} catch (IOException e) {
			System.out.println("Error writing cert0"+certificateCount+".pdf");
		}

		certificateCount++;
		certified = identityService.findByCitizenRegistry(ramonCitizenRegistry);
		issuerLicense = medicalLicenseService.findByProfessionalId("2");
		certificate = new MedicalCertificate();
		certificate.setCertified(certified);
		certificate.setIssuerLicense(issuerLicense);
		certificate.setComments(comments);
		certificate.setIssued(IPrescribeDateUtils.addDays(issued, -40));
		certificate.setGoal(goal);
		certificate.setValidity(IPrescribeDateUtils.addDays(issued, -30));
		medicalCertificateService.save(certificate);
		pdf = medicalCertificateService.generatePDFForMedicalCertificateVO(new MedicalCertificateVO(certificate));
		try {
			FileUtils.writeByteArrayToFile(new File("/tmp/cert0"+certificateCount+".pdf"), pdf.toByteArray());
		} catch (IOException e) {
			System.out.println("Error writing cert0"+certificateCount+".pdf");
		}

		certificateCount++;
		certified = identityService.findByCitizenRegistry(ramonCitizenRegistry);
		issuerLicense = medicalLicenseService.findByProfessionalId("2");
		certificate = new MedicalCertificate();
		certificate.setCertified(certified);
		certificate.setIssuerLicense(issuerLicense);
		certificate.setComments(comments);
		certificate.setIssued(IPrescribeDateUtils.addDays(issued, -50));
		certificate.setGoal(goal);
		certificate.setValidity(IPrescribeDateUtils.addDays(issued, -40));
		medicalCertificateService.save(certificate);
		pdf = medicalCertificateService.generatePDFForMedicalCertificateVO(new MedicalCertificateVO(certificate));
		try {
			FileUtils.writeByteArrayToFile(new File("/tmp/cert0"+certificateCount+".pdf"), pdf.toByteArray());
		} catch (IOException e) {
			System.out.println("Error writing cert0"+certificateCount+".pdf");
		}

		certificateCount++;
		certified = identityService.findByCitizenRegistry(ramonCitizenRegistry);
		issuerLicense = medicalLicenseService.findByProfessionalId("2");
		certificate = new MedicalCertificate();
		certificate.setCertified(certified);
		certificate.setIssuerLicense(issuerLicense);
		certificate.setComments(comments);
		certificate.setIssued(IPrescribeDateUtils.addDays(issued, -70));
		certificate.setGoal(goal);
		certificate.setValidity(IPrescribeDateUtils.addDays(issued, -65));
		medicalCertificateService.save(certificate);
		pdf = medicalCertificateService.generatePDFForMedicalCertificateVO(new MedicalCertificateVO(certificate));
		try {
			FileUtils.writeByteArrayToFile(new File("/tmp/cert0"+certificateCount+".pdf"), pdf.toByteArray());
		} catch (IOException e) {
			System.out.println("Error writing cert0"+certificateCount+".pdf");
		}

		certificateCount++;
		certified = identityService.findByCitizenRegistry(ramonCitizenRegistry);
		issuerLicense = medicalLicenseService.findByProfessionalId("3");
		certificate = new MedicalCertificate();
		certificate.setCertified(certified);
		certificate.setIssuerLicense(issuerLicense);
		certificate.setComments(comments);
		certificate.setIssued(IPrescribeDateUtils.addDays(issued, -100));
		certificate.setGoal(goal);
		certificate.setValidity(IPrescribeDateUtils.addDays(issued, -80));
		medicalCertificateService.save(certificate);
		pdf = medicalCertificateService.generatePDFForMedicalCertificateVO(new MedicalCertificateVO(certificate));
		try {
			FileUtils.writeByteArrayToFile(new File("/tmp/cert0"+certificateCount+".pdf"), pdf.toByteArray());
		} catch (IOException e) {
			System.out.println("Error writing cert0"+certificateCount+".pdf");
		}

		certificateCount++;
		certified = identityService.findByCitizenRegistry(ramonCitizenRegistry);
		issuerLicense = medicalLicenseService.findByProfessionalId("3");
		certificate = new MedicalCertificate();
		certificate.setCertified(certified);
		certificate.setIssuerLicense(issuerLicense);
		certificate.setComments(comments);
		certificate.setIssued(IPrescribeDateUtils.addDays(issued, -140));
		certificate.setGoal(goal);
		certificate.setValidity(IPrescribeDateUtils.addDays(issued, -130));
		medicalCertificateService.save(certificate);
		pdf = medicalCertificateService.generatePDFForMedicalCertificateVO(new MedicalCertificateVO(certificate));
		try {
			FileUtils.writeByteArrayToFile(new File("/tmp/cert0"+certificateCount+".pdf"), pdf.toByteArray());
		} catch (IOException e) {
			System.out.println("Error writing cert0"+certificateCount+".pdf");
		}
	}

	private void createDatabaseConfigurationIfDoesntExist(String key, String value) {
		DatabaseConfiguration databaseConfiguration = databaseConfigurationService.findByKey(key);
		if ((databaseConfiguration == null) || (databaseConfiguration.getId() == null)) {
			databaseConfiguration = new DatabaseConfiguration();
			databaseConfiguration.setKey(key);
			databaseConfiguration.setValue(value);
			databaseConfigurationService.save(databaseConfiguration);
		}
	}

}
