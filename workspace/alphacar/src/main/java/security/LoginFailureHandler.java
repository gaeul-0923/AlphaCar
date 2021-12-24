package security;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;



public class LoginFailureHandler implements AuthenticationFailureHandler{
//	@Resource(name="userSer")
//	private UserService userSer;
	
	private String loginidname           ;
	private String loginpwdname              ;
	private String errormsgname;
	private String defaultFailureUrl;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String customer_email = request.getParameter(loginidname);
		String customer_pw = request.getParameter(loginpwdname);
		String errormsg = null;

		  if(exception instanceof UsernameNotFoundException) {
		   errormsg = "계정이 존재하지 않습니다.";
		  }else if(exception instanceof BadCredentialsException) {
		   errormsg = "아이디 혹은 비밀번호를 잘못 입력했습니다."; 
		  }else if(exception instanceof DisabledException) {
		   errormsg = "비활성화 된 계정입니다.";//인증되지 않은 계정입니다.(계정 비활성화)
		  }else if(exception instanceof SessionAuthenticationException) {
		   errormsg = "중복로그인입니다."; //중복로그인
		  }

//		  if(exception instanceof BadCredentialsException) {
//				errormsg = MessageUtils.getMessage("error.BadCredentials");
//			} else if(exception instanceof InternalAuthenticationServiceException) {
//				errormsg = MessageUtils.getMessage("error.BadCredentials");
//			} else if(exception instanceof DisabledException) {
//				errormsg = MessageUtils.getMessage("error.Disaled");
//			} else if(exception instanceof CredentialsExpiredException) {
//				errormsg = MessageUtils.getMessage("error.CredentialsExpired");
//			} else if(exception instanceof SessionAuthenticationException) {
//				errormsg = MessageUtils.getMessage("error.SessionAuthentication");
//			}

		
		request.setAttribute(loginidname, customer_email);
        request.setAttribute(loginpwdname, customer_pw);
        request.setAttribute(errormsgname, errormsg);

        request.getRequestDispatcher(defaultFailureUrl).forward(request, response);


	}
	
	//비밀번호 3회이상 오류났을 때 계정잠김
//	protected void loginFailureCount(String username) {
//		userSer.countFailure(username);
//		int cnt = userSer.checkFailureCount(username);
//		if(cnt==3) {
//			userSer.disabledUsername(username);
//		}
//	}

	public String getLoginidname() {
		return loginidname;
	}



	public void setLoginidname(String loginidname) {
		this.loginidname = loginidname;
	}



	public String getLoginpwdname() {
		return loginpwdname;
	}



	public void setLoginpwdname(String loginpwdname) {
		this.loginpwdname = loginpwdname;
	}



	public String getErrormsgname() {
		return errormsgname;
	}

	public void setErrormsgname(String errormsgname) {
		this.errormsgname = errormsgname;
	}

	public String getDefaultFailureUrl() {
		return defaultFailureUrl;
	}

	public void setDefaultFailureUrl(String defaultFailureUrl) {
		this.defaultFailureUrl = defaultFailureUrl;
	}
	

}
