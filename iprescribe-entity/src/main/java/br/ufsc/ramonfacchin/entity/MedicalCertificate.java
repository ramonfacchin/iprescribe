package br.ufsc.ramonfacchin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import br.ufsc.ramonfacchin.common.validation.annotation.NotBlank;
import br.ufsc.ramonfacchin.entity.base.BaseEntity;

@Entity
@XmlRootElement
@Table(name = "medical_certificate")
@NamedQueries({
	@NamedQuery(name="MedicalCertificate.listByIssuerId",query="select obj from MedicalCertificate obj join obj.issuerLicense as issuerLicense where issuerLicense.user.id = :issuerid_arg order by obj.issued desc"),
	@NamedQuery(name="MedicalCertificate.listByIssuerCitizenRegistry",query="select obj from MedicalCertificate obj join obj.issuerLicense as issuerLicense where issuerLicense.user.identity.citizenRegistry = :issuerregistry_arg order by obj.issued desc"),
	@NamedQuery(name="MedicalCertificate.listByCertifiedId",query="select obj from MedicalCertificate obj join obj.certified as certified where certified.id = :certifiedid_arg order by obj.issued desc"),
	@NamedQuery(name="MedicalCertificate.listByCertifiedCitizenRegistry",query="select obj from MedicalCertificate obj join obj.certified as certified where certified.citizenRegistry = :certifiedregistry_arg order by obj.issued desc"),
	@NamedQuery(name="MedicalCertificate.listByIssuerIdValidOnly",query="select obj from MedicalCertificate obj join obj.issuerLicense as issuerLicense where issuerLicense.user.id = :issuerid_arg and obj.validity >= :now order by obj.issued desc"),
	@NamedQuery(name="MedicalCertificate.listByIssuerCitizenRegistryValidOnly",query="select obj from MedicalCertificate obj join obj.issuerLicense as issuerLicense where issuerLicense.user.identity.citizenRegistry = :issuerregistry_arg and obj.validity >= :now order by obj.issued desc"),
	@NamedQuery(name="MedicalCertificate.listByCertifiedIdValidOnly",query="select obj from MedicalCertificate obj join obj.certified as certified where certified.id = :certifiedid_arg and obj.validity >= :now order by obj.issued desc"),
	@NamedQuery(name="MedicalCertificate.listByCertifiedCitizenRegistryValidOnly",query="select obj from MedicalCertificate obj join obj.certified as certified where certified.citizenRegistry = :certifiedregistry_arg and obj.validity >= :now order by obj.issued desc"),
	@NamedQuery(name="MedicalCertificate.findByRegistryHash", query="select certificate from MedicalCertificateRegistry registry join registry.medicalCertificate as certificate where registry.hash = :hash_arg order by certificate.issued desc")
})
public class MedicalCertificate extends BaseEntity {

	private static final long serialVersionUID = 3377986351800857952L;

	/**
	 * References the issuer of the Medical Certificate.
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "medical_license_id", nullable = false, updatable = false)
	private MedicalLicense issuerLicense;

	/**
	 * References the person certified by the Medical Certificate.
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "identity_id", nullable = false, updatable = false)
	private Identity certified;

	/**
	 * Date and Time when the Medical Certificate was made.
	 */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_issued", nullable = false, updatable = false)
	private Date issued;

	/**
	 * Date and Time until when the Medical Certificate is valid.
	 */
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_validity", nullable = false, updatable = false)
	private Date validity;

	/**
	 * Goal for which the certificate was emitted.
	 */
	@NotBlank
	@Column(name = "goal", updatable = false)
	private String goal;

	/**
	 * Comments regarding the certificate and the context it was emitted.
	 */
	@Column(name = "comments", updatable = false)
	private String comments;
	
	/**
	 * International Disease Code (ICD)
	 */
	@Column(name = "icd", updatable = false)
	private String icd;
	
	/**
	 * Therapeutic behavior of the diagnosys.
	 */
	@Column(name = "therapeutic_behavior", updatable = false)
	private String therapeuticBehavior;
	
	/**
	 * Prognosys
	 */
	@Column(name = "prognosys", updatable = false)
	private String prognosys;
	
	/**
	 * Diagnosys
	 */
	@Column(name = "diagnosys", updatable = false)
	private String diagnosys;
	
	/**
	 * Health consequences
	 */
	@Column(name = "health_consequences", updatable = false)
	private String healthConsequences;
	
	/**
	 * Complementary exams results
	 */
	@Column(name = "complementary_exams_results", updatable = false)
	private String complementaryExamsResults;

	@XmlTransient
	public Identity getCertified() {
		return certified;
	}

	public void setCertified(Identity certified) {
		this.certified = certified;
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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
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
	public MedicalLicense getIssuerLicense() {
		return issuerLicense;
	}

	public void setIssuerLicense(MedicalLicense issuerLicense) {
		this.issuerLicense = issuerLicense;
	}

}
