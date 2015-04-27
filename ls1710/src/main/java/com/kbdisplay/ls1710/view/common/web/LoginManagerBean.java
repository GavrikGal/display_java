package com.kbdisplay.ls1710.view.common.web;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean(name = "loginMgmtBean")
@RequestScoped
public class LoginManagerBean {
	private String userName = null;
	private String password = null;




	public void login() {
		try {
			FacesContext fc = FacesContext.getCurrentInstance();
			ELContext elContext = FacesContext.getCurrentInstance()
					.getELContext();
			AuthenticationManager authenticationManager = (AuthenticationManager) fc
					.getApplication().getELResolver()
					.getValue(elContext, null, "authenticationManager");
			Authentication request =
					new UsernamePasswordAuthenticationToken(this.getUserName(),
							this.getPassword());
			Authentication result = authenticationManager.authenticate(request);
			SecurityContextHolder.getContext().setAuthentication(result);
		} catch (AuthenticationException e) {
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage(null, new FacesMessage("Вы ахренели?",
					"Пока... "));

			return ;
		}
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, new FacesMessage("Вы залогинены",
				"Привет: "));
		this.password = null;
		return ;
	}

	public void cancel() {
		return ;
	}

	public void logout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, new FacesMessage("Вы вышли из системы",
				"Пока... "));
		System.out.println("logout");
		SecurityContextHolder.clearContext();
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
}
