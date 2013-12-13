package br.ufsc.ramonfacchin.controller.mb.crud;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;
import javax.inject.Named;

import br.ufsc.ramonfacchin.common.constant.GlobalConstants;
import br.ufsc.ramonfacchin.common.validation.annotation.NotBlank;
import br.ufsc.ramonfacchin.controller.mb.util.BaseCrudMB;
import br.ufsc.ramonfacchin.entity.Identity;
import br.ufsc.ramonfacchin.entity.User;
import br.ufsc.ramonfacchin.service.IIdentityServiceLocal;
import br.ufsc.ramonfacchin.service.IServiceLocal;
import br.ufsc.ramonfacchin.service.IUserServiceLocal;

@Named("userCrudMB")
@ConversationScoped
public class UserCrudMB extends BaseCrudMB<User> {

	private static final long serialVersionUID = -4555960939381618434L;

	@EJB
	IUserServiceLocal userService;

	@EJB
	IIdentityServiceLocal identityService;

	@NotBlank
	private String repeatPassword;
	
	@PostConstruct
	private void init() {
		mock();
	}

	@Override
	protected IServiceLocal<User> getService() {
		return userService;
	}

	@Override
	protected String getCreateURL() {
		return "/iprescribe-web"+GlobalConstants.WEB.URL.REGISTER;
	}

	@Override
	protected String getReadURL() {
		return null;
	}

	@Override
	protected String getUpdateURL() {
		return null;
	}

	@Override
	protected String getDeleteURL() {
		return null;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	@Override
	public User getEntityForm() {
		User entityForm = super.getEntityForm();
		if (entityForm == null) {
			entityForm = new User();
		}
		if (entityForm.getIdentity() == null) {
			entityForm.setIdentity(new Identity());
		}
		setEntityForm(entityForm);
		return entityForm;
	}
	
	@Override
	protected void doBeforeCreate() {
		List<FacesMessage> errors = new ArrayList<FacesMessage>();
		boolean passwordConfirmed = getEntityForm().getPassword().equals(getRepeatPassword());
		if (!passwordConfirmed) {
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(getI18nMessage("user.register.validation.password.notmatch.summary"));
			msg.setDetail(getI18nMessage("user.register.validation.password.notmatch.detail"));
			errors.add(msg);
		}
		User alreadyExists = userService.findByUsername(getEntityForm().getUsername());
		if (alreadyExists != null) {
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(getI18nMessage("user.register.validation.username.alreadyexists.summary"));
			msg.setDetail(getI18nMessage("user.register.validation.username.alreadyexists.detail"));
			errors.add(msg);
		}
		alreadyExists = null;
		alreadyExists = userService.findByEmail(getEntityForm().getEmail());
		if (alreadyExists != null) {
			FacesMessage msg = new FacesMessage();
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			msg.setSummary(getI18nMessage("user.register.validation.email.alreadyexists.summary"));
			msg.setDetail(getI18nMessage("user.register.validation.email.alreadyexists.detail"));
			errors.add(msg);
		}
		if (!errors.isEmpty()) {
			throw new ValidatorException(errors);
		}
	}
	
	public void citizenRegistryFilled(AjaxBehaviorEvent event) {
		//TODO: add messages to inform user of the UI decisions
		String citizenRegistry = getEntityForm().getIdentity().getCitizenRegistry();
		Identity identity = identityService.findByCitizenRegistry(citizenRegistry);
		if (identity != null) {
			//means that the given citizen registry is already associated with an identity 
			User user = userService.findByCitizenRegistry(citizenRegistry);
			if (user != null) {
				//there is already an user for the given identity
				FacesMessage msg = null;
				getEntityForm().setIdentity(new Identity());
				msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, getI18nMessage("user.register.error.identity.alreadyassociated.summary"), getI18nMessage("user.register.error.identity.alreadyassociated.detail"));
				FacesContext.getCurrentInstance().addMessage("register-user:citizenregistry", msg);
			} else {
				//identity ok
				getEntityForm().setIdentity(identity);
			}
		} else {
			//means that the given citizen registry is not associated with any identity
			getEntityForm().setIdentity(new Identity());
			getEntityForm().getIdentity().setCitizenRegistry(citizenRegistry);
		}
	}

}
