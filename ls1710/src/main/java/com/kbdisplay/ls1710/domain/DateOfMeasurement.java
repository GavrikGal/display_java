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
 * ������ �� �� �������� ���� ���������.
 * @author Gavrik
 */
@Entity
@Table(name = "date_of_measurement")
public class DateOfMeasurement implements Serializable {

	/**
	 * ������ �������� �����.
	 */
	private static final long serialVersionUID = -544910450691585253L;
	/**
	 * ID ���� � ��.
	 */
	private Long idDate;
	/**
	 * ���� ���������.
	 */
	private DateTime date;
	/**
	 * ������ ��������� � ��������������� �����.
	 */
	private Set<Measurement> measurements = new HashSet<Measurement>();
	/**
	 * ������ ��������� ���������.
	 */
	private Set<Measurement> secondMeasurements = new HashSet<Measurement>();

	/**
	 * ��������� ������ ��������� ��������� ��������������� ����.
	 *
	 * @return ������ ��������� ���������
	 */
	@OneToMany(mappedBy = "dateOfSecondMeasurement",
			cascade = CascadeType.ALL, orphanRemoval = true)
	public final Set<Measurement> getSecondMeasurements() {
		return secondMeasurements;
	}

	/**
	 * ��������� ������ ��������� ���������.
	 *
	 * @param secondMeasurements
	 *            - ������ ��������� ���������
	 */
	public final void setSecondMeasurements(
			final Set<Measurement> secondMeasurements) {
		this.secondMeasurements = secondMeasurements;
	}

	/**
	 * ����� ���� ���������, ��������������� ����, � �� � ���������.
	 *
	 * @return ������ ��������� ��������������� ����
	 */
	@OneToMany(mappedBy = "dateOfMeasurement",
			cascade = CascadeType.ALL, orphanRemoval = true)
	public final Set<Measurement> getMeasurements() {
		return measurements;
	}

	/**
	 * ��������� ������ ���������, ��������������� ����.
	 *
	 * @param measurements
	 *            - ������ ���������
	 */
	public final void setMeasurements(final Set<Measurement> measurements) {
		this.measurements = measurements;
	}

	/**
	 * ��������� ID ���� ���������.
	 *
	 * @return ID ���� ���������
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Date_of_measurement")
	public final Long getIdDate() {
		return idDate;
	}

	/**
	 * ��������� ID ���� ���������.
	 *
	 * @param idDate
	 *            - ���� ���������
	 */
	public final void setIdDate(final Long idDate) {
		this.idDate = idDate;
	}

	/**
	 * ��������� ���� ���������.
	 *
	 * @return ���� ���������
	 */
	@Column(name = "Date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(iso = ISO.DATE)
	public final DateTime getDate() {
		return date;
	}

	/**
	 * ��������� ���� ���������.
	 *
	 * @param date
	 *            - ���� ���������
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
	 * ��������� ��������� ������.
	 *
	 * @return �������� ������
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
