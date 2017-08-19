package com.revature.TodoSpringBootAPI.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "TODO_ROLE")
@Component
public class TodoRole {
/*
 * This class is for TodoUser Authorities/Roles like ROLE_ADMIN, ROLE_USER, ROLE_TRIAL_MEMBER etc
 * 		in this example a User can only have 1 role
 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String role;

	public TodoRole() {
		super();
	}

	public TodoRole(String role) {
		super();
		this.role = role;
	}
	

	public TodoRole(int id, String role) {
		super();
		this.id = id;
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
