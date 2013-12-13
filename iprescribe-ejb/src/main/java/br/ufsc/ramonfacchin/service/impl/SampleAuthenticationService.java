/**
 * 
 */
package br.ufsc.ramonfacchin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.validation.ValidationException;

import br.ufsc.ramonfacchin.common.utils.IPrescribeStringUtils;
import br.ufsc.ramonfacchin.dao.IDAO;
import br.ufsc.ramonfacchin.dao.impl.BaseDAO;
import br.ufsc.ramonfacchin.entity.User;
import br.ufsc.ramonfacchin.entity.base.BaseEntity;
import br.ufsc.ramonfacchin.service.ISampleAuthenticationService;
import br.ufsc.ramonfacchin.service.ISampleAuthenticationServiceLocal;
import br.ufsc.ramonfacchin.service.IUserServiceLocal;

/**
 * Sample service for authenticating users.
 * 
 * @author Ramon Facchin / ramonfacchin@gmail.com
 * @since Aug 20, 2013
 *
 */
@WebService
@Stateless
@Remote(ISampleAuthenticationService.class)
@Local(ISampleAuthenticationServiceLocal.class)
public class SampleAuthenticationService extends BaseService<BaseEntity> implements ISampleAuthenticationServiceLocal {

	private static final long serialVersionUID = 8822637129161106232L;
	@EJB
	IUserServiceLocal userService;
	
	@Override
	public void authenticate(String username, String password) throws ValidationException {
		if (IPrescribeStringUtils.isBlank(username)) {
			throw new ValidationException("validation.authentication.username.blank");
		}
		if (IPrescribeStringUtils.isBlank(password)) {
			throw new ValidationException("validation.authentication.password.blank");
		}
		User user = userService.findByUsername(username);
		if (user == null) {
			throw new ValidationException("validation.authentication.user.null");
		}
		if (!IPrescribeStringUtils.checkPassword(password, user.getPassword())) {
			throw new ValidationException("validation.authentication.password.wrong");
		}
	}

	@Override
	public List<BaseEntity> list(int maxResults, int firstResultIndex, Boolean listOnlyActive) {
		return null;
	}

	@Override
	protected <D extends BaseDAO<BaseEntity>> IDAO<BaseEntity> getDAO() {
		return null;
	}

}
