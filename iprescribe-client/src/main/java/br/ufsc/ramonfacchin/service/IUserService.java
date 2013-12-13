package br.ufsc.ramonfacchin.service;

import javax.ejb.Remote;

import br.ufsc.ramonfacchin.common.annotation.DefaultBeanName;
import br.ufsc.ramonfacchin.entity.User;

/**
 * Remote interface for EJB.
 * Class {@link User}
 * 
 * Notice that EVERY Remote EJB interface must be annotated with
 * {@link DefaultBeanName}
 * 
 * @author ramon
 * @version $Id: $
 * @since 0.0.0.0
 * 
 */
@DefaultBeanName("UserService")
@Remote
public interface IUserService extends IService<User> {

	User findByEmail(String email);

	User findByUsername(String username);

	User findByCitizenRegistry(String citizenRegistry);

}
