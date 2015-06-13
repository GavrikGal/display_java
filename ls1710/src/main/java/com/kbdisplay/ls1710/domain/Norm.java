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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * Объект норм, которым должно соответствовать испытуемое изделие, из БД.
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "norm")
public class Norm implements Serializable {

	/**
	 * Серийный номер модели.
	 */
	private static final long serialVersionUID = 9089008554064871620L;
	/**
	 * ID норм.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	/**
	 * Сокращенная запись норм (гр.1.1.2 либо R2=15м).
	 */
	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "standard_id")
	private Standard standard;

	@ManyToOne
	@JoinColumn(name = "norm_handler_id")
	private NormHandler normHandler;

	@OneToMany(mappedBy = "norm", cascade = CascadeType.MERGE,
			orphanRemoval = true)
	@OrderBy("frequency")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Limit> limits;

	@ManyToMany
	@JoinTable(name = "document_norm", joinColumns = @JoinColumn(
			name = "norm_id"), inverseJoinColumns = @JoinColumn(
			name = "document_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Document> documents;

	@ManyToMany
	@JoinTable(name = "norm_parameters", joinColumns = @JoinColumn(
			name = "norm_id"), inverseJoinColumns = @JoinColumn(
			name = "parameter_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Parameter> parameters;


	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Standard getStandard() {
		return standard;
	}

	public void setStandard(Standard standard) {
		this.standard = standard;
	}

	public NormHandler getNormHandler() {
		return normHandler;
	}

	public void setNormHandler(NormHandler normHandler) {
		this.normHandler = normHandler;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Limit> getLimits() {
		return limits;
	}

	public void setLimits(List<Limit> limits) {
		this.limits = limits;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

}
