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
@Table(name = "users")
public class User implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = 7925769595205799558L;

	/**
	 * ID пользователя.
	 */
	private Long idUser;
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
	private String userName;
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
	@Column(name = "id_User")
	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(final Long idUsers) {
		this.idUser = idUsers;
	}

	@Column(name = "First_name")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "Last_name")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "Father_name")
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

	@Column(name = "User_name")
	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	@Column(name = "Password")
	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	@ManyToMany
	@JoinTable(name = "users_role",
		joinColumns = @JoinColumn(name = "user"),
		inverseJoinColumns = @JoinColumn(name = "role"))
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(final Set<Role> roles) {
		this.roles = roles;
	}

}
