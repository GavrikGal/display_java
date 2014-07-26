package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.sql.Time;
//import java.util.ArrayList;
//import java.util.HashSet;
import java.util.List;
//import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Объект измеренного спектра из БД.
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "spectrums")
public class Spectrum implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = 822452653650724060L;

	/**
	 * ID измеренного спектра.
	 */
	private Long idSpectrums;
	/**
	 * измеренный спектр.
	 */
	private Measurement measurement;
	/**
	 * Параметры спектра (измеряемая величина, разрешение, тип измерений и
	 * т.д.).
	 */
	private SpectrumParameter spectrumParameters;
	/**
	 * Описание измеренного спектра, либо комментарии к измеренному спектру
	 * (типа: соотв. и т.д.).
	 */
	private String description;
	/**
	 * Время измерения спектра.
	 */
	private Time time;
	/**
	 * Список спектральных составляющих - гармоник спектра.
	 */
	private List<Harmonic> harmonics;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@OneToMany(mappedBy = "spectrum",
			cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("frequency")
	@LazyCollection(LazyCollectionOption.FALSE)
	public final List<Harmonic> getHarmonics() {
		return this.harmonics;
	}

	public final void setHarmonics(final List<Harmonic> harmonics) {
		this.harmonics = harmonics;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Spectrums")
	public final Long getIdSpectrums() {
		return idSpectrums;
	}

	public final void setIdSpectrums(final Long idSpectrums) {
		this.idSpectrums = idSpectrums;
	}

	@ManyToOne
	@JoinColumn(name = "Measurements")
	public final Measurement getMeasurement() {
		return measurement;
	}

	public final void setMeasurement(final Measurement measurement) {
		this.measurement = measurement;
	}

	@ManyToOne
	@JoinColumn(name = "Spectrum_parameters")
	public final SpectrumParameter getSpectrumParameters() {
		return spectrumParameters;
	}

	public final void setSpectrumParameters(
			final SpectrumParameter spectrumParameters) {
		this.spectrumParameters = spectrumParameters;
	}

	@Column(name = "Time")
	public final Time getTime() {
		return time;
	}

	public final void setTime(final Time time) {
		this.time = time;
	}

	@Column(name = "Description")
	public final String getDescription() {
		return description;
	}

	public final void setDescription(final String description) {
		this.description = description;
	}

}
