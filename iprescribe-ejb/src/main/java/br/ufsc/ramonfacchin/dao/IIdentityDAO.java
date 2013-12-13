package br.ufsc.ramonfacchin.dao;

import java.util.Date;
import java.util.List;

import br.ufsc.ramonfacchin.entity.Identity;

public interface IIdentityDAO extends IDAO<Identity> {

	List<Identity> findByName(String name);

	List<Identity> findByBirth(Date birth);

	List<Identity> findByNameAndBirth(String name, Date birth);

	Identity findByUsername(String username);
	
	Identity findByCitizenRegistry(String citizenRegistry);

}
