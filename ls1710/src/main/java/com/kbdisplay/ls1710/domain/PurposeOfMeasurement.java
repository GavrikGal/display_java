package com.kbdisplay.ls1710.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Объект цели измерений/испытаний из БД.
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "purpose")
public class PurposeOfMeasurement implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = 3847060908637301976L;
	/**
	 * ID цели измерений/испытаний.
	 */
	private Long id;
	/**
	 * Цель испытаний.
	 */
	private String name;

	/**
	 * цель испытаний, которая будет следовать за текущими испытаниями.
	 * например после приемочных испытаний следуют приемосдаточные испытания.
	 */
	private Long prevPurpose;

	/**
	 * описание цели испытаний.
	 */
	private String description;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(
			final Long id) {
		this.id = id;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Column(name = "prev_purpose")
	public Long getPrevPurpose() {
		return prevPurpose;
	}

	public void setPrevPurpose(final Long prevPurpose) {
		this.prevPurpose = prevPurpose;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
