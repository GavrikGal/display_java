package com.kbdisplay.ls1710.service.data.jpa.CustomUserDetails;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUserDetails extends User {

	private static final long serialVersionUID = -4205754242760057139L;
	private com.kbdisplay.ls1710.domain.User userDetail;
	private String firstName;
	private String lastName;

	public CustomUserDetails(com.kbdisplay.ls1710.domain.User user,
			Collection<? extends GrantedAuthority> authorities) {
		super(user.getUserName(), user.getPassword(), authorities);
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.userDetail = user;
	}

	public com.kbdisplay.ls1710.domain.User getUsersDetails() {
		return userDetail;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setUsersDetails(com.kbdisplay.ls1710.domain.User usersDetails) {
		this.userDetail = usersDetails;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
