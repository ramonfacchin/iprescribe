package br.ufsc.ramonfacchin.enumeration;

public enum GenderEnum {

	MALE, FEMALE;

	public String getI18nKey() {
		return getClass().getCanonicalName().concat(".").concat(this.name());
	}

}
