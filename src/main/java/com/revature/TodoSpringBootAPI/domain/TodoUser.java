package com.revature.TodoSpringBootAPI.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.revature.TodoSpringBootAPI.core.BaseEntity;

@Entity
@Table(name = "TODO_USER")
@Component
public class TodoUser implements UserDetails {

	/*
	 * BaseEntity - made ourselves for repeated states like id
	 * 
	 * private int id; //is inherited from BaseEntity
	 * 
	 */

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private static final long serialVersionUID = -8266288753343851128L;
	// Spring Security states required
	private String username;
	private String password;
	private boolean isEnabled;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpired;

	@OneToOne
	@JoinColumn(name = "role_id")
	private TodoRole role;

	public TodoUser() {
		super();
	}

	public TodoUser(int id, String username, String password, boolean isEnabled, boolean isAccountNonExpired,
			boolean isAccountNonLocked, boolean isCredentialsNonExpired, TodoRole role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.isEnabled = isEnabled;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.role = role;
	}

	public TodoUser(String username, String password, boolean isEnabled, boolean isAccountNonExpired,
			boolean isAccountNonLocked, boolean isCredentialsNonExpired, TodoRole role) {
		super();
		this.username = username;
		this.password = password;
		this.isEnabled = isEnabled;
		this.isAccountNonExpired = isAccountNonExpired;
		this.isAccountNonLocked = isAccountNonLocked;
		this.isCredentialsNonExpired = isCredentialsNonExpired;
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		System.out.println("TodoUser - getRole " + this.role.getRole());
		authorities.add(new SimpleGrantedAuthority(role.getRole()));
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

	public TodoRole getRole() {
		return role;
	}

	public void setRole(TodoRole role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

	public void setAccountNonExpired(boolean isAccountNonExpired) {
		this.isAccountNonExpired = isAccountNonExpired;
	}

	public void setAccountNonLocked(boolean isAccountNonLocked) {
		this.isAccountNonLocked = isAccountNonLocked;
	}

	public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
		this.isCredentialsNonExpired = isCredentialsNonExpired;
	}

	@Override
	public String toString() {
		return "TodoUser [username=" + username + ", password=" + password + ", isEnabled=" + isEnabled
				+ ", isAccountNonExpired=" + isAccountNonExpired + ", isAccountNonLocked=" + isAccountNonLocked
				+ ", isCredentialsNonExpired=" + isCredentialsNonExpired + ", role=" + role + "]";
	}

}
