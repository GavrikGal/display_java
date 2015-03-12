package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Объект пользователя из БД.
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = 7925769595205799558L;

	/**
	 * ID пользователя.
	 */
	private Long id;

	/**
	 * Фамилия пользователя.
	 */
	private String firstName;

	/**
	 * имя пользователя.
	 */
	private String lastName;

	/**
	 * Отчество пользователя.
	 */
	private String fatherName;

	/**
	 * Логин пользователя для входа в систему.
	 */
	private String login;

	/**
	 * Пароль для входа в систему.
	 */
	private String password;

	/**
	 * Список измерений, которые проводил пользователь.
	 */
	private Set<Measurement> measurements = new HashSet<Measurement>();

	/**
	 * Список привилегий пользователя (юзер, админ и т.д.).
	 */
	private Set<Role> roles;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Column(name = "first_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "father_name")
	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(final String fatherName) {
		this.fatherName = fatherName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	// @ManyToMany
	// @JoinTable(name = "users_of_measurement", joinColumns = @JoinColumn(name
	// = "User"), inverseJoinColumns = @JoinColumn(name = "Measurement"))
	@OneToMany(mappedBy = "user",
			cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Measurement> getMeasurements() {
		return this.measurements;
	}

	public void setMeasurements(final Set<Measurement> measurements) {
		this.measurements = measurements;
	}

	@Transient
	public List<Measurement> getMeasurementsAsList() {
		return new ArrayList<Measurement>(measurements);
	}

	@Column(name = "login")
	public String getLogin() {
		return login;
	}

	public void setLogin(final String login) {
		this.login = login;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@ManyToMany
	@JoinTable(name = "user_role",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(final Set<Role> roles) {
		this.roles = roles;
	}

}
