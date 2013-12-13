package br.ufsc.ramonfacchin.common.interceptor;

import java.util.Date;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.ufsc.ramonfacchin.common.annotation.MethodAudit;

/**
 * Interceptor for EJB methdos annotated with {@link MethodAudit}.
 * 
 * @author ramonfacchin
 *
 */
@Interceptor
@MethodAudit
public class MethodAuditInterceptor {
	
	/**
	 * Interception that triggers whenever a method annotated with {@link MethodAudit} is invoked.
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	@AroundInvoke
	public final static Object doMethodAudit(InvocationContext context) throws Exception {
		System.out.println("Invoked: " + context.getMethod().getDeclaringClass().getName() + "." + context.getMethod().getName() + " at " + new Date());
		return context.proceed();
	}

}
