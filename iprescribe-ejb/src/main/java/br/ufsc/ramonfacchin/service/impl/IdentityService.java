package br.ufsc.ramonfacchin.service.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;

import br.ufsc.ramonfacchin.dao.IIdentityDAO;
import br.ufsc.ramonfacchin.entity.Identity;
import br.ufsc.ramonfacchin.service.IIdentityService;
import br.ufsc.ramonfacchin.service.IIdentityServiceLocal;

@WebService
@Stateless
@Remote(IIdentityService.class)
@Local(IIdentityServiceLocal.class)
public class IdentityService extends BaseService<Identity> implements IIdentityServiceLocal {

	private static final long serialVersionUID = 5885841429129736884L;

	@EJB
	IIdentityDAO identityDAO;

	@Override
	public List<Identity> list(int maxResults, int firstResultIndex, Boolean listOnlyActive) {
		return getDAO().list(maxResults, firstResultIndex, listOnlyActive);
	}

	@Override
	public List<Identity> findByName(String name) {
		return getDAO().findByName(name);
	}

	@Override
	public List<Identity> findByBirth(Date birth) {
		return getDAO().findByBirth(birth);
	}

	@Override
	public List<Identity> findByNameAndBirth(String name, Date birth) {
		return getDAO().findByNameAndBirth(name, birth);
	}

	@Override
	public Identity findByUsername(String username) {
		return getDAO().findByUsername(username);
	}

	@Override
	protected IIdentityDAO getDAO() {
		return identityDAO;
	}

	@Override
	public Identity findByCitizenRegistry(String citizenRegistry) {
		return getDAO().findByCitizenRegistry(citizenRegistry);
	}

}
