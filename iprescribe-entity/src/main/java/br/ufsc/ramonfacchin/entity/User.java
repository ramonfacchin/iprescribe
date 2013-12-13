package br.ufsc.ramonfacchin.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.ufsc.ramonfacchin.common.utils.IPrescribeStringUtils;
import br.ufsc.ramonfacchin.common.validation.annotation.NotBlank;
import br.ufsc.ramonfacchin.entity.base.BaseEntity;

@XmlRootElement
@Entity
@Table(name = "user")
@NamedQueries(value = { 
	@NamedQuery(name = "User.findByEmail", query = "select obj from User obj where lower(obj.email) = lower(:email_arg)"),
	@NamedQuery(name = "User.findByUsername", query = "select obj from User obj where lower(obj.username) = lower(:username_arg)"),
	@NamedQuery(name = "User.findByCitizenRegistry", query = "select obj from User obj where lower(obj.identity.citizenRegistry) = lower(:citizenregistry_arg)") 
})
public class User extends BaseEntity implements Comparable<User> {

	private static final long serialVersionUID = -8066899223261666003L;

	@NotBlank
	@Column(name = "username", nullable = false, unique = true)
	private String username;

	@NotBlank
	@Column(name = "password", nullable = false)
	private String password;

	@NotBlank
	@Column(name = "email",unique=true,nullable=false)
	private String email;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "identity_id", nullable = false, unique = true)
	private Identity identity;

	@Override
	public int compareTo(User arg0) {
		if (arg0 == null) {
			return -1;
		}
		if (IPrescribeStringUtils.isNotBlank(getUsername()) && IPrescribeStringUtils.isNotBlank(arg0.getUsername())) {
			return getUsername().compareTo(arg0.getUsername());
		}
		return 0;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Identity getIdentity() {
		return identity;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

}
