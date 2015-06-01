package com.kbdisplay.ls1710.controller;

import java.util.List;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.User;
import com.kbdisplay.ls1710.service.data.UserService;
import com.kbdisplay.ls1710.view.settings.NormsSetting;
import com.kbdisplay.ls1710.view.settings.UsersSetting;
import com.kbdisplay.ls1710.view.settings.web.NormsSettingImpl;
import com.kbdisplay.ls1710.view.settings.web.UsersSettingImpl;

@Component("settingsController")
public class SettingsController {

	@Autowired
	private UserService userService;

	public UsersSetting newUsersSetting() {

		UsersSetting usersSetting = new UsersSettingImpl();

		List<User> users = userService.findAll();

		usersSetting.setUsers(users);


		return usersSetting;
	}

	public NormsSetting newNormsSetting() {

		NormsSetting normsSetting = new NormsSettingImpl();

		return normsSetting;
	}

	public User newUser() {
		return new User();
	}

	public void saveUser(org.springframework.webflow.execution.RequestContext context) {
		UsersSetting usersSetting = (UsersSetting) context.getFlowScope().get("usersSetting");

		if (usersSetting != null) {
			System.out.println(usersSetting.getSelected().getFirstName());
			usersSetting.setSelected(userService.save(usersSetting.getSelected()));
			usersSetting.getUsers().add(usersSetting.getSelected());
			System.out.println(usersSetting.getSelected().getId());
		}
	}


	public void deleteUser(User selected) {

		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, new FacesMessage("Пользователь удален",
				"Пользователь: " + selected.getLastName() + " "
						+ selected.getFirstName() + " удален"));

		ELContext elContext =
				FacesContext.getCurrentInstance().getELContext();
		UsersSetting usersSetting =
				(UsersSetting) fc.getApplication().getELResolver()
						.getValue(elContext, null, "usersSetting");

		usersSetting.getUsers().remove(selected);
		//userService.delete(selected);
	}

	public String editUser(User selected) {
		return null;
	}



}
