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
@Table(name = "spectrum")
public class Spectrum implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = 822452653650724060L;

	/**
	 * ID измеренного спектра.
	 */
	private Long id;

	/**
	 * измеренный спектр.
	 */
	private Measurement measurement;

	/**
	 * Параметры спектра (измеряемая величина, разрешение, тип измерений и
	 * т.д.).
	 */
	private SpectrumParameter parameter;

	/**
	 * версия спектра.
	 */
	private int	version;

	/**
	 * Описание измеренного спектра, либо комментарии к измеренному спектру
	 * (типа: соотв. и т.д.).
	 */
	private String description;

	/**
	 * Время измерения спектра.
	 */
	private DateTime date;

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
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "measurement_id")
	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(final Measurement measurement) {
		this.measurement = measurement;
	}

	@ManyToOne
	@JoinColumn(name = "parameter_id")
	public SpectrumParameter getParameter() {
		return parameter;
	}

	public void setParameter(
			final SpectrumParameter parameter) {
		this.parameter = parameter;
	}

	@Column(name = "version")
	public int getVersion() {
		return version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Column(name = "date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(iso = ISO.DATE)
	public DateTime getDate() {
		return date;
	}

	public void setDate(final DateTime date) {
		this.date = date;
	}


//	@Column(name = "Time")
//	public Time getTime() {
//		return time;
//	}
//
//	public void setTime(final Time time) {
//		this.time = time;
//	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

}
