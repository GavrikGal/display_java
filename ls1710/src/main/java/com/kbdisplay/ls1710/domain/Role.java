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
 * Объект ролей пользователей (юзеры, администраторы) из БД.
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "role")
public class Role implements Serializable {
	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = -684162762837065878L;

	/**
	 * ID роли.
	 */
	private Long id;

	/**
	 * Роль.
	 */
	private String name;

	/**
	 * Список пользователей, имеющих данную роль.
	 */
	private Set<User> users;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@ManyToMany
	@JoinTable(name = "user_role",
		joinColumns = @JoinColumn(name = "role_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id"))
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(final Set<User> users) {
		this.users = users;
	}

}
