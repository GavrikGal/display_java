package com.kbdisplay.ls1710.service.data.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.Role;
import com.kbdisplay.ls1710.repository.UserRepository;
import com.kbdisplay.ls1710.service.data.UserService;
import com.kbdisplay.ls1710.service.data.jpa.CustomUserDetails.CustomUserDetails;

@Service("userService")
@Repository
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {

	@Autowired
	private UserRepository	userRepository;

	@Override
	@Transactional(readOnly = true)
	public List<com.kbdisplay.ls1710.domain.User> findAll() {
		return Lists.newArrayList(userRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public com.kbdisplay.ls1710.domain.User findById(Long id) {
		return userRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public com.kbdisplay.ls1710.domain.User findByFirstName(String firstName) {
		return userRepository.findByFirstName(firstName);
	}

	@Override
	public com.kbdisplay.ls1710.domain.User save(com.kbdisplay.ls1710.domain.User user) {
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		com.kbdisplay.ls1710.domain.User userFromDB =
				userRepository.findByLogin(login);
		User user;

		if (userFromDB != null) {
			if (userFromDB.getRoles() != null) {
				Collection<GrantedAuthority> userRoles = new ArrayList<GrantedAuthority>();
				for (Role role : userFromDB.getRoles()) {
					userRoles.add(new SimpleGrantedAuthority(role.getName()));
				}

				user = new CustomUserDetails(userFromDB, userRoles);

			} else {
				throw new UsernameNotFoundException("Can not find user");
			}
		} else {
			System.out.println("Can not find user with UserName: " + login);
			throw new UsernameNotFoundException("Can not find user with UserName: " + login);
		}

		return user;
	}

	@Override
	public void delete(com.kbdisplay.ls1710.domain.User user) {
		userRepository.delete(user);
	}

}
