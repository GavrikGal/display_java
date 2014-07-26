package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.List;

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
 * Объект проводимых измерений/испытаний из БД.
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "measurements")
public class Measurement implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = -1257165263393374914L;

	/**
	 * ID измерения/испытания.
	 */
	private Long idMeasurements;
	/**
	 * ф.
	 */
	private Equipment equipment;
	/**
	 * Дата измерений.
	 */
	private DateOfMeasurement dateOfMeasurement;
	/**
	 * Дата повторных измерений.
	 */
	private DateOfMeasurement dateOfSecondMeasurement;
	// TODO Заменить дату повторного измерения на версию измерений
	// private String version (1.1, 1.2, 2.1 ... 5.3 ... );
	/**
	 * Список спектров, которые включает в себя измерение.
	 */
	private List<Spectrum> spectrums;
	/**
	 * Пользователь проводивший измерения.
	 */
	private User user;

	@ManyToOne
	@JoinColumn(name = "Date_of_measurement")
	public final DateOfMeasurement getDateOfMeasurement() {
		return dateOfMeasurement;
	}

	public final void setDateOfMeasurement(final DateOfMeasurement date) {
		this.dateOfMeasurement = date;
	}

	@ManyToOne
	@JoinColumn(name = "Date_of_second_measurement")
	public final DateOfMeasurement getDateOfSecondMeasurement() {
		return dateOfSecondMeasurement;
	}

	public final void setDateOfSecondMeasurement(
			final DateOfMeasurement dateOfSecondMeasurement) {
		this.dateOfSecondMeasurement = dateOfSecondMeasurement;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Measurements")
	public final Long getIdMeasurements() {
		return idMeasurements;
	}

	@ManyToOne
	@JoinColumn(name = "Equipment")
	public final Equipment getEquipment() {
		return equipment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public final void setIdMeasurements(final Long idMeasurements) {
		this.idMeasurements = idMeasurements;
	}

	public final void setEquipment(final Equipment equipment) {
		this.equipment = equipment;
	}

	// public void setUsers(List<Users> users) {
	// this.users = users;
	// }
	//
	// @ManyToMany
	// @JoinTable(name = "users_of_measurement", joinColumns = @JoinColumn(name
	// = "Measurement"), inverseJoinColumns = @JoinColumn(name = "User"))
	// @OrderBy("firstName")
	// @LazyCollection(LazyCollectionOption.FALSE)
	// public List<Users> getUsers() {
	// return users;
	// }

	@ManyToOne
	@JoinColumn(name = "User")
	public final User getUser() {
		return user;
	}

	public final void setUser(final User user) {
		this.user = user;
	}

	@OneToMany(mappedBy = "measurement",
			cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("time")
	@LazyCollection(LazyCollectionOption.FALSE)
	public final List<Spectrum> getSpectrums() {
		return spectrums;
	}

	public final void setSpectrums(final List<Spectrum> spectrums) {
		this.spectrums = spectrums;
	}

}
