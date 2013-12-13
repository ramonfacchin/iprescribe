package br.ufsc.ramonfacchin.dao;

import br.ufsc.ramonfacchin.entity.User;

public interface IUserDAO extends IDAO<User> {

	User findByEmail(String email);

	User findByUsername(String username);

	User findByCitizenRegistry(String citizenRegistry);

}
