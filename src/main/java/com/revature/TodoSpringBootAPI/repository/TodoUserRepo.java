package com.revature.TodoSpringBootAPI.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.revature.TodoSpringBootAPI.domain.TodoUser;

@Repository(value="todoRepoImpl")
public interface TodoUserRepo extends  CrudRepository<TodoUser,Integer> {

	TodoUser findByUsername(String username); 
}
