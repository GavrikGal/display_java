package com.kbdisplay.ls1710.controller;

import java.util.List;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.Norm;
import com.kbdisplay.ls1710.domain.User;
import com.kbdisplay.ls1710.service.data.NormService;
import com.kbdisplay.ls1710.service.data.UserService;
import com.kbdisplay.ls1710.service.data.jpa.CustomUserDetails.CustomUserDetails;
import com.kbdisplay.ls1710.view.settings.NormsSetting;
import com.kbdisplay.ls1710.view.settings.Settings;
import com.kbdisplay.ls1710.view.settings.UserDetailsSetting;
import com.kbdisplay.ls1710.view.settings.UsersSetting;
import com.kbdisplay.ls1710.view.settings.web.NormsSettingImpl;
import com.kbdisplay.ls1710.view.settings.web.SettingsImpl;
import com.kbdisplay.ls1710.view.settings.web.UserDetailsSettingImpl;
import com.kbdisplay.ls1710.view.settings.web.UsersSettingImpl;

@Component("settingsController")
public class SettingsController {

	/**
	 * логгер класса.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(SettingsController.class);


	@Autowired
	private UserService userService;

	@Autowired
	private NormService normService;


	@PreAuthorize("hasRole('ROLE_USER')")
	public Settings newSettings(){
		Settings settings = new SettingsImpl();
		return settings;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	public UsersSetting newUsersSetting() {

		UsersSetting usersSetting = new UsersSettingImpl();

		List<User> users = userService.findAll();

		usersSetting.setUsers(users);

		return usersSetting;
	}

	public NormsSetting newNormsSetting() {

		NormsSetting normsSetting = new NormsSettingImpl();

		normsSetting.init();

		return normsSetting;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User newUser() {
		return new User();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void saveUser(
			org.springframework.webflow.execution.RequestContext context) {
		UsersSetting usersSetting =
				(UsersSetting) context.getFlowScope().get("usersSetting");

		if (usersSetting != null) {
			usersSetting.setSelected(userService.save(usersSetting
					.getSelected()));
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

		if (selected.getId() == 1) {
			fc.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Гала не сломить!", "Пользователь: "
							+ selected.getLastName() + " "
							+ selected.getFirstName()
							+ " скорее тебя удалит, чем ты его"));
			return;
		}

		if (selected.getId().equals(user.getId())) {
			fc.addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,"Пользователь не удален", "Пользователь: "
							+ selected.getLastName() + " "
							+ selected.getFirstName()
							+ " не может удалить сам себя"));
		} else {
			fc.addMessage(
					null,
					new FacesMessage("Пользователь удален", "Пользователь: "
							+ selected.getLastName() + " "
							+ selected.getFirstName() + " удален"));

			ELContext elContext =
					FacesContext.getCurrentInstance().getELContext();
			UsersSetting usersSetting =
					(UsersSetting) fc.getApplication().getELResolver()
							.getValue(elContext, null, "usersSetting");

			usersSetting.getUsers().remove(selected);
			// userService.delete(selected);
		}
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	public UserDetailsSetting newUserDetailsSetting() {

		CustomUserDetails userDetails =
				(CustomUserDetails) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();
		User user = userDetails.getUsersDetails();

		UserDetailsSetting detailsSetting = new UserDetailsSettingImpl();

		detailsSetting.setUser(user);

		return detailsSetting;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	public void changeLogin() {

		CustomUserDetails customUserDetails =
				(CustomUserDetails) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal();
		User user = customUserDetails.getUsersDetails();
		user = userService.save(user);
		logger.info("User " + user.getLastName() + " change login");
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String editUser(User selected) {
		return null;
	}



	public void saveNewNorm(org.springframework.webflow.execution.RequestContext context) {
		NormsSetting normsSetting =
				(NormsSetting) context.getFlowScope().get("normsSetting");

		if (normsSetting != null) {
			Norm norm = normService.save(normsSetting.getSelectedNorm());
			System.out.println("save" + norm.getId());
		}
	}



}
