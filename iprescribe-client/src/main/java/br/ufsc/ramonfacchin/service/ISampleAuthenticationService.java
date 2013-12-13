/**
 * 
 */
package br.ufsc.ramonfacchin.service;

import javax.ejb.Remote;
import javax.validation.ValidationException;

import br.ufsc.ramonfacchin.common.annotation.DefaultBeanName;
import br.ufsc.ramonfacchin.entity.base.BaseEntity;


/**
 * TODO Descrição da classe.
 *
 * <h3>Bry Tecnologia - 2013</h3>
 * 
 * @author TODO Nome / TODO Email
 * @since Aug 20, 2013
 *
 */
@Remote
@DefaultBeanName("SampleAuthenticationService")
public interface ISampleAuthenticationService  extends IService<BaseEntity> {

	void authenticate(String username, String password) throws ValidationException;
	
}
