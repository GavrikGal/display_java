package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * <b>Объект ролей пользователей (юзеры, администраторы) из БД.</b>
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "roles")
public class Role implements Serializable {
	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = -684162762837065878L;
	/**
	 * ID роли.
	 */
	private Long idRoles;
	/**
	 * Роль.
	 */
	private String role;
	/**
	 * Список пользователей, имеющих данную роль.
	 */
	private Set<User> users;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Role")
	public final Long getIdRoles() {
		return idRoles;
	}

	public final void setIdRoles(final Long idRoles) {
		this.idRoles = idRoles;
	}

	@Column(name = "Role")
	public final String getRole() {
		return role;
	}

	public final void setRole(final String role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@ManyToMany
	@JoinTable(name = "users_role",
		joinColumns = @JoinColumn(name = "role"),
		inverseJoinColumns = @JoinColumn(name = "user"))
	public final Set<User> getUsers() {
		return users;
	}

	public final void setUsers(final Set<User> users) {
		this.users = users;
	}

}
