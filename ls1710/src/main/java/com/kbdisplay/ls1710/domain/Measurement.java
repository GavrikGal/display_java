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
import javax.persistence.OneToOne;
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
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * испытуемое оборудование.
	 */
	@ManyToOne
	@JoinColumn(name = "equipment_id")
	private Equipment equipment;

	/**
	 * Дата измерений.
	 */
	@ManyToOne
	@JoinColumn(name = "date_id")
	private DateOfMeasurement date;

	/**
	 * версия измерения.
	 */
	private int version;

	/**
	 * цель измерений (пи/пси/типовые/периодические).
	 */
	@ManyToOne
	@JoinColumn(name = "purpose_id")
	private PurposeOfMeasurement purpose;

	/**
	 * Пользователь проводивший измерения.
	 */
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	/**
	 * требования(нормы) для измерения.
	 */
	@ManyToOne
	@JoinColumn(name = "norm_id")
	private Norm norm;

	// /**
	// * предыдущее испытание. например перед испытаниями приемосдаточными
	// * испытаниями идут приемочные испытания, а также перед типовыми
	// испытаниями
	// * могут идти приемосдаточные испытания.
	// */
	// @ManyToOne
	// @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	// @JoinColumn(name = "parent_id")
	// private Measurement parentMeasurement;
	//
	// /**
	// * все испытания идущие после текущего испытания.
	// * например после пи-испытания будет идти пси-испытание
	// */
	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "parentMeasurement",
	// cascade = CascadeType.ALL)
	// @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	// private List<Measurement> nextMeasurements = new
	// ArrayList<Measurement>();

	/**
	 * предыдущее измерение.
	 */
	@OneToOne
	@JoinColumn(name = "parent_id")
	private Measurement parentMeasurement;

	/**
	 * следующее измерение.
	 */
	@OneToOne(mappedBy = "parentMeasurement")
	private Measurement nextMeasurement;

	// TODO заменить на результаты испытаний
	/**
	 * Список спектров, которые включает в себя измерение.
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "measurement",
			cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("date")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Spectrum> spectrums;


	public DateOfMeasurement getDate() {
		return date;
	}

	public void setDate(final DateOfMeasurement date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

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

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

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

	public PurposeOfMeasurement getPurpose() {
		return purpose;
	}

	public void setPurpose(final PurposeOfMeasurement purpose) {
		this.purpose = purpose;
	}

	public Norm getNorm() {
		return norm;
	}

	public void setNorm(final Norm norm) {
		this.norm = norm;
	}

	public Measurement getParentMeasurement() {
		return parentMeasurement;
	}

	public void setParentMeasurement(final Measurement parentMeasurement) {
		this.parentMeasurement = parentMeasurement;
	}

	public Measurement getNextMeasurement() {
		return nextMeasurement;
	}

	public void setNextMeasurement(final Measurement nextMeasurement) {
		this.nextMeasurement = nextMeasurement;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
