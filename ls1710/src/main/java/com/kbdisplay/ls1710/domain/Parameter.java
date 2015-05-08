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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
	 * спектры с этим параметром.
	 */
	@ManyToMany
	@JoinTable(name = "specrum_parameters", joinColumns = @JoinColumn(
			name = "parameter_id"), inverseJoinColumns = @JoinColumn(
			name = "spectrum_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Spectrum> spectrums;

	@ManyToMany
	@JoinTable(name = "norm_parameters", joinColumns = @JoinColumn(
			name = "parameter_id"), inverseJoinColumns = @JoinColumn(
			name = "norm_id"))
	private List<Norm> norms;


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

	public List<Spectrum> getSpectrums() {
		return spectrums;
	}

	public void setSpectrums(final List<Spectrum> spectrums) {
		this.spectrums = spectrums;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parameter other = (Parameter) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
