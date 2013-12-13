package br.ufsc.ramonfacchin.enumeration;

public enum HashAlgorithm {
	SHA1,SHA256,SHA512;

	public String getI18nKey() {
		return getClass().getCanonicalName().concat(".").concat(this.name());
	}
}
