package br.ufsc.ramonfacchin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import br.ufsc.ramonfacchin.common.validation.annotation.CPF;
import br.ufsc.ramonfacchin.common.validation.annotation.NotBlank;
import br.ufsc.ramonfacchin.entity.base.BaseEntity;
import br.ufsc.ramonfacchin.enumeration.GenderEnum;

@XmlRootElement
@Entity
@Table(name = "identity")
@NamedQueries(value = { @NamedQuery(name = "Identity.findByName", query = "select obj from Identity obj where upper(obj.name) like :name_arg"), @NamedQuery(name = "Identity.findByBirth", query = "select obj from Identity obj where obj.birth = :birth_arg"), @NamedQuery(name = "Identity.findByNameAndBirth", query = "select obj from Identity obj where upper(obj.name) like :name_arg and obj.birth = :birth_arg"), @NamedQuery(name = "Identity.findByUsername", query = "select obj from User u join u.identity obj where lower(u.username) = lower(:username_arg)"),
		@NamedQuery(name="Identity.findByCitizenRegistry", query="select obj from Identity obj where trim(upper(obj.citizenRegistry)) = trim(upper(:citizenregistry_arg))")})
public class Identity extends BaseEntity {

	private static final long serialVersionUID = 7754396811750432136L;

	@CPF(formatted=false)
	@NotBlank
	@Column(name = "citizen_registry", unique = true)
	private String citizenRegistry;

	@NotBlank
	@Column(name = "name", nullable = false)
	private String name;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "birth", nullable = false)
	private Date birth;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "gender", nullable = false)
	private GenderEnum gender;

	public String getCitizenRegistry() {
		return citizenRegistry;
	}

	public void setCitizenRegistry(String citizenRegistry) {
		this.citizenRegistry = citizenRegistry;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

}
