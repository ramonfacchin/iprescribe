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

import br.ufsc.ramonfacchin.common.validation.validator.CNPJValidator;

/**
 * Anotação de validação para CNPJ.
 * Valida inclusive a fórmula de geração.
 * 
 * @author Ramon Facchin / ramon@gmail.com
 * @since Aug 22, 2013
 * 
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD })
@Constraint(validatedBy=CNPJValidator.class)
public @interface CNPJ {

	String message() default "{validacao.cnpj.invalido}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	boolean formatado() default false;
}
