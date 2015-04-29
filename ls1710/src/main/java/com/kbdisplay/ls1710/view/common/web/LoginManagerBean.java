package com.kbdisplay.ls1710.view.common.web;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean(name = "loginMgmtBean")
@SessionScoped
public class LoginManagerBean {
	private String userName = null;
	private String password = null;


	@ManagedProperty(value = "#{authenticationManager}")
	private AuthenticationManager authenticationManager;

	public void login() {
		try {

			Authentication request =
					new UsernamePasswordAuthenticationToken(this.getUserName(),
							this.getPassword());
			Authentication result = authenticationManager.authenticate(request);
			System.out.println(result.getName());
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

		System.out.println("logout name: "+ SecurityContextHolder.getContext().getAuthentication().getName());
		List<GrantedAuthority> grants = new ArrayList<GrantedAuthority>();
		grants.addAll(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		System.out.println("Grantes:");
		for (GrantedAuthority grantedAuthority : grants) {
			System.out.println(grantedAuthority.getAuthority());
		}
		SecurityContextHolder.clearContext();
	}

	@PreAuthorize(value="hasRole('ROLE_ANONYMOUS')")
	public void testAscess(){
		System.out.println("it is work");
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

	public void
			setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}


}
