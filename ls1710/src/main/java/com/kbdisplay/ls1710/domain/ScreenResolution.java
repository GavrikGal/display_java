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
 * <b>Объект разрешения экрана из БД.</b>
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
	public final Set<SpectrumParameter> getSpectrumsParameters() {
		return this.spectrumsParameters;
	}

	public final void setSpectrumsParameters(
			final Set<SpectrumParameter> spectrumsParameters) {
		this.spectrumsParameters = spectrumsParameters;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Resolution")
	public final Long getIdResolution() {
		return idResolution;
	}

	public final void setIdResolution(final Long idResolution) {
		this.idResolution = idResolution;
	}

	@Column(name = "Resolution")
	public final String getResolution() {
		return screenResolution;
	}

	public final void setResolution(final String resolution) {
		this.screenResolution = resolution;
	}

	@Override
	public final String toString() {
		return screenResolution;
	}

}
