package br.ufsc.ramonfacchin.controller.mb.util;

import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

import br.ufsc.ramonfacchin.common.constant.ConfigurationConstants;
import br.ufsc.ramonfacchin.common.constant.GlobalConstants;
import br.ufsc.ramonfacchin.common.utils.IPrescribeStringUtils;
import br.ufsc.ramonfacchin.service.IDatabaseConfigurationServiceLocal;

public abstract class BaseConversationMB extends BaseMB {

	private static final long serialVersionUID = -72192644551050144L;

	@Inject
	Conversation conversation;
	
	@Inject
	protected UrlMB urlMB;

	@EJB
	IDatabaseConfigurationServiceLocal databaseConfigurationService;

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

}
