package br.ufsc.ramonfacchin.controller.mb.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufsc.ramonfacchin.common.constant.ConfigurationConstants;
import br.ufsc.ramonfacchin.common.constant.GlobalConstants;
import br.ufsc.ramonfacchin.common.utils.IPrescribeStringUtils;

@Named("urls")
@ApplicationScoped
public class UrlMB extends BaseConversationMB {

	private static final long serialVersionUID = -3746907871882324585L;

	@Inject
	Conversation conversation;
	
	protected void beginConversationIfTransient(long duration) {
		if ((conversation != null) && conversation.isTransient()) {
			conversation.begin();
			conversation.setTimeout(duration);
		}
	}

	protected void beginConversationIfTransient() {
		String durationStr = databaseConfigurationService.get(ConfigurationConstants.WEB.CONVERSATION_DURATION);
		beginConversationIfTransient(IPrescribeStringUtils.isBlank(durationStr) ? 5*GlobalConstants.TIME.MINUTE : new Long(durationStr));
	}
	
	protected void endConversationIfLongRunning() {
		if (conversation != null && !conversation.isTransient()) {
			conversation.end();
		}
	}

	private void performConversationAction(long conversationAction) {
		switch ((int) conversationAction) {
		case 1:
			beginConversationIfTransient();
			break;
		case 2:
			endConversationIfLongRunning();
			break;
		default:
			break;
		}
	}
	
	private String inferRedirectUrl(long conversationAction, String url) {
		if (((int)conversationAction) == 2)  {
			return url.concat("?faces-redirect=true");
		}
		return url;
	}
	
	public String getLogout(long conversationAction) {
		performConversationAction(conversationAction);
		return inferRedirectUrl(conversationAction,	GlobalConstants.WEB.URL.LOGOUT);
	}

	public String getLogin(long conversationAction) {
		performConversationAction(conversationAction);
		return inferRedirectUrl(conversationAction,	GlobalConstants.WEB.URL.LOGIN);
	}

	public String getHome(long conversationAction) {
		performConversationAction(conversationAction);
		return inferRedirectUrl(conversationAction,	GlobalConstants.WEB.URL.HOME);
	}

	public String getAuthcheck(long conversationAction) {
		performConversationAction(conversationAction);
		return inferRedirectUrl(conversationAction,	GlobalConstants.WEB.URL.AUTHCHECK);
	}

	public String getMdAddLicense(long conversationAction) {
		performConversationAction(conversationAction);
		return inferRedirectUrl(conversationAction,	GlobalConstants.WEB.URL.MD_ADD_LICENSE);
	}

	public String getMdCertificates(long conversationAction) {
		performConversationAction(conversationAction);
		return inferRedirectUrl(conversationAction,	GlobalConstants.WEB.URL.MD_CERTIFICATES);
	}

	public String getMdCertify(long conversationAction) {
		performConversationAction(conversationAction);
		return inferRedirectUrl(conversationAction,	GlobalConstants.WEB.URL.MD_CERTIFY);
	}

	public String getUserRegister(long conversationAction) {
		performConversationAction(conversationAction);
		return inferRedirectUrl(conversationAction,	GlobalConstants.WEB.URL.REGISTER);
	}

	public String getUserCertificates(long conversationAction) {
		performConversationAction(conversationAction);
		return inferRedirectUrl(conversationAction,	GlobalConstants.WEB.URL.USER_CERTIFICATES);
	}
	
	public String getViewCertificate(long conversationAction) {
		performConversationAction(conversationAction);
		return inferRedirectUrl(conversationAction,	GlobalConstants.WEB.URL.MD_VIEW_CERTIFICATE);
	}
	
	public String getViewUserCertificate(long conversationAction) {
		performConversationAction(conversationAction);
		return inferRedirectUrl(conversationAction,	GlobalConstants.WEB.URL.USER_VIEW_CERTIFICATE);
	}
	
