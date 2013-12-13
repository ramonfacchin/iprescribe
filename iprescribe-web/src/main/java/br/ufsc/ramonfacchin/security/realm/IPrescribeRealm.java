/**
 * 
 */
package br.ufsc.ramonfacchin.security.realm;

import java.util.Set;
import java.util.TreeSet;

import javax.validation.ValidationException;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;

import br.ufsc.ramonfacchin.entity.User;
import br.ufsc.ramonfacchin.service.ISampleAuthenticationService;
import br.ufsc.ramonfacchin.service.IUserService;
import br.ufsc.ramonfacchin.service.util.ServiceLocator;

/**
 * TODO Descrição da classe.
 *
 * <h3>Bry Tecnologia - 2013</h3>
 * 
 * @author TODO Nome / TODO Email
 * @since Aug 20, 2013
 *
 */
public class IPrescribeRealm extends AuthorizingRealm implements Realm {

	ISampleAuthenticationService sampleAuthenticationService;
	
	IUserService userService;

	public ISampleAuthenticationService getSampleAuthenticationService() {
		try {
			if (sampleAuthenticationService == null) {
				sampleAuthenticationService = (ISampleAuthenticationService) ServiceLocator.getInstance().getLocalService(ISampleAuthenticationService.class);
			}
			return sampleAuthenticationService;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public IUserService getUserService() {
		try {
			if (userService == null) {
				userService = (IUserService) ServiceLocator.getInstance().getLocalService(IUserService.class);
			}
			return userService;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getName() {
		return "realm";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		if (token instanceof UsernamePasswordToken) {
			return true;
		}
		return false;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Set<String> roles = new TreeSet<String>();
		if (principals != null && !principals.isEmpty()) {
			String principal = (String) principals.getPrimaryPrincipal();
			if ("schaffer".equals(principal) || "sammet".equals(principal) || "mustaine".equals(principal)) {
				roles.add("md");
			}
			if ("ramonfacchin".equals(principal)) {
				roles.add("admin");
			}
			AuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo(roles);
			return authorizationInfo;
		}
		return null;
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upt = (UsernamePasswordToken) token;
		User user = getUserService().findByUsername(upt.getUsername());
		if (user == null) {
			throw new UnknownAccountException("validation.authentication.user.null");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), "realm");
		return info;
	}
	
	@Override
	protected void assertCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException {
		UsernamePasswordToken upt = (UsernamePasswordToken) token;
		try {
			getSampleAuthenticationService().authenticate(upt.getUsername(), new String(upt.getPassword()));
		} catch (ValidationException e) {
			if ("validation.authentication.password.wrong".equals(e.getMessage())) {
				throw new IncorrectCredentialsException(e.getMessage());
			} else {
				throw new ValidationException(e.getMessage());
			}
		}
	}

}
