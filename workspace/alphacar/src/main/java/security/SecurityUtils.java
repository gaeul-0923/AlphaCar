package security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityUtils {

	public static User getLoginUser() {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	  User user = (User) authentication.getPrincipal();
	  return user;
	 }
}
