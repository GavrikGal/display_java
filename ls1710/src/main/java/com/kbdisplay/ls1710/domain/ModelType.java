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

/**
 * Объект типа модели изделия (видеомонитор, комп и т.д.) из БД.
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "model_type")
public class ModelType implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID типа модели.
	 */
	private Long id;

	/**
	 * название типа модели.
	 */
	private String name;

	/**
	 * описание типа модели.
	 */
	private String description;

	/**
	 * Список моделей, в которых используется данный тип.
	 */
	private Set<ModelOfEquipment> modelOfEquipments =
			new HashSet<ModelOfEquipment>();


	@OneToMany(mappedBy = "modelType", cascade = CascadeType.ALL,
			orphanRemoval = true)
	public Set<ModelOfEquipment> getModelOfEquipments() {
		return this.modelOfEquipments;
	}

	public void setModelOfEquipments(
			final Set<ModelOfEquipment> modelOfEquipments) {
		this.modelOfEquipments = modelOfEquipments;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return name;
	}

}
