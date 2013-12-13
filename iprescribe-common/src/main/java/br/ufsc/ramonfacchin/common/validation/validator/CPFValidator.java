/**
 * 
 */
package br.ufsc.ramonfacchin.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import br.ufsc.ramonfacchin.common.utils.IPrescribeStringUtils;
import br.ufsc.ramonfacchin.common.validation.annotation.CPF;

/**
 * CPF Validator.
 *
 * @author Ramon Facchin / ramonfacchin@gmail.com
 * @since Apr 5, 2013
 *
 */
public class CPFValidator implements ConstraintValidator<CPF, String> {
	
	private boolean formatted;

	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(CPF constraintAnnotation) {
		formatted = constraintAnnotation.formatted();
	}

	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.isBlank(value)) {
			return false;
		}
		String pattern = null;
		if (formatted) {
			pattern = "\\d{3}.\\d{3}.\\d{3}-\\d{2}";
		} else {
			pattern = "\\d{11}";
		}
		boolean matchesPattern = value.matches(pattern);
		if (!matchesPattern) {
			return false;
		}
		
		//Para referencia sobre a formula do CPF: http://www.gerardocumentos.com.br/entenda-a-formula-do-cpf
		String onlyDigits = IPrescribeStringUtils.removerLettersAndSpecialChars(value);
		
		int sum = 0;
		int iterationCount = 0;
		while (iterationCount < 9) {
			int currentDigit = new Integer(onlyDigits.charAt(iterationCount)+"");
			sum = sum + ((10-iterationCount)*currentDigit);
			iterationCount++;
		}
		
		int remainder = sum % 11;
		
		int firstVerificationDigit = new Integer(onlyDigits.charAt(9)+"");
		int secondVerificationDigit = new Integer(onlyDigits.charAt(10)+"");
		int expectedVerificationDigit;
		
		expectedVerificationDigit = remainder < 2 ? 0 : 11-remainder;
		
		if (firstVerificationDigit != expectedVerificationDigit) {
			return false;
		}
		
		sum = 0;
		iterationCount = 0;
		while (iterationCount < 10) {
			int currentDigit = new Integer(onlyDigits.charAt(iterationCount)+"");
			sum = sum + ((11-iterationCount)*currentDigit);
			iterationCount++;
		}
		
		remainder = sum % 11;
		expectedVerificationDigit = remainder < 2 ? 0 : 11-remainder;

		return secondVerificationDigit == expectedVerificationDigit;
	}
	
	public void setFormatted(boolean formatted) {
		this.formatted = formatted;
	}
	
	public boolean getFormatted() {
		return this.formatted;
	}

}
