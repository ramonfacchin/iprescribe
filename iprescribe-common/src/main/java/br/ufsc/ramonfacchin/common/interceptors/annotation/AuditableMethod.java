/**
 * 
 */
package br.ufsc.ramonfacchin.common.interceptors.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;
import javax.interceptor.InterceptorBinding;

import br.ufsc.ramonfacchin.common.interceptors.AuditableMethodInterceptor;

/**
 * Interceptor for method audit purposes.
 * <br/><br/>
 * Every invocation of the annotated method
 * triggers the audit, and provides the complete
 * invocation context (parameters, classes etc).
 * <br/><br/>
 * Methods will be intercepted by: {@link AuditableMethodInterceptor}
 * 
 * @author Ramon Facchin / ramonfacchin@gmail.com
 * @since May 10, 2013
 * 
 */
@Inherited
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
@Qualifier
public @interface AuditableMethod {

}
