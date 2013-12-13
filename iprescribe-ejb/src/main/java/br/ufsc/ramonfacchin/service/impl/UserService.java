/**
 * 
 */
package br.ufsc.ramonfacchin.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.validation.ValidationException;

import br.ufsc.ramonfacchin.common.utils.IPrescribeStringUtils;
import br.ufsc.ramonfacchin.dao.IUserDAO;
import br.ufsc.ramonfacchin.entity.User;
import br.ufsc.ramonfacchin.service.IDatabaseConfigurationServiceLocal;
import br.ufsc.ramonfacchin.service.IUserService;
import br.ufsc.ramonfacchin.service.IUserServiceLocal;

/**
 * TODO Descrição da classe.
 * 
 * <h3>Bry Tecnologia - 2013</h3>
 * 
 * @author TODO Nome / TODO Email
 * @since Aug 20, 2013
 * 
 */
@WebService
@Stateless
@Remote(IUserService.class)
@Local(IUserServiceLocal.class)
public class UserService extends BaseService<User> implements IUserServiceLocal {

	private static final long serialVersionUID = -4545661321870257388L;

	@EJB
	IUserDAO userDao;

	@EJB
	IDatabaseConfigurationServiceLocal databaseConfigurationService;

	@Override
	public List<User> list(int maxResults, int firstResultIndex, Boolean listOnlyActive) {
		return getDAO().list(maxResults, firstResultIndex, listOnlyActive);
	}

	@Override
	protected IUserDAO getDAO() {
		return userDao;
	}

	@Override
	public User findByUsername(String username) {
		return getDAO().findByUsername(username);
	}

	@Override
	public void changePassword(User user, String password, String newPassword, String newPasswordConfirm) throws ValidationException {
		if (!IPrescribeStringUtils.checkPassword(password, user.getPassword())) {
			throw new ValidationException("validation.password.wrong");
		}
		if (IPrescribeStringUtils.isBlank(newPassword)) {
			throw new ValidationException("validation.password.new.empty");
		}
		if (!newPassword.equals(newPasswordConfirm)) {
			throw new ValidationException("validation.password.new.confirmation.not.equals");
		}
		String minLengthStr = databaseConfigurationService.get("password.min.length");
		int minLength = 6;
		if (IPrescribeStringUtils.isNotBlank(minLengthStr)) {
			minLength = new Integer(minLengthStr);
		}
		if (newPassword.length() < minLength) {
			throw new ValidationException("validation.password.new.short");
		}
		user.setPassword(IPrescribeStringUtils.encryptPassword(newPassword));
		save(user);
	}
	
	@Override
	@WebMethod(exclude = true)
	public void save(User entity) {
		entity.setUsername(entity.getUsername().toLowerCase());
		super.save(entity);
	}

	@Override
	public User findByCitizenRegistry(String citizenRegistry) {
		return getDAO().findByCitizenRegistry(citizenRegistry);
	}

	@Override
	public User findByEmail(String email) {
		return getDAO().findByEmail(email);
	}

}
