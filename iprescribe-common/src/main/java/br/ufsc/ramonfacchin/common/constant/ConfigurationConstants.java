package br.ufsc.ramonfacchin.common.constant;

/**
 * DatabaseConfiguration constant Keys.
 * 
 * @author ramonfacchin
 * 
 */
public interface ConfigurationConstants {

	/**
	 * Constants that affect the web module.
	 */
	public interface WEB {
		public static final String CONVERSATION_DURATION = "system.web.conversation.duration";
	}
	
	public interface SECURITY {
		/**
		 * defines the default hash algorithm for medical certificate generation
		 */
		public static final String MEDICAL_CERTIFICATE_CHECK_HASH_ALGORITHM = "system.security.medicalcertificate.hash.algorithm";
		/**
		 * defines the number of attempts for each hash algorithm when a collision
		 * occurs performing the hash generation for the medical certificate.
		 */
		public static final String HASH_GENERATION_ATTEMPTS = "system.security.medicalcertificate.hash.attempts";
	}

}
