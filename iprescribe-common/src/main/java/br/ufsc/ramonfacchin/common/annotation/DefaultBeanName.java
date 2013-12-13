package br.ufsc.ramonfacchin.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that defines the EJB JNDI name that should be looked up 
 * (if none name found in service-configuration.properties, inside the 
 * client module JAR).
 * 
 * @author Ramon Facchin
 * @version $Id: $
 * @since 0.0.0.0
 * 
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultBeanName {

	/**
	 * Should be filled with <b>the explicitly declared EJB name (if any given)</b>, 
	 * otherwise, value should be filled with the <b>class Simple Name</b>
	 */
	String value();
}