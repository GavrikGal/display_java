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
 * Объект разрешения экрана из БД.
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "screen_resolution")
public class ScreenResolution implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = 8281839097726999031L;

	/**
	 * ID разрешения экрана.
	 */
	private Long id;

	/**
	 * Разрешение экрана.
	 */
	private String resolution;

	/**
	 * Список параметров спектров, использующие это разрешение.
	 */
	private Set<SpectrumParameter> spectrumsParameters =
			new HashSet<SpectrumParameter>();

	@OneToMany(mappedBy = "screenResolution",
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

	@Column(name = "resolution")
	public String getResolution() {
		return resolution;
	}

	public void setResolution(final String resolution) {
		this.resolution = resolution;
	}

	@Override
	public String toString() {
		return resolution;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result =
				prime * result
						+ ((resolution == null) ? 0 : resolution.hashCode());
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
		ScreenResolution other = (ScreenResolution) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (resolution == null) {
			if (other.resolution != null)
				return false;
		} else if (!resolution.equals(other.resolution))
			return false;
		return true;
	}



}
