package com.revature.TodoSpringBootAPI.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revature.TodoSpringBootAPI.domain.TodoUser;
import com.revature.TodoSpringBootAPI.repository.TodoUserRepo;


/*
 * The only purpose of this class is to provide Spring Security's AuthenticationManager
 * 	the User's credentials
 */
@Service("userDetailsService")
@Transactional
public class ProcessLogin implements UserDetailsService {

	@Autowired
	TodoUserRepo todoRepoImpl;
	
	/*
	 * Load the TodoUser Record from the DB into an Object and convert TodoUser object
	 * 		into a Spring Security User Object 
	 * 		Note: Our TodoUser implements UserDetails, it is not necessary to convert
	 * 			but the example of how to convert to a Spring Security User is at the bottom commented out
	 */
	 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TodoUser todoDbUserRecord = todoRepoImpl.findByUsername(username);
		
		//Collection of TodoUser's GrantedAuthority aka user's permissions/roles to the application
			//Are they a Member, Trial-Member, Admin, etc 
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	
		// Build/add user's authorities to a Collection
		authorities.add(new SimpleGrantedAuthority(todoDbUserRecord.getRole().getRole()));
//		authorities.add(new SimpleGrantedAuthority("ROLE_USER")); //This is equivalent to the above line that is pulled from the DB
		
		return todoDbUserRecord; //Our TodoUser Class implements UserDetails, 
			//if our Pojo doesn't we would need to convert to a TodoUser into a Spring Security User
			//Example below

		/*
		 * 
			//Example purpose only for setting booleans to true, should be stored with the User Record
		boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;
		boolean isEnabled = true;
		
	    return new org.springframework.security.core.userdetails.User(
	            user.getUsername(),
	            user.getPassword(),
	            isEnabled,
	            accountNonExpired,
	            credentialsNonExpired,
	            accountNonLocked,
	            authorities);
		return user;
		
		*/
	    }


}
