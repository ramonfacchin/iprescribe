package br.ufsc.ramonfacchin.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.ufsc.ramonfacchin.common.annotation.DefaultBeanName;
import br.ufsc.ramonfacchin.entity.Identity;

@Local
@DefaultBeanName("IdentityService")
public interface IIdentityServiceLocal extends IIdentityService, IServiceLocal<Identity> {

	List<Identity> findByName(String name);

	List<Identity> findByBirth(Date birth);

	List<Identity> findByNameAndBirth(String name, Date birth);

	Identity findByUsername(String username);
	
	Identity findByCitizenRegistry(String citizenRegistry);

}
