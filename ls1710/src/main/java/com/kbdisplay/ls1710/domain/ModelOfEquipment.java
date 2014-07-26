package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Объект модели изделия из БД.
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "models")
public class ModelOfEquipment implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = 7092054057261196283L;
	/**
	 * ID модели изделия.
	 */
	private Long idModel;
	/**
	 * Название модели.
	 */
	private String modelName;
	/**
	 * Описание модели изделия.
	 */
	private String description;
	/**
	 * Фото модели изделия.
	 */
	private byte[] photo;
	/**
	 * Список изделий данной модели.
	 */
	private Set<Equipment> equipments = new HashSet<Equipment>();
	/**
	 * Список норм, которым должно соответствовать эта модель изделия.
	 */
	private List<Norm> norms;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Model")
	public final Long getIdModel() {
		return idModel;
	}

	public final void setIdModel(final Long idModel) {
		this.idModel = idModel;
	}

	@OneToMany(mappedBy = "model",
			cascade = CascadeType.ALL, orphanRemoval = true)
	public final Set<Equipment> getEquipments() {
		return this.equipments;
	}

	public final void setEquipments(final Set<Equipment> equipments) {
		this.equipments = equipments;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public final String toString() {
		return modelName;
	}

	@Column(name = "Description")
	public final String getDescription() {
		return description;
	}

	public final void setDescription(final String description) {
		this.description = description;
	}

	@Basic(fetch = FetchType.LAZY)
	@Lob
	@Column(name = "Photo")
	public final byte[] getPhoto() {
		return photo;
	}

	public final void setPhoto(final byte[] photo) {
		this.photo = photo;
	}

	@ManyToMany
	@JoinTable(name = "norms_of_model",
		joinColumns = @JoinColumn(name = "Model"),
		inverseJoinColumns = @JoinColumn(name = "Norms"))
	@OrderBy("idNorms")
	@LazyCollection(LazyCollectionOption.FALSE)
	public final List<Norm> getNorms() {
		return norms;
	}

	public final void setNorms(final List<Norm> norms) {
		this.norms = norms;
	}

	@Column(name = "Model_name")
	public final String getModelName() {
		return modelName;
	}

	public final void setModelName(final String modelName) {
		this.modelName = modelName;
	}

}
