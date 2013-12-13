package br.ufsc.ramonfacchin.controller.mb.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;

import br.ufsc.ramonfacchin.common.constant.GlobalConstants;
import br.ufsc.ramonfacchin.controller.mb.login.AuthenticationMB;

/**
 * Base Managed Bean (for inheritance purposes).
 * 
 * @author Ramon Facchin
 * @version $Id: $
 * @since 0.0.0.0
 *
 */
public abstract class BaseMB implements Serializable {

	private static final long serialVersionUID = -6813295797914907344L;
	
	@Inject
	protected InternationalizationMB internationalizationMB;
	
	@Inject
	protected Conversation conversation;
	
	@Inject
	protected AuthenticationMB authenticationMB;

	/**
	 * Get I18N value for the given key.
	 * @param key messages*.properties file key.
	 * @return I18N Message found for the given key. If none found, returns the key itself.
	 */
	protected String getI18nMessage(String key) {
		ResourceBundle messages = FacesContext.getCurrentInstance().getApplication().getResourceBundle(FacesContext.getCurrentInstance(), "messages");
		try {
			String message = messages.getString(key);
			return StringUtils.isBlank(message) ? key : message;
		} catch (Exception e) {
			return "???".concat(key).concat("???");
		}
	}
	
	/**
	 * Get I18N value for the given key.
	 * @param key messages*.properties file key.
	 * @param args the args for ordinal parameters in the message
	 * @return I18N Message found for the given key. If none found, returns the key itself.
	 */
	protected String getI18nMessage(String key, String[] args) {
		String msg = getI18nMessage(key);
		MessageFormat format = new MessageFormat(msg);
		return format.format(args);
	}
	
	public String getLoginUrl() {
		return GlobalConstants.WEB.URL.CONTEXT+GlobalConstants.WEB.URL.LOGIN;
	}
	
	public String getHomeUrl() {
		return GlobalConstants.WEB.URL.HOME;
	}
	
	public String getLogoutUrl() {
		return GlobalConstants.WEB.URL.LOGOUT;
	}
	
	public String downloadPdf(String filename, ByteArrayOutputStream outputStream, String pageRedirect) {
		FacesContext fc = FacesContext.getCurrentInstance();
	    ExternalContext ec = fc.getExternalContext();
	    ec.responseReset();
	    ec.setResponseContentType("application/pdf");
	    ec.setResponseContentLength(outputStream.size());
	    ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

	    try {
			OutputStream output = ec.getResponseOutputStream();
			output.write(outputStream.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}

	    fc.responseComplete();
		return pageRedirect;
	}
	
	public void addMessage(Severity severity, String clientId, String summary, String detail) {
		FacesMessage msg = new FacesMessage(severity, summary, detail);
		FacesContext.getCurrentInstance().addMessage(clientId, msg);
		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	}
	
}
