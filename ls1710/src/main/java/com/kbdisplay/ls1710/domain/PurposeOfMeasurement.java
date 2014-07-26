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
@Table(name = "purpose_of_measurement")
public class PurposeOfMeasurement implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = 3847060908637301976L;
	/**
	 * ID цели измерений/испытаний.
	 */
	private Long idPurposeOfMeasurement;
	/**
	 * Цель испытаний.
	 */
	private String purpose;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Purpose_of_measurement")
	public final Long getIdPurposeOfMeasurement() {
		return idPurposeOfMeasurement;
	}

	public final void setIdPurposeOfMeasurement(
			final Long idPurposeOfMeasurement) {
		this.idPurposeOfMeasurement = idPurposeOfMeasurement;
	}

	@Column(name = "Purpose")
	public final String getPurpose() {
		return purpose;
	}

	public final void setPurpose(final String purpose) {
		this.purpose = purpose;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
