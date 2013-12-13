/**
 * 
 */
package br.ufsc.ramonfacchin.controller.mb.util;

import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.ufsc.ramonfacchin.entity.base.BaseEntity;
import br.ufsc.ramonfacchin.service.IServiceLocal;

/**
 * TODO Descrição da classe.
 *
 * <h3>Bry Tecnologia - 2013</h3>
 * 
 * @author TODO Nome / TODO Email
 * @since Aug 21, 2013
 *
 */
@Dependent
public abstract class BaseCrudMB<T extends BaseEntity> extends BaseConversationMB {

	private static final long serialVersionUID = 8034638415623337249L;
	
	protected T entityForm;

	private boolean mock = false;
	
	protected abstract IServiceLocal<T> getService();
	
	protected abstract String getCreateURL();
	
	protected abstract String getReadURL();
	
	protected abstract String getUpdateURL();
	
	protected abstract String getDeleteURL();
	
	protected String getCreateErrorURL() {
		return null;
	}
	
	protected String getReadErrorURL() {
		return null;
	}
	
	protected String getUpdateErrorURL() {
		return null;
	}
	
	protected String getDeleteErrorURL() {
		return null;
	}
	
	public String create() {
		try {
			doBeforeCreate();
		} catch (ValidatorException e) {
			treatErrorMessages(e);
			return getCreateErrorURL();
		}
		doCreate();
		doAfterCreate();
		return getCreateURL();
	}
	
	public String update() {
		try {
			doBeforeUpdate();
		} catch (ValidatorException e) {
			treatErrorMessages(e);
			return getUpdateErrorURL();
		}
		doUpdate();
		doAfterUpdate();
		return getUpdateURL();
	}
	
	public String delete() {
		try {
			doBeforeDelete();
		} catch (ValidatorException e) {
			treatErrorMessages(e);
			return getDeleteErrorURL();
		}
		doDelete();
		doAfterDelete();
		return getDeleteURL();
	}
	
	public String read(Long id) {
		try {
			doBeforeRead(id);
		} catch (ValidatorException e) {
			treatErrorMessages(e);
			return getReadErrorURL();
		}
		doRead(id);
		doAfterRead(id);
		return getReadURL();
	}

	public T getEntityForm() {
		return entityForm;
	}

	public void setEntityForm(T entityForm) {
		this.entityForm = entityForm;
	}
	
	protected void doBeforeCreate() {
		
	}
	
	protected void doCreate() {
		if (!mock) {
			getService().save(getEntityForm());
		}
	}
	
	protected void doAfterCreate() {
		addMessage(FacesMessage.SEVERITY_INFO, null, getCreateSuccessMessageSummary(), getCreateSuccessMessageDetail());
	}
	
	protected void doBeforeUpdate() {
		
	}
	
	protected void doUpdate() {
		if (!mock) {
			getService().save(getEntityForm());
		}
	}
	
	protected void doAfterUpdate() {
		addMessage(FacesMessage.SEVERITY_INFO, null, getUpdateSuccessMessageSummary(), getUpdateSuccessMessageDetail());
	}
	
	protected void doBeforeRead(Long id) {
		
	}
	
	protected void doRead(Long id) {
		setEntityForm(getService().findById(id));
	}
	
	protected void doAfterRead(Long id) {
		
	}
	
	protected void doBeforeDelete() {
		
	}
	
	protected void doDelete() {
		if (!mock) {
			getService().remove(getEntityForm());
		}
	}
	
	protected void doAfterDelete() {
		addMessage(FacesMessage.SEVERITY_INFO, null, getDeleteSuccessMessageSummary(), getDeleteSuccessMessageDetail());
	}
	
	protected void mock() {
		mock = true;
	}
	
	protected void treatErrorMessages(ValidatorException e) {
		for (FacesMessage msg : e.getFacesMessages()) {
			FacesContext.getCurrentInstance().addMessage(null, msg);
			addMessage(msg.getSeverity(), null, msg.getSummary(), msg.getDetail());
		}
	}

	public boolean isMock() {
		return mock;
	}
	
	protected String getCreateSuccessMessageSummary() {
		return getI18nMessage("general.crud.create.success.summary");
	}
	
	protected String getCreateSuccessMessageDetail() {
		return getI18nMessage("general.crud.create.success.detail");
	}
	
	protected String getUpdateSuccessMessageSummary() {
		return getI18nMessage("general.crud.update.success.summary");
	}
	
	protected String getUpdateSuccessMessageDetail() {
		return getI18nMessage("general.crud.update.success.detail");
	}
	
	protected String getDeleteSuccessMessageSummary() {
		return getI18nMessage("general.crud.delete.success.summary");
	}
	
	protected String getDeleteSuccessMessageDetail() {
		return getI18nMessage("general.crud.delete.success.detail");
	}
	
}
