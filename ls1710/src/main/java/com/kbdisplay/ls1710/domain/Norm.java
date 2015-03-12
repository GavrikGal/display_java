package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Объект норм, которым должно соответствовать испытуемое изделие, из БД.
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "norm")
public class Norm implements Serializable {

	/**
	 * Серийный номер модели.
	 */
	private static final long serialVersionUID = 9089008554064871620L;
	/**
	 * ID норм.
	 */
	private Long id;
	/**
	 * Сокращенная запись норм (гр.1.1.2 либо R2=15м).
	 */
	private String name;
	/**
	 * Список измерений, которым соответствуют данная норма.
	 */
	private Set<Measurement> measurements;

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


	@OneToMany(mappedBy = "norm", cascade = CascadeType.ALL,
			orphanRemoval = true)
	public Set<Measurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(final Set<Measurement> measurements) {
		this.measurements = measurements;
	}

}
