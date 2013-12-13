/**
 * 
 */
package br.ufsc.ramonfacchin.common.interceptors;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.ufsc.ramonfacchin.common.interceptors.annotation.AuditableMethod;

/**
 * Class implementing {@link AuditableMethod}.
 * <br/><br/>
 * Audits the method invocation.
 *
 * @author Ramon Facchin / ramonfacchin@gmail.com
 * @since May 10, 2013
 *
 */
@Interceptor
@AuditableMethod
public class AuditableMethodInterceptor implements Serializable {
	
	private static final Logger logger = Logger.getLogger(AuditableMethodInterceptor.class.getName());
	
	private static final long serialVersionUID = 6558780667946880710L;

	@AroundInvoke
	public Object auditMethod(InvocationContext invocationContext) throws Exception {
		String invokerClass = invocationContext.getMethod().getDeclaringClass().getName();
		String methodName = invocationContext.getMethod().getName();
		Date dateBefore = new Date();
		logger.info("[Begin] Method invocation: " + invokerClass + "." + methodName + " at " + dateBefore);
		Object proceed = invocationContext.proceed();
		Date dateAfter = new Date();
		logger.info("[End]   Method invocation: " + invokerClass + "." + methodName + " at " + dateAfter);
		return proceed;
	}

}
