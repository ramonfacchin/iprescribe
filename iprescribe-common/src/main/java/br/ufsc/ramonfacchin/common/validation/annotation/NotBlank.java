/**
 * 
 */
package br.ufsc.ramonfacchin.common.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.ufsc.ramonfacchin.common.validation.validator.NotBlankValidator;

/**
 * Annotation for validating fields that
 * will reject null values or {@link String}s 
 * with no chars but 'blank' or containing only
 * whitespaces.
 * 
 * @author Ramon Facchin / ramonfacchin@gmail.com
 * @since Apr 5, 2013
 * 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy=NotBlankValidator.class)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD })
public @interface NotBlank {

	String message() default "{validation.not.blank}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
