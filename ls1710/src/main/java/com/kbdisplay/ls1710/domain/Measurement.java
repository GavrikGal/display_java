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
@Table(name = "measurement")
public class Measurement implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = -1257165263393374914L;

	/**
	 * ID измерения/испытания.
	 */
	private Long id;

	/**
	 * испытуемое оборудование.
	 */
	private Equipment equipment;

	/**
	 * Дата измерений.
	 */
	private DateOfMeasurement date;

	/**
	 * версия измерения.
	 */
	private int version;

	/**
	 * цель измерений (пи/пси/типовые/периодические).
	 */
	private PurposeOfMeasurement purpose;

	/**
	 * Пользователь проводивший измерения.
	 */
	private User user;

	/**
	 * требования(нормы) для измерения.
	 */
	private Norm norm;

	// TODO заменить на результаты испытаний
	/**
	 * Список спектров, которые включает в себя измерение.
	 */
	private List<Spectrum> spectrums;


	@ManyToOne
	@JoinColumn(name = "date_id")
	public DateOfMeasurement getDate() {
		return date;
	}

	public void setDate(final DateOfMeasurement date) {
		this.date = date;
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
	@JoinColumn(name = "equipment_id")
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
	@JoinColumn(name = "user_id")
	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "measurement",
			cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("date")
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<Spectrum> getSpectrums() {
		return spectrums;
	}

	public void setSpectrums(final List<Spectrum> spectrums) {
		this.spectrums = spectrums;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@ManyToOne
	@JoinColumn(name = "purpose_id")
	public PurposeOfMeasurement getPurpose() {
		return purpose;
	}

	public void setPurpose(final PurposeOfMeasurement purpose) {
		this.purpose = purpose;
	}

	@ManyToOne
	@JoinColumn(name = "norm_id")
	public Norm getNorm() {
		return norm;
	}

	public void setNorm(final Norm norm) {
		this.norm = norm;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
