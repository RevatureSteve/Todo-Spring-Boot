package com.steveace6.TodoSpringBootAPI.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class TodoWebSecurityConfiguration extends WebSecurityConfigurerAdapter{

//	@Autowired
//    private SimpleUrlAuthenticationSuccessHandler restAuthenticationSuccessHandler;
//
//    @Autowired
//    private AuthenticationFailureHandler restAuthenticationFailureHandler;
//	
	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;
	
	
	
//	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("configureGlobal");
		auth.userDetailsService(userDetailsService);
//		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
		//.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		// Allow these urls
		web.ignoring().antMatchers("app.html", "/");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			.headers().disable()
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/test**").authenticated().and()
				
//				.hasRole("ADMIN").and()
			.formLogin()
				.loginPage("/").loginProcessingUrl("/authenticate")
				.successHandler(loginSuccessHandler()).failureHandler(loginFailureHandler())
				.usernameParameter("username")
				.passwordParameter("password")
				.permitAll().and()
			.logout()
				.logoutUrl("/logout").deleteCookies("JSESSIONID").permitAll();
				
	}
	
	 public AuthenticationSuccessHandler loginSuccessHandler() {
	        return (request, response, authentication) -> {
	        	System.out.println("success login");
	        	response.sendRedirect("/test");
	        	};
	    }

	    public AuthenticationFailureHandler loginFailureHandler() {
	        return (request, response, exception) -> {
//	            request.getSession().setAttribute("flash", new FlashMessage("Incorrect username and/or password. Please try again.", FlashMessage.Status.FAILURE));
	        	System.out.println("FAILED Login ");
	            response.sendRedirect("/");
	        };
	    }
	    
	    
//	    @Bean
//	    public UserDetailsService userDetailsService() {
//	      return new UserDetailsService() {
//
//	        @Transactional
//	        @Override
//	        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//	          SiteUser user = userRepository.findByUsername(username);
//
//	          Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
//	          SimpleGrantedAuthority userAuthority = new SimpleGrantedAuthority("ROLE_USER");
//	          SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
//	          User u = null;
//	          if(user == null) {
//	            throw new UsernameNotFoundException("No such User: " + username);
//	          } else {
//
//	            if (user.getRole().equals("USER")) {
//	              authorities.add(userAuthority);
//	            } else if (user.getRole().equals("ADMIN")) {
//	              authorities.add(userAuthority);
//	              authorities.add(adminAuthority);
//	            }
//	            u = new User(user.getUsername(), user.getPassword(), authorities);
//	          }
//
//	          return u;
//	        }
//
//	      };
//	    }
	    
	    
	    @Bean
	    public EvaluationContextExtension securityExtension() {
	        return new EvaluationContextExtensionSupport() {
	            @Override
	            public String getExtensionId() {
	                return "security";
	            }

	            @Override
	            public Object getRootObject() {
	                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	                return new SecurityExpressionRoot(authentication) {};
	            }
	        };
	    }
}
