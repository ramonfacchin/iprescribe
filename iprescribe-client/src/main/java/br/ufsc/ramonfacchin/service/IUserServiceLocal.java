/**
 * 
 */
package br.ufsc.ramonfacchin.service;

import javax.ejb.Local;
import javax.validation.ValidationException;

import br.ufsc.ramonfacchin.common.annotation.DefaultBeanName;
import br.ufsc.ramonfacchin.entity.User;

/**
 * TODO Descrição da classe.
 *
 * <h3>Bry Tecnologia - 2013</h3>
 * 
 * @author TODO Nome / TODO Email
 * @since Aug 20, 2013
 *
 */
@Local
@DefaultBeanName("UserService")
public interface IUserServiceLocal extends IUserService,IServiceLocal<User> {
	
	void changePassword(User user, String password, String newPassword, String newPasswordConfirm) throws ValidationException;

}
