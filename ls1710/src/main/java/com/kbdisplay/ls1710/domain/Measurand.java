package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Объект измеряемой физической величины (напряженность, напряжение, ток) из
 * БД.
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "measurands")
public class Measurand implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = 4305595715434295082L;
	/**
	 * ID измеряемой физической величины. В качестве ID используется название
	 * измеряемой величины.
	 */
	private String idMeasurands;
	/**
	 * Список параметров спектров, в которых используется данная физическая
	 * величина.
	 */
	private Set<SpectrumParameter> spectrumsParameters =
			new HashSet<SpectrumParameter>();

	@OneToMany(mappedBy = "measurand",
			cascade = CascadeType.ALL, orphanRemoval = true)
	public final Set<SpectrumParameter> getSpectrumsParameters() {
		return this.spectrumsParameters;
	}

	public final void setSpectrumsParameters(
			final Set<SpectrumParameter> spectrumsParameters) {
		this.spectrumsParameters = spectrumsParameters;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@Column(name = "id_Measurand")
	public final String getIdMeasurands() {
		return idMeasurands;
	}

	public final void setIdMeasurands(final String idMeasurands) {
		this.idMeasurands = idMeasurands;
	}

	@Override
	public final String toString() {
		return idMeasurands;
	}
}
