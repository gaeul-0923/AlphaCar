package security;



import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
   

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/resources/**")
			.antMatchers("/**/*.js")
			.antMatchers("/**/*.css" )
			.antMatchers("/img/**")
			.antMatchers("/ioTCarWash")
			.antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/**/*.ho", "/", "/**/*.no", "/list.se",
					"/detail.se", "/**/*.wa").permitAll()
			.antMatchers("/reply.qna", "/reply_insert.qna", "/reply_update.qna", "/mastermemberUpdate.mp",
					"/masterMemberList.mp", "/insert.se", "/write.se", "/update.se",
					"/update_work.se","/delete.se").hasAuthority("ROLE_ALPHACHR")
			.antMatchers("/**/*.mp", "/**/*.ch", "/**/*.qna").authenticated();
		
		
		http.logout()
			.logoutUrl("/logout") // 요청 url은 logout
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
			.deleteCookies("JESSIONID, remember-me");
		//logout으로 요청이 오면 로그아웃을 시켜버리고 / 경로로 리다이렉트 해주며
		//SecurityContextHolder에 저장된 세션정보를 지운다.
		
		
			
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userService)
//    	// 해당 서비스(userService)에서는 UserDetailsService를 implements해서 
//        // loadUserByUsername() 구현해야함 (서비스 참고)
//    	.passwordEncoder(new BCryptPasswordEncoder()); 
		
	}
	
//	/* * SuccessHandler bean register */ 
//	@Bean 
//	public AuthenticationSuccessHandler authenticationSuccessHandler() { 
//		LoginSuccessHandler successHandler = new LoginSuccessHandler();
//		successHandler.setDefaultUrl("/");
//
//		return successHandler; 
//	}
//	
//	@Bean public AuthenticationFailureHandler authenticationFailureHandler() { 
//		LoginFailureHandler failureHandler = new LoginFailureHandler(); 
//		failureHandler.setDefaultFailureUrl("/loginPage?error=error"); 
//		return failureHandler; 
//	}
//	
//	@Bean
//    public BCryptPasswordEncoder encodePassword() {  // 회원가입 시 비밀번호 암호화에 사용할 Encoder 빈 등록
//        return new BCryptPasswordEncoder();
//    }
//	
	


	
	
	
}
