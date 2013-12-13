/**
 * 
 */
package br.ufsc.ramonfacchin.common.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

import br.ufsc.ramonfacchin.common.validation.annotation.NotBlank;

/**
 * Validator for non blank fields.
 *
 * @author Ramon Facchin / ramonfacchin@gmail.com
 * @since Apr 5, 2013
 *
 */
public class NotBlankValidator implements ConstraintValidator<NotBlank, String> {

	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(NotBlank constraintAnnotation) {
	}

	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return StringUtils.isNotBlank(value);
	}

}
