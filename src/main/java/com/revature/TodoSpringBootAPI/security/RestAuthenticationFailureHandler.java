package com.revature.TodoSpringBootAPI.security;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;


//CLASS NOT IN USE!
@Component("restAuthenticationFailureHandler")
public class RestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	
	/*
	 * This class is not used but could be used instead of the method: 
	 * 	com.revature.TodoSpringBootAPI.core.TodoWebSecurityConfiguration.loginFailureHandler()
	 * 
	 * 	then use the method below:
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {

	}
	*/
}