	public String getLicenseRequest(long conversationAction) {
		performConversationAction(conversationAction);
		return inferRedirectUrl(conversationAction,	GlobalConstants.WEB.URL.ADMIN_LICENSE_REQUEST);
	}
	
	public String getConfigurations(long conversationAction) {
		performConversationAction(conversationAction);
		return inferRedirectUrl(conversationAction,	GlobalConstants.WEB.URL.ADMIN_CONFIGURATIONS);
	}

	public String getLogout() {
		return getLogout(0);
	}

	public String getLogin() {
		return getLogin(0);
	}

	public String getHome() {
		return getHome(0);
	}

	public String getAuthcheck() {
		return getAuthcheck(0);
	}

	public String getMdAddLicense() {
		return getMdAddLicense(0);
	}

	public String getMdCertificates() {
		return getMdCertificates(0);
	}

	public String getMdCertify() {
		return getMdCertify(0);
	}

	public String getUserRegister() {
		return getUserRegister(0);
	}

	public String getUserCertificates() {
		return getUserCertificates(0);
	}
	
	public String getViewCertificate() {
		return getViewCertificate(0);
	}
	
	public String getViewUserCertificate() {
		return getViewUserCertificate(0);
	}
	
	public String getLicenseRequest() {
		return getLicenseRequest(0);
	}
	
	public String getConfigurations() {
		return getConfigurations(0);
	}

	public String getContext() {
		return GlobalConstants.WEB.URL.CONTEXT;
	}

	public String getLogoutContext(long conversationAction) {
		return getContext()+getLogout(conversationAction);
	}

	public String getLoginContext(long conversationAction) {
		return getContext()+getLogin(conversationAction);
	}

	public String getHomeContext(long conversationAction) {
		return getContext()+getHome(conversationAction);
	}

	public String getAuthcheckContext(long conversationAction) {
		return getContext()+getAuthcheck(conversationAction);
	}

	public String getMdAddLicenseContext(long conversationAction) {
		return getContext()+getMdAddLicense(conversationAction);
	}

	public String getMdCertificatesContext(long conversationAction) {
		return getContext()+getMdCertificates(conversationAction);
	}

	public String getMdCertifyContext(long conversationAction) {
		return getContext()+getMdCertify(conversationAction);
	}

	public String getUserRegisterContext(long conversationAction) {
		return getContext()+getUserRegister(conversationAction);
	}

	public String getUserCertificatesContext(long conversationAction) {
		return getContext()+getUserCertificates(conversationAction);
	}
	
	public String getViewCertificateContext(long conversationAction) {
		return getContext()+getViewCertificate(conversationAction);
	}
	
	public String getViewUserCertificateContext(long conversationAction) {
		return getContext()+getViewUserCertificate(conversationAction);
	}
	
	public String getLicenseRequestContext(long conversationAction) {
		return getContext()+getLicenseRequest(conversationAction);
	}
	
	public String getConfigurationsContext(long conversationAction) {
		return getContext()+getConfigurations(conversationAction);
	}

	public String getLogoutContext() {
		return getLogoutContext(0);
	}

	public String getLoginContext() {
		return getLoginContext(0);
	}

	public String getHomeContext() {
		return getHomeContext(0);
	}

	public String getAuthcheckContext() {
		return getAuthcheckContext(0);
	}

	public String getMdAddLicenseContext() {
		return getMdAddLicenseContext(0);
	}

	public String getMdCertificatesContext() {
		return getMdCertificatesContext(0);
	}

	public String getMdCertifyContext() {
		return getMdCertifyContext(0);
	}

	public String getUserRegisterContext() {
		return getUserRegisterContext(0);
	}

	public String getUserCertificatesContext() {
		return getUserCertificatesContext(0);
	}
	
	public String getViewCertificateContext() {
		return getViewCertificateContext(0);
	}
	
	public String getViewUserCertificateContext() {
		return getViewUserCertificateContext(0);
	}
	
	public String getLicenseRequestContext() {
		return getLicenseRequestContext(0);
	}
	
	public String getConfigurationsContext() {
		return getContext()+getConfigurations(0);
	}
}
