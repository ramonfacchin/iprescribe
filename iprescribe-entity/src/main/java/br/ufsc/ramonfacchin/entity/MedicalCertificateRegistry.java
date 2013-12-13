package br.ufsc.ramonfacchin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.ufsc.ramonfacchin.entity.base.BaseEntity;
import br.ufsc.ramonfacchin.enumeration.HashAlgorithm;

@XmlRootElement
@Entity
@Table(name = "medical_certificate_registry")
@NamedQueries({
	@NamedQuery(name="MedicalCertificateRegistry.findByHash", query="select registry from MedicalCertificateRegistry registry where registry.hash = :hash_arg"),
	@NamedQuery(name="MedicalCertificateRegistry.findByCertificateId", query="select registry from MedicalCertificateRegistry registry join registry.medicalCertificate as certificate where certificate.id = :certificateid_arg")
})
public class MedicalCertificateRegistry extends BaseEntity {

	private static final long serialVersionUID = 7754861704700281108L;

	@OneToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "medical_certificate_id", nullable = false, updatable = false)
	private MedicalCertificate medicalCertificate;

	@Column(name = "hash", nullable = false, updatable = false, unique = true)
	private String hash;

	@Enumerated(EnumType.STRING)
	@Column(name = "algorithm", nullable = false, updatable = false)
	private HashAlgorithm algorithm;

	public MedicalCertificate getMedicalCertificate() {
		return medicalCertificate;
	}

	public void setMedicalCertificate(MedicalCertificate medicalCertificate) {
		this.medicalCertificate = medicalCertificate;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public HashAlgorithm getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(HashAlgorithm algorithm) {
		this.algorithm = algorithm;
	}

}
