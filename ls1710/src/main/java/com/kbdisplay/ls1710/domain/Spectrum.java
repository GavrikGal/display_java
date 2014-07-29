package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
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
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

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
	private DateTime dateTime;
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
	public List<Harmonic> getHarmonics() {
		return this.harmonics;
	}

	public void setHarmonics(final List<Harmonic> harmonics) {
		this.harmonics = harmonics;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Spectrums")
	public Long getIdSpectrums() {
		return idSpectrums;
	}

	public void setIdSpectrums(final Long idSpectrums) {
		this.idSpectrums = idSpectrums;
	}

	@ManyToOne
	@JoinColumn(name = "Measurements")
	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(final Measurement measurement) {
		this.measurement = measurement;
	}

	@ManyToOne
	@JoinColumn(name = "Spectrum_parameters")
	public SpectrumParameter getSpectrumParameters() {
		return spectrumParameters;
	}

	public void setSpectrumParameters(
			final SpectrumParameter spectrumParameters) {
		this.spectrumParameters = spectrumParameters;
	}



	@Column(name = "DateTime")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(iso = ISO.DATE)
	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(final DateTime dateTime) {
		this.dateTime = dateTime;
	}


//	@Column(name = "Time")
//	public Time getTime() {
//		return time;
//	}
//
//	public void setTime(final Time time) {
//		this.time = time;
//	}

	@Column(name = "Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

}
