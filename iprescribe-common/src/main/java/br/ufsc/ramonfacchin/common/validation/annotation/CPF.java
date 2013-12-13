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

import br.ufsc.ramonfacchin.common.validation.validator.CPFValidator;

/**
 * CPF Validation annotation.
 * Validation process includes formula validation.
 * 
 * @author Ramon Facchin / ramonfacchin@gmail.com
 * @since Apr 5, 2013
 * 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD })
@Constraint(validatedBy=CPFValidator.class)
public @interface CPF {

	String message() default "{validation.cpf.invalid}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	boolean formatted() default false;
}
