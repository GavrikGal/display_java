package com.kbdisplay.ls1710.view.common.web;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.kbdisplay.ls1710.service.data.jpa.CustomUserDetails.CustomUserDetails;

@ManagedBean(name = "loginMgmtBean")
@SessionScoped
public class LoginManagerBean {
	private String userName = null;
	private String password = null;

	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager;


	public String login() {
		try {

			Authentication request =
					new UsernamePasswordAuthenticationToken(this.getUserName(),
							this.getPassword());
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
		} catch (AuthenticationException e) {
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage(null, new FacesMessage("Вход для пользователя " + this.userName + " не может быть выполнен",
					"Проверте логин и пароль, затем повторите вход"));

			return null;
		}
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, new FacesMessage("Вход в систему",
				user.getFirstName() + ", добро пожаловать в систему"));
		return null;
	}

	public void cancel() {
		return;
	}

	public String logout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, new FacesMessage("Выход из системы", "Вы вышли из системы"));

		SecurityContextHolder.clearContext();
		return "logout";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}

	public void setAuthenticationManager(
			AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

}
