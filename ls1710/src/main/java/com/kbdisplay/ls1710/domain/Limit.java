package com.kbdisplay.ls1710.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Объект величины нормы из БД.
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "norm_limit")
public class Limit implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = -859155391137462138L;

	/**
	 * ID объекта.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * норма, к которой относится лимит.
	 */
	@ManyToOne
	@JoinColumn(name = "norm_id", updatable = true)
	private Norm norm;

	/**
	 * Частота.
	 */
	@Column(name = "frequency")
	private Double frequency;

	/**
	 * Амплитуда.
	 */
	@Column(name = "amplitude")
	private Double amplitude;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Norm getNorm() {
		return norm;
	}

	public void setNorm(Norm norm) {
		this.norm = norm;
	}

	public Double getFrequency() {
		return frequency;
	}

	public void setFrequency(Double frequency) {
		this.frequency = frequency;
	}

	public Double getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(Double amplitude) {
		this.amplitude = amplitude;
	}

}
