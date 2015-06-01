package com.kbdisplay.ls1710.view.settings.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.faces.context.FacesContext;

import com.kbdisplay.ls1710.domain.Role;
import com.kbdisplay.ls1710.domain.User;
import com.kbdisplay.ls1710.service.data.UserService;
import com.kbdisplay.ls1710.view.settings.UsersSetting;

public class UsersSettingImpl implements UsersSetting, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8663846945001220144L;

	private List<User> users;

	private User selected;

	private String newRole = "ROLE_USER";



	@Override
	public List<User> getUsers() {
		return users;
	}

	@Override
	public void setUsers(List<User> users) {
		System.out.println(users);
		this.users = users;
	}

	@Override
	public String getRole(User user) {

		if (user != null) {
			System.out.println(user.getFirstName());
			if (user.getRoles() == null) {
				FacesContext fc = FacesContext.getCurrentInstance();
				ELContext elContext = FacesContext.getCurrentInstance()
						.getELContext();
				UserService userService = (UserService) fc
						.getApplication().getELResolver()
						.getValue(elContext, null, "userService");

				user = userService.findByFirstName(user.getFirstName());

			}

			List<Role> roles = new ArrayList<Role>(user.getRoles());

			String roleName = "Специалист";
			for (Role role : roles) {
				if (role.getName().equals("ROLE_ADMIN")) {
					roleName = "Администратор";
				}
			}

			return roleName;
		}
		return "Бездельник";
	}

	@Override
	public void setNewRole(String role) {
		//TODO не забудь исправить!!
		if (role.equals("ROLE_ADMIN")) {
			selected.setRoles(users.get(0).getRoles());
		}else {
			selected.setRoles(users.get(1).getRoles());
		}

	}



	@Override
	public String getNewRole() {
		return newRole;
	}

	@Override
	public User getSelected() {
		return selected;
	}

	@Override
	public void setSelected(User selected) {
		this.selected = selected;
	}

	@Override
	public void newUser() {
		User user = new User();
		java.util.HashSet<Role> roles = new java.util.HashSet<Role>();
		user.setRoles(roles);

		selected = user;

	}



}
