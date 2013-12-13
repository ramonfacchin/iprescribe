package br.ufsc.ramonfacchin.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.ufsc.ramonfacchin.common.enumeration.LoggerLevel;

/**
 * Annotation for Method Auditing interceptor.
 * 
 * @author ramonfacchin
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface MethodAudit {
	LoggerLevel level() default LoggerLevel.INFO;
}
