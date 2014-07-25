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
 * Объект из БД хранящий дату измерения.
 * @author Gavrik
 */
@Entity
@Table(name = "date_of_measurement")
public class DateOfMeasurement implements Serializable {

	/**
	 * Просто серийный номер.
	 */
	private static final long serialVersionUID = -544910450691585253L;
	/**
	 * ID даты в БД.
	 */
	private Long idDate;
	/**
	 * Дата измерений.
	 */
	private DateTime date;
	/**
	 * Список измерений с соответствующей датой.
	 */
	private Set<Measurement> measurements = new HashSet<Measurement>();
	/**
	 * Список повторных измерений.
	 */
	private Set<Measurement> secondMeasurements = new HashSet<Measurement>();

	/**
	 * Получение списка повторных измерений соответствующих дате.
	 *
	 * @return Список повторных измерений
	 */
	@OneToMany(mappedBy = "dateOfSecondMeasurement",
			cascade = CascadeType.ALL, orphanRemoval = true)
	public final Set<Measurement> getSecondMeasurements() {
		return secondMeasurements;
	}

	/**
	 * Установка списка повторных измерения.
	 *
	 * @param secondMeasurements
	 *            - список повторных измерений
	 */
	public final void setSecondMeasurements(
			final Set<Measurement> secondMeasurements) {
		this.secondMeasurements = secondMeasurements;
	}

	/**
	 * Связь поля измерений, соответствующих дате, в БД с объектами.
	 *
	 * @return список измерений соответствующих дате
	 */
	@OneToMany(mappedBy = "dateOfMeasurement",
			cascade = CascadeType.ALL, orphanRemoval = true)
	public final Set<Measurement> getMeasurements() {
		return measurements;
	}

	/**
	 * Установка списка измерений, соответствующих дате.
	 *
	 * @param measurements
	 *            - список измерений
	 */
	public final void setMeasurements(final Set<Measurement> measurements) {
		this.measurements = measurements;
	}

	/**
	 * Получение ID даты измерений.
	 *
	 * @return ID даты измерений
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Date_of_measurement")
	public final Long getIdDate() {
		return idDate;
	}

	/**
	 * Установка ID даты измерений.
	 *
	 * @param idDate
	 *            - дата измерений
	 */
	public final void setIdDate(final Long idDate) {
		this.idDate = idDate;
	}

	/**
	 * Получение даты измерений.
	 *
	 * @return дата измерений
	 */
	@Column(name = "Date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(iso = ISO.DATE)
	public final DateTime getDate() {
		return date;
	}

	/**
	 * Установка даты измерений.
	 *
	 * @param date
	 *            - дата измерений
	 */
	public final void setDate(final DateTime date) {
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

	/**
	 * Получение серийника класса.
	 *
	 * @return серийник класса
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
