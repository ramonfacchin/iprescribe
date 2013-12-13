package br.ufsc.ramonfacchin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import br.ufsc.ramonfacchin.common.validation.annotation.NotBlank;
import br.ufsc.ramonfacchin.entity.base.BaseEntity;

@Entity
@XmlRootElement
@Table(name = "database_configuration")
public class DatabaseConfiguration extends BaseEntity {

	private static final long serialVersionUID = -1651247209034806066L;

	@NotBlank
	@Column(name = "config_key", nullable = false, unique = true)
	private String key;

	@Column(name = "config_value")
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = (prime * result) + ((key == null) ? 0 : key.hashCode());
		result = (prime * result) + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		DatabaseConfiguration other = (DatabaseConfiguration) obj;
		if (key == null) {
			if (other.key != null) {
				return false;
			}
		} else if (!key.equals(other.key)) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

}
