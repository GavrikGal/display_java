package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Объект даты испытаний из БД.
 *
 * @author Gavrik
 */
@Entity
@Table(name = "date")
public class DateOfMeasurement implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = -544910450691585253L;

	/**
	 * ID даты испытаний.
	 */
	private Long id;
	/**
	 * Дата испытаний.
	 */
	private DateTime date;

	/**
	 * Список измерений, соответствующих дате испытаний.
	 */
	private Set<Measurement> measurements = new HashSet<Measurement>();


	@OneToMany(mappedBy = "date", cascade = CascadeType.ALL,
			orphanRemoval = true)
	public Set<Measurement> getMeasurements() {
		return measurements;
	}


	public void setMeasurements(final Set<Measurement> measurements) {
		this.measurements = measurements;
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


	@Column(name = "date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(iso = ISO.DATE)
	public DateTime getDate() {
		return date;
	}


	public void setDate(final DateTime date) {
		this.date = date;
	}


	// @Transient
	// public String getDateString() {
	// String dateString = "";
	// if (date != null) {
	// dateString =
	// org.joda.time.format.DateTimeFormat.forPattern("dd.MM.yyyy").print(
	// date);
	// }
	// return dateString;
	// }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
