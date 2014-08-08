package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	 * @deprecated будет заменена версией измерений.
	 */
	@Deprecated
	private DateOfMeasurement dateOfSecondMeasurement;
	// TODO Заменить дату повторного измерения на версию измерений
	// private String version (1.1, 1.2, 2.1 ... 5.3 ... );
	/**
	 * версия измерения.
	 * первое измерение изделия имеет версию 1.0, последующие
	 * измерения этого изделия, входящие в один цикл измерений, номеруются по
	 * порядку после точки, типа 1.1, 1.2... 1.5. следующий цикл измерений
	 * должен увеличить цифру перед точкой, а цифпу после точки установить в
	 * ноль. Таким образом если изделие проверяется повторно как на ПСИ
	 * испытаниях, то измерения получат номер версии х.1 или выше. Если
	 * требуется измерить изделие повторно на ПИ и периодических испытаниях, то
	 * номер версии будет 2.0, 3.0 или выше. Также если испытания имеют
	 * экспериментальный характер, то версии испытаний номируются после точки,
	 * при этом сохранится логическая последовательность (цепочка)
	 * испытаний/измерений.
	 */
	private String version;
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
	public DateOfMeasurement getDateOfMeasurement() {
		return dateOfMeasurement;
	}

	public void setDateOfMeasurement(final DateOfMeasurement date) {
		this.dateOfMeasurement = date;
	}

	@ManyToOne
	@JoinColumn(name = "Date_of_second_measurement")
	public DateOfMeasurement getDateOfSecondMeasurement() {
		return dateOfSecondMeasurement;
	}

	public void setDateOfSecondMeasurement(
			final DateOfMeasurement dateOfSecondMeasurement) {
		this.dateOfSecondMeasurement = dateOfSecondMeasurement;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Measurements")
	public Long getIdMeasurements() {
		return idMeasurements;
	}

	public void setIdMeasurements(final Long idMeasurements) {
		this.idMeasurements = idMeasurements;
	}

	@ManyToOne
	@JoinColumn(name = "Equipment")
	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(final Equipment equipment) {
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
	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "measurement",
			cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("dateTime")
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<Spectrum> getSpectrums() {
		return spectrums;
	}

	public void setSpectrums(final List<Spectrum> spectrums) {
		this.spectrums = spectrums;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
