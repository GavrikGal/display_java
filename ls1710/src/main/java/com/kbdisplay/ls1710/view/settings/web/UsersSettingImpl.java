package com.kbdisplay.ls1710.view.settings.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.kbdisplay.ls1710.domain.Role;
import com.kbdisplay.ls1710.domain.User;
import com.kbdisplay.ls1710.view.settings.UsersSetting;

public class UsersSettingImpl implements UsersSetting, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8663846945001220144L;

	private List<User> users;

	private User selected;



	@Override
	public List<User> getUsers() {
		return users;
	}

	@Override
	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String getRole(User user) {

		List<Role> roles = new ArrayList<Role>(user.getRoles());

		String roleName = "Специалист";
		for (Role role : roles) {
			if (role.getName().equals("ROLE_ADMIN")) {
				roleName = "Администратор";
			}
		}

		return roleName;
	}

	@Override
	public User getSelected() {
		return selected;
	}

	@Override
	public void setSelected(User selected) {
		this.selected = selected;
	}



}
