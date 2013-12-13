package br.ufsc.ramonfacchin.controller.mb.util;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

import org.apache.commons.lang.StringUtils;

import br.ufsc.ramonfacchin.common.utils.IPrescribeDateUtils;

@SessionScoped
@Named("internationalizationMB")
public class InternationalizationMB implements Serializable {

	private static final long serialVersionUID = -7819196376038693441L;

	private static Map<String, Object> availableLanguages;

	static {
		availableLanguages = new HashMap<String, Object>();
		availableLanguages.put("English (United States)", new Locale("en", "US"));
		availableLanguages.put("Portuguese (Brazil)", new Locale("pt", "BR"));
	}

	private String localeKey;

	public String getLocaleKey() {
		return localeKey;
	}

	public void setLocaleKey(String localeKey) {
		this.localeKey = localeKey;
	}

	public Map<String, Object> getAvailableLanguages() {
		return availableLanguages;
	}

	/**
	 * Treats changing display language event.
	 * @param event
	 */
	public void changeLocale(ValueChangeEvent event) {
		String newLocalKey = event.getNewValue().toString();
		for (Map.Entry<String, Object> entry : getAvailableLanguages().entrySet()) {

			if (entry.getValue().toString().equals(newLocalKey)) {

				Locale locale = (Locale) entry.getValue();
				FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
			}
		}
	}

	/**
	 * Changes the display language.
	 * @param language lowercase two-letter ISO-639 code.
	 * @param country uppercase two-letter ISO-3166 code.
	 */
	public void changeLocale(String language, String country) {
		String newLocalKey = language + (StringUtils.isNotBlank(country) ? "_"+country : "");
		for (Map.Entry<String, Object> entry : getAvailableLanguages().entrySet()) {

			if (entry.getValue().toString().equals(newLocalKey)) {

				Locale locale = (Locale) entry.getValue();
				FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);

			}
		}
	}
	
	public String getI18nString(String key, Locale locale) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, "messages");
		return bundle.getString(key);
	}
	
	public String getFormattedDate(Date date, String pattern) {
		return IPrescribeDateUtils.formatDate(pattern, date);
	}

}
