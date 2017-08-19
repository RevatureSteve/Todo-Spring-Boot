package com.steveace6.TodoSpringBootAPI.security;

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

import com.steveace6.TodoSpringBootAPI.domain.TodoUser;
import com.steveace6.TodoSpringBootAPI.repository.TodoRepo;

@Service("userDetailsService")
@Transactional
public class ProcessLogin implements UserDetailsService {

	@Autowired
	TodoRepo todoRepoImpl;
	
	 
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("Load User by Username" + username);
		TodoUser user = todoRepoImpl.findByUsername(username);
		System.out.println("User from DB: " + user);
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	
		// Build user's authorities
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(authorities);
		System.out.println(Result);
//		return new User(username, "123", Result);
//		return new TodoUser("steve", "123", true, true, true, true, new TodoRole("ROLE_ADMIN"));

		boolean accountNonExpired = true;
	    boolean credentialsNonExpired = true;
	    boolean accountNonLocked = true;
		boolean isEnabled = true;
	    return new User(
	            user.getUsername(),
	            user.getPassword(),
	            isEnabled,
	            accountNonExpired,
	            credentialsNonExpired,
	            accountNonLocked,
	            getAuthorities(user.getRole().getRole()));
	    }

	public List<String> getRolesAsList(String roles) {
	    List <String> rolesAsList = new ArrayList<String>();
	        rolesAsList.add("ROLE_USER");
	    return rolesAsList;
	}

	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    for (String role : roles) {
	        authorities.add(new SimpleGrantedAuthority(role));
	    }
	    return authorities;
	}

	public Collection<? extends GrantedAuthority> getAuthorities(String roles) {
	    List<GrantedAuthority> authList = getGrantedAuthorities(getRolesAsList(roles));
	    System.out.println("Auth list: "+authList);
	    return authList;
	}
}
