package com.kbdisplay.ls1710.view.settings;

import java.util.List;

import com.kbdisplay.ls1710.domain.User;

public interface UsersSetting {

	public List<User> getUsers();

	public void setUsers(List<User> users);

	public String getRole(User user);

	public void setNewRole(String role);

	public String getNewRole();

	public User getSelected();

	public void setSelected(User selected);

	public void newUser();



}
