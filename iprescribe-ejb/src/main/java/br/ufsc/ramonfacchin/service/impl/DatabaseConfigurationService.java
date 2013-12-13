package br.ufsc.ramonfacchin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.jws.WebMethod;
import javax.jws.WebService;

import br.ufsc.ramonfacchin.common.interceptor.MethodAuditCDIInterceptor;
import br.ufsc.ramonfacchin.dao.IDatabaseConfigurationDAO;
import br.ufsc.ramonfacchin.entity.DatabaseConfiguration;
import br.ufsc.ramonfacchin.service.IDatabaseConfigurationService;
import br.ufsc.ramonfacchin.service.IDatabaseConfigurationServiceLocal;

/**
 * A sample EJB/WebService implementation class.
 * 
 * @author ramon
 * @version $Id: $
 * @since 0.0.0.0
 *
 */
@WebService
@Stateless
@Remote(IDatabaseConfigurationService.class)
public class DatabaseConfigurationService extends BaseService<DatabaseConfiguration> implements IDatabaseConfigurationServiceLocal {

	private static final long serialVersionUID = -2988319757295156223L;
	
	@EJB
	IDatabaseConfigurationDAO databaseConfigurationDAO;
	
	@Override
	public DatabaseConfiguration findByKey(String key) {
		return getDAO().findByKey(key);
	}

	@Override
	public String get(String key) {
		return getDAO().get(key);
	}

	@Override
	protected IDatabaseConfigurationDAO getDAO() {
		return databaseConfigurationDAO;
	}

	@Interceptors(MethodAuditCDIInterceptor.class)
	@WebMethod(exclude=true)
	@Override
	public List<DatabaseConfiguration> list(int maxResults, int firstResultIndex, Boolean listOnlyActive) {
		return getDAO().list(maxResults, firstResultIndex, listOnlyActive);
	}

}
