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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "document")
public class Document implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4659349920146324406L;

	/**
	 * ID норм.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@ManyToMany
	@JoinTable(name = "document_norm", joinColumns = @JoinColumn(
			name = "document_id"), inverseJoinColumns = @JoinColumn(
			name = "norm_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Norm> norms;

	@OneToMany(mappedBy = "document", cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<ModelOfEquipment> models;

	@OneToMany(mappedBy = "specialDocument", cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<Equipment> equipments;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Norm> getNorms() {
		return norms;
	}

	public void setNorms(List<Norm> norms) {
		this.norms = norms;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ModelOfEquipment> getModels() {
		return models;
	}

	public void setModels(List<ModelOfEquipment> models) {
		this.models = models;
	}

	public List<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}

}
