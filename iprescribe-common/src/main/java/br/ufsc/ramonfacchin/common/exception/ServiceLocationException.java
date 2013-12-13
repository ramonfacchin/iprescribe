package br.ufsc.ramonfacchin.common.exception;

/**
 * General Exception for EJB Lookup Issues.
 * 
 * @author ramon
 * @version $Id: $
 * @since 0.0.0.0
 *
 */
public class ServiceLocationException extends Exception {

	private static final long serialVersionUID = 1225637680085618210L;
	
	private String msg;
	
	public ServiceLocationException(String message) {
		msg = message;
	}
	
	@Override
	public String getMessage() {
		return msg;
	}

}
