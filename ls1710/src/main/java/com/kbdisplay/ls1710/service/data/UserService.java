package com.kbdisplay.ls1710.service.data;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kbdisplay.ls1710.domain.User;

public interface UserService {

	public List<User> findAll();

	public User findById(Long id);

	public User findByFirstName(String firstName);

	@PreAuthorize("hasRole('ROLE_USER')")
	public User save(User user);

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void delete(User user);

}
