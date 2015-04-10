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
 * Объект измеряемой физической величины (напряженность, напряжение, ток) из
 * БД.
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "measurand")
public class Measurand implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = 4305595715434295082L;

	/**
	 * ID измеряемой физической величины.
	 */
	private Long id;

	/**
	 * название измеряемой величины.
	 */
	private String name;

	/**
	 * Список параметров спектров, в которых используется данная физическая
	 * величина.
	 */
	private Set<SpectrumParameter> spectrumsParameters =
			new HashSet<SpectrumParameter>();

	@OneToMany(mappedBy = "measurand",
			cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<SpectrumParameter> getSpectrumsParameters() {
		return this.spectrumsParameters;
	}

	public void setSpectrumsParameters(
			final Set<SpectrumParameter> spectrumsParameters) {
		this.spectrumsParameters = spectrumsParameters;
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

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Measurand other = (Measurand) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


}
