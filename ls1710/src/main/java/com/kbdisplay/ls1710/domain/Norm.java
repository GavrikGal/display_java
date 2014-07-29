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
	public Long getIdNorms() {
		return idNorms;
	}

	public void setIdNorms(final Long idNorms) {
		this.idNorms = idNorms;
	}

	@Column(name = "Short_norms")
	public String getShortNorms() {
		return shortNorms;
	}

	public void setShortNorms(final String shortNorms) {
		this.shortNorms = shortNorms;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@ManyToMany
	@JoinTable(name = "norms_of_model",
		joinColumns = @JoinColumn(name = "Norms"),
		inverseJoinColumns = @JoinColumn(name = "Model"))
	public Set<ModelOfEquipment> getModels() {
		return models;
	}

	public void setModels(final Set<ModelOfEquipment> models) {
		this.models = models;
	}

}
