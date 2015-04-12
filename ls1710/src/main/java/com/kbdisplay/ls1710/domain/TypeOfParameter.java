package com.kbdisplay.ls1710.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * типы параметров спектра из БД.
 * @author Gavrik
 *
 */
@Entity
@Table(name = "type_parameter")
public class TypeOfParameter implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID типа параметра.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * название типа параметра.
	 */
	@Column(name = "name")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
