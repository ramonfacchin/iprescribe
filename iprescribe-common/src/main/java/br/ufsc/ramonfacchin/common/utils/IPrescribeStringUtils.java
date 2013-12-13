/**
 * 
 */
package br.ufsc.ramonfacchin.common.utils;

import java.io.ByteArrayOutputStream;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.jasypt.digest.config.SimpleDigesterConfig;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

/**
 * Complementary methods for {@link StringUtils} from Apache Commons Lang.
 * 
 * @author Ramon Facchin / ramonfacchin@gmail.com
 * @since Apr 5, 2013
 * 
 */
public class IPrescribeStringUtils extends StringUtils {
	private static ConfigurablePasswordEncryptor passwordEncryptor;
	static {
		passwordEncryptor = new ConfigurablePasswordEncryptor();
		passwordEncryptor.setAlgorithm("SHA-512");
		passwordEncryptor.setPlainDigest(false);
		passwordEncryptor.setConfig(new SimpleDigesterConfig());
	}

	/**
	 * Remove digits and special chars from {@link String}, keeping only
	 * alphabetical chars.
	 * 
	 * @param s
	 *            the {@link String} where the replacement will occur.
	 * @return the resulting {@link String} without digit nor special
	 *         characters. <code>null</code> if blank String given.
	 */
	public static String removeDigitsAndSpecialChars(String s) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(s)) {
			for (Character c : s.toCharArray()) {
				if (Character.isLetter(c)) {
					sb.append(c);
				}
			}
			return sb.toString();
		}
		return null;
	}

	/**
	 * Remove alphabetical chars and special chars from {@link String}, keeping
	 * only digits.
	 * 
	 * @param s
	 *            the {@link String} where the replacement will occur.
	 * @return the resulting {@link String} without alphabetical chars nor
	 *         special characters. <code>null</code> if blank String given.
	 */
	public static String removerLettersAndSpecialChars(String s) {
		StringBuilder sb = new StringBuilder();
		if (StringUtils.isNotBlank(s)) {
			for (Character c : s.toCharArray()) {
				if (Character.isDigit(c)) {
					sb.append(c);
				}
			}
			return sb.toString();
		}
		return null;
	}

	/**
	 * Replaces all EL occurrences on the given {@link String} based on the
	 * given {@link Map}.
	 * 
	 * @param text
	 *            the text containing entries following the pattern: #{somekey}
	 * @param replacementMap
	 *            {@link Map} with the replacements to be done. Existent keys
	 *            with no occurrences in the text will be ignored. And EL
	 *            occurrences with no corresponding key in the {@link Map} will
	 *            remain in the text as they are.
	 * @return the text with the informed replacements.
	 */
	public static String parseEL(String text, Map<String, String> replacementMap) {
		if ((replacementMap == null) || StringUtils.isBlank(text)) {
			return text;
		}
		String replacedText = text;
		for (String chave : replacementMap.keySet()) {
			String elKey = "#{" + chave + "}";
			String value = replacementMap.get(chave);
			replacedText = replacedText.replace(elKey, value);
		}
		return replacedText;
	}

	/**
	 * 
	 * Given a filename, returns its extension.
	 * 
	 * @param filename
	 * @return the file extension, starting with '.'. Returns <code>null</code>
	 *         if no extension found.
	 */
	public static String parseFileExtension(String filename) {
		if (isNotBlank(filename) && filename.contains(".")) {
			return filename.substring(filename.lastIndexOf(".")).toLowerCase();
		}
		return null;
	}

	/**
	 * Remove accent from accented chars and replace compund chars (like ccedil
	 * > c).
	 * 
	 * @param text
	 * @return the normalized text.
	 */
	public static String normalize(String text) {
		String normalizedString = Normalizer.normalize(text, Form.NFD);
		normalizedString = normalizedString.replaceAll("[^\\p{ASCII}]", "");
		return normalizedString;
	}

	/**
	 * Given a password, returns it encrypted.
	 * 
	 * @param password
	 * @return the encrypted password
	 */
	public static String encryptPassword(String password) {
		return passwordEncryptor.encryptPassword(password);
	}

	/**
	 * Password match check.
	 * 
	 * @param password
	 *            the password to be checked
	 * @param encryptedPassword
	 *            the encrypted password that given plain password must match
	 * @return <code>true</code> if given password matches encrypted password,
	 *         <code>false</code> otherwise.
	 */
	public static boolean checkPassword(String password, String encryptedPassword) {
		return passwordEncryptor.checkPassword(password, encryptedPassword);
	}

	/**
	 * Checks password safety.
	 * 
	 * @param password
	 * @return 0 = No safety; 1 = Low safety; 2 = Regular safety; 3 = Good
	 *         safety
	 */
	public static int checkPasswordSafety(String password) {
		if (isNotBlank(password)) {
			int letterCount = 0;
			int digitCount = 0;
			int specialCharCount = 0;
			for (Character c : password.toCharArray()) {
				if ((letterCount == 0) && Character.isLetter(c)) {
					letterCount++;
				}
				if ((digitCount == 0) && Character.isDigit(c)) {
					digitCount++;
				}
				if ((specialCharCount == 0) && !Character.isDigit(c) && !Character.isLetter(c)) {
					specialCharCount++;
				}
			}
			return letterCount + digitCount + specialCharCount;
		}
		return 0;
	}

	/**
	 * @return a secure Random Number.
	 */
	public static String generateRandomString(int numberOfChars) {
		return RandomStringUtils.random(numberOfChars, "ABCDEF0123456789");
	}
	
	/**
	 * @param content
	 * @return the SHA1 Hex String calculated for the given content.
	 */
	public static String sha1Hex(byte[] content) {
		return DigestUtils.sha1Hex(content);
	}
	
	/**
	 * @param content
	 * @return the SHA256 Hex String calculated for the given content.
	 */
	public static String sha256Hex(byte[] content) {
		return DigestUtils.sha256Hex(content);
	}
	
	/**
	 * @param content
	 * @return the SHA512 Hex String calculated for the given content.
	 */
	public static String sha512Hex(byte[] content) {
		return DigestUtils.sha512Hex(content);
	}
	
	/**
	 * @param entity (an object annotated with @{@link XmlRootElement})
	 * @return The XML representation of the given entity or <code>null</code> if there was an error parsing
	 */
	public static String parseEntityToXmlString(Object entity) {
		try {
			JAXBContext context = JAXBContext.newInstance(entity.getClass());
			Marshaller marshaller = context.createMarshaller();
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			marshaller.marshal(entity, os);
			byte[] byteArray = os.toByteArray();
			String xml = new String(byteArray);
			return xml;
		} catch (Exception e) {
			return null;
		}
	}

}
