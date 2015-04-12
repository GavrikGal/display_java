package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Объект параметров спектра из БД.
 *
 * @author Gavrik
 */
@Entity
@Table(name = "parameter")
public class Parameter implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID параметра.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * тип параметра.
	 */
	@ManyToOne
	@JoinColumn(name = "type_id")
	private TypeOfParameter type;

	/**
	 * название параметра.
	 */
	@Column(name = "name")
	private String name;

	/**
	 * родительские параметры спектра.
	 */
	@ManyToMany
	@JoinTable(name = "relationship_parameters", joinColumns = @JoinColumn(
			name = "child_id"), inverseJoinColumns = @JoinColumn(
			name = "parent_id"))
	private List<Parameter> parentParameters;

	/**
	 * дочерние параметры спектра.
	 */
	@ManyToMany
	@JoinTable(name = "relationship_parameters", joinColumns = @JoinColumn(
			name = "parent_id"), inverseJoinColumns = @JoinColumn(
			name = "child_id"))
	private List<Parameter> childParameters;

	/**
	 * спектры с этим параметром.
	 */
	@ManyToMany
	@JoinTable(name = "specrum_parameters", joinColumns = @JoinColumn(
			name = "parameter_id"), inverseJoinColumns = @JoinColumn(
			name = "spectrum_id"))
	private List<Spectrum> spectrums;


	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public TypeOfParameter getType() {
		return type;
	}

	public void setType(final TypeOfParameter type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public List<Parameter> getParentParameters() {
		return parentParameters;
	}

	public void setParentParameters(final List<Parameter> parentParameters) {
		this.parentParameters = parentParameters;
	}

	public List<Parameter> getChildParameters() {
		return childParameters;
	}

	public void setChildParameters(final List<Parameter> childParameters) {
		this.childParameters = childParameters;
	}

	public List<Spectrum> getSpectrums() {
		return spectrums;
	}

	public void setSpectrums(final List<Spectrum> spectrums) {
		this.spectrums = spectrums;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
