package com.kbdisplay.ls1710.view.settings.web;

import java.io.Serializable;

import com.kbdisplay.ls1710.domain.User;
import com.kbdisplay.ls1710.view.settings.UserDetailsSetting;

public class UserDetailsSettingImpl implements UserDetailsSetting, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6867305871469138834L;

	private User user;


	@Override
	public User getUser() {
		return user;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
