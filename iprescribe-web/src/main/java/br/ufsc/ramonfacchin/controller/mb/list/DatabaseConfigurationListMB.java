package br.ufsc.ramonfacchin.controller.mb.list;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Inject;
import javax.inject.Named;

import br.ufsc.ramonfacchin.controller.mb.util.BaseListMB;
import br.ufsc.ramonfacchin.entity.DatabaseConfiguration;
import br.ufsc.ramonfacchin.service.IDatabaseConfigurationServiceLocal;

@SessionScoped
@Named("databaseConfigurationListMB")
public class DatabaseConfigurationListMB extends BaseListMB<DatabaseConfiguration> {

	private static final long serialVersionUID = -8807330515967301221L;

	@Inject
	IDatabaseConfigurationServiceLocal databaseConfigurationService;
	
	@Override
	protected IDatabaseConfigurationServiceLocal getService() {
		return databaseConfigurationService;
	}
	
	public void editRow(DatabaseConfiguration dbc) {
		dbc.setEdit(true);
	}
	
	public void save() {
		for (DatabaseConfiguration dbc : getList()) {
			if (dbc.isEdit()) {
				if (dbc.getId() == null) {
					if (databaseConfigurationService.get(dbc.getKey()) != null) {
						addMessage(FacesMessage.SEVERITY_ERROR, null, getI18nMessage("dbc.error.key.exists.summary"), getI18nMessage("dbc.error.key.exists.summary"));
						break;
					}
				}
				databaseConfigurationService.save(dbc);
				dbc.setEdit(false);
			}
		}
	}
	
	public void addRow() {
		goToLastPage();
		DatabaseConfiguration dbc = new DatabaseConfiguration();
		dbc.setEdit(true);
		getList().add(dbc);
	}

}
