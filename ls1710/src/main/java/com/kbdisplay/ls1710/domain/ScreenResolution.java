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
@Table(name = "screen_resolutions")
public class ScreenResolution implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = 8281839097726999031L;
	/**
	 * ID разрешения экрана.
	 */
	private Long idResolution;
	/**
	 * Разрешение экрана.
	 */
	private String screenResolution;
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
	@Column(name = "id_Resolution")
	public Long getIdResolution() {
		return idResolution;
	}

	public void setIdResolution(final Long idResolution) {
		this.idResolution = idResolution;
	}

	@Column(name = "Resolution")
	public String getResolution() {
		return screenResolution;
	}

	public void setResolution(final String resolution) {
		this.screenResolution = resolution;
	}

	@Override
	public String toString() {
		return screenResolution;
	}

}
