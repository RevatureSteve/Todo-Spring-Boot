package com.steveace6.TodoSpringBootAPI.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.steveace6.TodoSpringBootAPI.domain.TodoUser;

@Repository(value="todoRepoImpl")
public interface TodoRepo extends  CrudRepository<TodoUser,Integer> {

	TodoUser findByUsername(String username); 
}
