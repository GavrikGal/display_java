package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Объект норм, которым должно соответствовать испытуемое изделие, из БД.
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "norms")
public class Norm implements Serializable {

	/**
	 * Серийный номер модели.
	 */
	private static final long serialVersionUID = 9089008554064871620L;
	/**
	 * ID норм.
	 */
	private Long idNorms;
	/**
	 * Сокращенная запись норм (гр.1.1.2 либо R2=15м).
	 */
	private String shortNorms;
	/**
	 * Список моделей, которым соответствуют данная норма.
	 */
	private Set<ModelOfEquipment> models;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Norms")
	public final Long getIdNorms() {
		return idNorms;
	}

	public final void setIdNorms(final Long idNorms) {
		this.idNorms = idNorms;
	}

	@Column(name = "Short_norms")
	public final String getShortNorms() {
		return shortNorms;
	}

	public final void setShortNorms(final String shortNorms) {
		this.shortNorms = shortNorms;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@ManyToMany
	@JoinTable(name = "norms_of_model",
		joinColumns = @JoinColumn(name = "Norms"),
		inverseJoinColumns = @JoinColumn(name = "Model"))
	public final Set<ModelOfEquipment> getModels() {
		return models;
	}

	public final void setModels(final Set<ModelOfEquipment> models) {
		this.models = models;
	}

}
