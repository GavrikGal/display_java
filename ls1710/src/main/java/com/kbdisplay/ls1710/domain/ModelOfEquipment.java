package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.HashSet;
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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Объект модели изделия из БД.
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "model")
public class ModelOfEquipment implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = 7092054057261196283L;

	/**
	 * ID модели изделия.
	 */
	private Long id;

	/**
	 * Название модели.
	 */
	private String name;

	/**
	 * тип модели.
	 */
	private ModelType modelType;

	/**
	 * Описание модели изделия.
	 */
	private String description;

	private Document document;

	/**
	 * Фото модели изделия.
	 */
	private byte[] photo;

	/**
	 * Список изделий данной модели.
	 */
	private Set<Equipment> equipments = new HashSet<Equipment>();


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@OneToMany(mappedBy = "model", cascade = CascadeType.ALL,
			orphanRemoval = true)
	public Set<Equipment> getEquipments() {
		return this.equipments;
	}

	public void setEquipments(final Set<Equipment> equipments) {
		this.equipments = equipments;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public final String toString() {
		return name;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	@Basic(fetch = FetchType.LAZY)
	@Lob
	@Column(name = "photo")
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(final byte[] photo) {
		this.photo = photo;
	}

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn(name = "model_type_id")
	public ModelType getModelType() {
		return modelType;
	}

	public void setModelType(final ModelType modelType) {
		this.modelType = modelType;
	}

	@ManyToOne
	@JoinColumn(name = "document_id")
	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}



}
