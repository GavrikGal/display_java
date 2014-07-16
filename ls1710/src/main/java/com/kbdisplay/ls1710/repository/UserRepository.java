package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	public User findByFirstName(String firstName);
	
	public User findByUserName(String userName);

}
