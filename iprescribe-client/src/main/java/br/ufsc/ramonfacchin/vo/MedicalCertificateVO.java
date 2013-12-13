package br.ufsc.ramonfacchin.vo;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.ufsc.ramonfacchin.entity.MedicalCertificate;

@XmlRootElement(name="medicalCertificate")
public class MedicalCertificateVO implements Serializable {

	private static final long serialVersionUID = -3740252974291424787L;

	public MedicalCertificateVO() {
	}

	public MedicalCertificateVO(MedicalCertificate certificate) {
		try {
			Long issuerLicenseId = certificate.getIssuerLicense().getId();
			Long certifiedId = certificate.getCertified().getId();
			setId(certificate.getId()+"");
			setIssuerLicenseId(issuerLicenseId == null ? null : issuerLicenseId+"");
			setCertifiedId(certifiedId == null ? null : certifiedId+"");
			setIssued(certificate.getIssued());
			setValidity(certificate.getValidity());
			setGoal(certificate.getGoal());
			setComments(certificate.getComments());
			setIcd(certificate.getIcd());
			setTherapeuticBehavior(certificate.getTherapeuticBehavior());
			setPrognosys(certificate.getPrognosys());
			setDiagnosys(certificate.getDiagnosys());
			setHealthConsequences(certificate.getHealthConsequences());
			setComplementaryExamsResults(certificate.getComplementaryExamsResults());
		} catch (Exception e) {
			throw new RuntimeException("Cannot instantiate MedicalCertificateVO -> null Issuer/Certified");
		}
	}
	
	private String id;
	
	/**
	 * References the issuer of the Medical Certificate.
	 */
	private String issuerLicenseId;

	/**
	 * References the person certified by the Medical Certificate.
	 */
	private String certifiedId;

	/**
	 * Date and Time when the Medical Certificate was made.
	 */
	private Date issued;

	/**
	 * Date and Time until when the Medical Certificate is valid.
	 */
	private Date validity;

	/**
	 * Goal for which the certificate was emitted.
	 */
	private String goal;

	/**
	 * Comments regarding the certificate and the context it was emitted.
	 */
	private String comments;
	
	/**
	 * International Disease Code (ICD)
	 */
	private String icd;
	
	/**
	 * Therapeutic behavior of the diagnosys.
	 */
	private String therapeuticBehavior;
	
	/**
	 * Prognosys
	 */
	private String prognosys;
	
	/**
	 * Diagnosys
	 */
	private String diagnosys;
	
	/**
	 * Health consequences
	 */
	private String healthConsequences;
	
	/**
	 * Complementary exams results
	 */
	private String complementaryExamsResults;

	public String getIssuerLicenseId() {
		return issuerLicenseId;
	}

	public void setIssuerLicenseId(String issuerId) {
		this.issuerLicenseId = issuerId;
	}

	public String getCertifiedId() {
		return certifiedId;
	}

	public void setCertifiedId(String certifiedId) {
		this.certifiedId = certifiedId;
	}

	public Date getIssued() {
		return issued;
	}

	public void setIssued(Date issued) {
		this.issued = issued;
	}

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getIcd() {
		return icd;
	}

	public void setIcd(String icd) {
		this.icd = icd;
	}

	public String getTherapeuticBehavior() {
		return therapeuticBehavior;
	}

	public void setTherapeuticBehavior(String therapeuticBehavior) {
		this.therapeuticBehavior = therapeuticBehavior;
	}

	public String getPrognosys() {
		return prognosys;
	}

	public void setPrognosys(String prognosys) {
		this.prognosys = prognosys;
	}

	public String getDiagnosys() {
		return diagnosys;
	}

	public void setDiagnosys(String diagnosys) {
		this.diagnosys = diagnosys;
	}

	public String getHealthConsequences() {
		return healthConsequences;
	}

	public void setHealthConsequences(String healthConsequences) {
		this.healthConsequences = healthConsequences;
	}

	public String getComplementaryExamsResults() {
		return complementaryExamsResults;
	}

	public void setComplementaryExamsResults(String complementaryExamsResults) {
		this.complementaryExamsResults = complementaryExamsResults;
	}

	@XmlTransient
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}