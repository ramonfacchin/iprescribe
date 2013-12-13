package br.ufsc.ramonfacchin.common.exception;

/**
 * Exception to throw on hash collisions for medical certificate registries.
 * 
 * @author ramonfacchin
 *
 */
public class HashAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -4799594384161451678L;
	
	private String hash;
	
	private String algorithm;
	
	public HashAlreadyExistsException(String hash, String algorithm) {
		super("error.hash.already.exists");
		this.hash = hash;
		this.algorithm = algorithm;
	}

	public String getHash() {
		return hash;
	}

	public String getAlgorithm() {
		return algorithm;
	}

}
