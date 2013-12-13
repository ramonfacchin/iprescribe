/**
 * 
 */
package br.ufsc.ramonfacchin.controller.mb.login;

import java.util.Set;
import java.util.TreeSet;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;

import br.ufsc.ramonfacchin.common.validation.annotation.NotBlank;
import br.ufsc.ramonfacchin.controller.mb.util.BaseMB;
import br.ufsc.ramonfacchin.entity.User;
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
@SessionScoped
@Named("authenticationMB")
public class AuthenticationMB extends BaseMB {

	private static final long serialVersionUID = -2463691989544657955L;

	@EJB
	IUserServiceLocal userService;

	private User loggedUser;

	@NotBlank
	private String username;

	@NotBlank
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String authenticate() {
		try {
			SecurityUtils.getSubject().login(new UsernamePasswordToken(getUsername(), getPassword()));
			setPassword(null);
			setLoggedUser(userService.findByUsername(getUsername()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getHomeUrl();
	}

	public String logout() {
		SecurityUtils.getSubject().logout();
		setLoggedUser(null);
		return getHomeUrl();
	}

	public boolean isAuthenticated() {
		return SecurityUtils.getSubject().isAuthenticated();
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
	
	public boolean isUserInRole(String role) {
		try {
			SecurityUtils.getSubject().checkRole(role);
		} catch (AuthorizationException e) {
			return false;
		}
		return true;
	}

	public Set<String> getRoles() {
		Set<String> userroles = new TreeSet<String>();
		try {
			SecurityUtils.getSubject().checkRole("md");
			userroles.add("md");
		} catch (AuthorizationException e) {
			System.out.println("User doesnt have the role: md");
		}
		try {
			SecurityUtils.getSubject().checkRole("admin");
			userroles.add("admin");
		} catch (AuthorizationException e) {
			System.out.println("User doesnt have the role: admin");
		}
		return userroles;
	}

}
