package br.ufsc.ramonfacchin.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;
import javax.interceptor.InterceptorBinding;

/**
 * Annotation for auditing methods of CDI beans.
 * 
 * @author ramonfacchin
 *
 */
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Qualifier
public @interface MethodAuditCDI {

}
