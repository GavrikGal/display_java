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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * измеренный спектр.
	 */
	@ManyToOne
	@JoinColumn(name = "measurement_id", updatable = true)
	private Measurement measurement;

	/**
	 * Параметры спектра (измеряемая величина, разрешение, тип измерений и
	 * т.д.).
	 */
//	@ManyToOne
//	@JoinColumn(name = "parameter_id")
//	private SpectrumParameter parameter;

	/**
	 * версия спектра.
	 */
	@Column(name = "version")
	private int	version;

	/**
	 * Описание измеренного спектра, либо комментарии к измеренному спектру
	 * (типа: соотв. и т.д.).
	 */
	@Column(name = "description")
	private String description;

	/**
	 * Время измерения спектра.
	 */
	@Column(name = "date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(iso = ISO.DATE)
	private DateTime date;

	/**
	 * Список спектральных составляющих - гармоник спектра.
	 */
	@OneToMany(mappedBy = "spectrum",
			cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("frequency")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Harmonic> harmonics;



	/**
	 * список параметров спектра.
	 */
	@ManyToMany
	@JoinTable(name = "specrum_parameters", joinColumns = @JoinColumn(name
	= "spectrum_id"), inverseJoinColumns = @JoinColumn(name = "parameter_id"))
	@OrderBy("type")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Parameter> parameters;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Harmonic> getHarmonics() {
		return this.harmonics;
	}

	public void setHarmonics(final List<Harmonic> harmonics) {
		this.harmonics = harmonics;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Measurement getMeasurement() {
		return measurement;
	}

	public void setMeasurement(final Measurement measurement) {
		this.measurement = measurement;
	}

//	public SpectrumParameter getParameter() {
//		return parameter;
//	}
//
//	public void setParameter(
//			final SpectrumParameter parameter) {
//		this.parameter = parameter;
//	}

	public int getVersion() {
		return version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(final DateTime date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

}
