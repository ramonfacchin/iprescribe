package br.ufsc.ramonfacchin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import br.ufsc.ramonfacchin.entity.base.BaseEntity;

@Entity
@XmlRootElement
@Table(name="medical_license")
@NamedQueries({
	@NamedQuery(name="MedicalLicense.listByUsername",query="select ml from MedicalLicense ml join ml.user as u where u.username = :username_arg"),
	@NamedQuery(name="MedicalLicense.listByUserId", query="select ml from MedicalLicense ml join ml.user as u where u.id = :userid_arg"),
	@NamedQuery(name="MedicalLicense.findByProfessionalId", query="select ml from MedicalLicense ml where ml.professionalId = :professionalid_arg"),
	@NamedQuery(name="MedicalLicense.listNotVerified", query="select ml from MedicalLicense ml where ml.verified is false")
})
public class MedicalLicense extends BaseEntity {

	private static final long serialVersionUID = 7795294218565840008L;
	
	@ManyToOne(fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="user_id",nullable=false,updatable=false)
	private User user;
	
	@NotEmpty
	@Column(name="professional_id",unique=true,nullable=false,updatable=false)
	private String professionalId;
	
	@NotEmpty
	@Column(name="country",nullable=false,updatable=false)
	private String country;
	
	@NotEmpty
	@Column(name="state",nullable=false,updatable=false)
	private String state;
	
	@Column(name="city")
	private String city;
	
	@Column(name="location")
	private String location;
	
	@Column(name="verified",nullable=false)
	private boolean verified = false;

	public String getProfessionalId() {
		return professionalId;
	}

	public void setProfessionalId(String professionalId) {
		this.professionalId = professionalId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public String getLabelString() {
		return getProfessionalId().concat(" ").concat(getState()).concat(" - ").concat(getCountry());
	}

}
