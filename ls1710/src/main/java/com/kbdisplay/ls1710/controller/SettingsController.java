package com.kbdisplay.ls1710.controller;

import java.util.List;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.User;
import com.kbdisplay.ls1710.service.data.UserService;
import com.kbdisplay.ls1710.service.data.jpa.CustomUserDetails.CustomUserDetails;
import com.kbdisplay.ls1710.view.settings.NormsSetting;
import com.kbdisplay.ls1710.view.settings.UsersSetting;
import com.kbdisplay.ls1710.view.settings.web.NormsSettingImpl;
import com.kbdisplay.ls1710.view.settings.web.UsersSettingImpl;

@Component("settingsController")
public class SettingsController {

	@Autowired
	private UserService userService;

	@PreAuthorize("hasRole('ROLE_USER')")
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

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User newUser() {
		return new User();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void saveUser(org.springframework.webflow.execution.RequestContext context) {
		UsersSetting usersSetting = (UsersSetting) context.getFlowScope().get("usersSetting");

		if (usersSetting != null) {
			usersSetting.setSelected(userService.save(usersSetting.getSelected()));
			usersSetting.getUsers().add(usersSetting.getSelected());
		}
	}


	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteUser(User selected) {

		FacesContext fc = FacesContext.getCurrentInstance();

		// TODO получаем зарегистированного пользователя
		CustomUserDetails userDetails =
				(CustomUserDetails) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();
		User user = userDetails.getUsersDetails();

		if(selected.getId().equals(user.getId())) {
			fc.addMessage(null, new FacesMessage("Пользователь не удален",
					"Пользователь: " + selected.getLastName() + " "
							+ selected.getFirstName() + " не может удалить сам себя"));
		} else {
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
	}

	public User getCurrentUser() {
		CustomUserDetails userDetails =
				(CustomUserDetails) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();
		User user = userDetails.getUsersDetails();

		return user;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	public void changeLogin(org.springframework.webflow.execution.RequestContext context) {
		FacesContext fc = FacesContext.getCurrentInstance();

		User user = (User) context.getFlowScope().get("currentUserDetails");
		fc.addMessage(null, new FacesMessage("Логин изменен",
				user.getLastName() + " "
						+ user.getFirstName() + ", при следующем входе в систему, используйте новый логин"));
		userService.save(user);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String editUser(User selected) {
		return null;
	}



}
