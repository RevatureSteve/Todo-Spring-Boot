package com.revature.TodoSpringBootAPI.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


//CLASS NOT IN USE!
@Component("restAuthenticationSuccessHandler")
public class RestAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	
	
/*
 * This class is not used but could be used instead of the method: 
 * 	com.revature.TodoSpringBootAPI.core.TodoWebSecurityConfiguration.loginSuccessHandler()
 * 
 * 	then use the method below
 
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.onAuthenticationSuccess(request, response, authentication);
	}
 */
}
