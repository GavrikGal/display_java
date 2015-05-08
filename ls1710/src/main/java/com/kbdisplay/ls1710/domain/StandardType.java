package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "standard_type")
public class StandardType implements Serializable {

	/**
	 * Серийный номер модели.
	 */
	private static final long serialVersionUID = -5483954723303788029L;

	/**
	 * ID норм.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	/**
	 * Сокращенная запись норм (гр.1.1.2 либо R2=15м).
	 */
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "standardType", cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Standard> standards;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Standard> getStandards() {
		return standards;
	}

	public void setStandards(List<Standard> standards) {
		this.standards = standards;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
