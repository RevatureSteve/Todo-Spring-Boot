package com.revature.TodoSpringBootAPI.core;

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

	@Autowired
	@Qualifier("userDetailsService")
	UserDetailsService userDetailsService;
	
	
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//Pull User Object from a external Resource like a file or database
		auth.userDetailsService(userDetailsService); //Load User by Username is the important method found here
	
		//Use below for testing without an DB
//		auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
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
				.antMatchers("/home**").authenticated() //Logged in user, no role necessary
				.antMatchers("/user**").hasRole("USER") //User must have the ROLE_USER to see these pages
				.antMatchers("/admin**").hasRole("ADMIN").and() //User must have ROLE_ADMIN to see these pages
			.formLogin()
				.loginPage("/")
				.loginProcessingUrl("/authenticate") //Spring Security processes the username & password automatically at the URL you specify e.g. /authenticate
					.successHandler(loginSuccessHandler()) //whose handling success
					.failureHandler(loginFailureHandler()) //whose handling failure, methods are below 
					.usernameParameter("username") 
					.passwordParameter("password")
				.permitAll().and()
			.logout()
				.logoutUrl("/logout")
				.deleteCookies("JSESSIONID")
				.permitAll();
				
	}
	
	/*
	 * Simple methods but may use RestAuthenticationFailureHandler & SuccessHandler Class instead of these
	 */
	 public AuthenticationSuccessHandler loginSuccessHandler() {
	        return (request, response, authentication) -> {
	        	System.err.println("Login-Success");
	        	response.sendRedirect("/home");
	        	};
	    }

	    public AuthenticationFailureHandler loginFailureHandler() {
	        return (request, response, exception) -> {
	        	System.err.println("Login-Failed");
	            response.sendRedirect("/");
	        };
	    }
	    
	    
	    
}
