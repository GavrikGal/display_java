package com.kbdisplay.ls1710.service.data;

import java.util.List;



import com.kbdisplay.ls1710.domain.User;

public interface UserService {
	
	public List<User> findAll();
	
	public User findById(Long id);
	
	public User findByFirstName(String firstName);

	public User save(User user);

}
