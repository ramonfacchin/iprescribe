package br.ufsc.ramonfacchin.common.interceptor;

import java.util.Date;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import br.ufsc.ramonfacchin.common.annotation.MethodAuditCDI;

/**
 * Interceptor for CDI methods annotated with {@link MethodAuditCDI}
 * 
 * @author ramonfacchin
 *
 */
@Interceptor
@MethodAuditCDI
public class MethodAuditCDIInterceptor {

	/**
	 * Interception that triggers whenever a method annotated with {@link MethodAuditCDI} is invoked.
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	@AroundInvoke
	public Object doMethodAudit(InvocationContext context) throws Exception {
		System.out.println("Invoked: " + context.getMethod().getDeclaringClass().getName() + "." + context.getMethod().getName() + " at " + new Date());
		return context.proceed();
	}

}
