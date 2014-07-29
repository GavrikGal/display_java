package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

/**
 * Объект испытуемого изделия из БД.
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "equipments")
public class Equipment implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = 7277923833960735466L;

	/**
	 * ID изделия.
	 */
	private Long idEquipment;
	/**
	 * Модель изделия.
	 */
	private ModelOfEquipment model;
	/**
	 * Серийный номер изделия.
	 */
	private String serialNumber;
	/**
	 * Список измерений, проведенных с данным изделием.
	 */
	private Set<Measurement> measurements = new HashSet<Measurement>();


	/**
	 * Получение серийного номера класса.
	 *
	 * @return серийный номер класса
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/**
	 * Получение ID изделия.
	 *
	 * @return ID изделия
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Equipment")
	public Long getIdEquipment() {
		return idEquipment;
	}


	/**
	 * Установка ID изделия.
	 *
	 * @param idEquipment
	 *            ID изделия
	 */
	public void setIdEquipment(final Long idEquipment) {
		this.idEquipment = idEquipment;
	}


	/**
	 * Получение модели изделия.
	 *
	 * @return модель изделия
	 */
	@ManyToOne
	@JoinColumn(name = "Model")
	public ModelOfEquipment getModel() {
		return model;
	}


	/**
	 * Установка модели изделия.
	 *
	 * @param model
	 *            модель изделия
	 */
	public void setModel(final ModelOfEquipment model) {
		this.model = model;
	}


	/**
	 * Получение серийного номера изделия.
	 *
	 * @return серийный номер изделия
	 */
	@Column(name = "Serial_number")
	public String getSerialNumber() {
		return serialNumber;
	}


	/**
	 * Установка серийного номера изделия.
	 *
	 * @param serialNumber
	 *            серийный номер изделия
	 */
	public void setSerialNumber(final String serialNumber) {
		this.serialNumber = serialNumber;
	}


	/**
	 * Получение списка измерений, соответствующих изделию.
	 *
	 * тут fetch позволяет получить список данных из БД. без него будет ошибка
	 * Lazy-что-то там...
	 *
	 * @return список измерений модели
	 */
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "equipment",
			cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("version")
	public Set<Measurement> getMeasurements() {
		return this.measurements;
	}


	/**
	 * Установка списка измерений, соответствующих изделию.
	 *
	 * @param measurements
	 *            список измерений модели
	 */
	public void setMeasurements(final Set<Measurement> measurements) {
		this.measurements = measurements;
	}

}
