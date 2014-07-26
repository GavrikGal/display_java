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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <b>Объект параметров спектра из БД.</b>
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "spectrums_parameters")
public class SpectrumParameter implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = -8217254806166488713L;

	/**
	 * ID параметров спектра.
	 */
	private Long idSpectrumParameters;
	/**
	 * измеряемая физическая величина (Е, I, U и т.д.).
	 */
	private Measurand measurand;
	/**
	 * Тип спектра (си, ирп).
	 */
	private TypeOfSpectrum typeOfSpectrum;
	/**
	 * Разрешение экрана (800х600, 1024х768 и т.д.).
	 */
	private ScreenResolution screenResolution;
	/**
	 * Список спектров, пользующихся этими параметрами.
	 */
	private Set<Spectrum> spectrums = new HashSet<Spectrum>();
	// private PurposeOfMeasurement purposeOfMeasurement;

	// @ManyToOne
	// @JoinColumn(name = "Purpose")
	// public PurposeOfMeasurement getPurposeOfMeasurement() {
	// return purposeOfMeasurement;
	// }
	//
	// public void setPurposeOfMeasurement(PurposeOfMeasurement
	// purposeOfMeasurement) {
	// this.purposeOfMeasurement = purposeOfMeasurement;
	// }



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Spectrum_parameters")
	public final Long getIdSpectrumParameters() {
		return idSpectrumParameters;
	}

	public final void setIdSpectrumParameters(final Long idSpectrumParameters) {
		this.idSpectrumParameters = idSpectrumParameters;
	}

	@ManyToOne
	@JoinColumn(name = "Measurand")
	public final Measurand getMeasurand() {
		return measurand;
	}

	public final void setMeasurand(final Measurand measurand) {
		this.measurand = measurand;
	}

	@ManyToOne
	@JoinColumn(name = "Type")
	public final TypeOfSpectrum getTypeOfSpectrum() {
		return typeOfSpectrum;
	}

	public final void setTypeOfSpectrum(final TypeOfSpectrum type) {
		this.typeOfSpectrum = type;
	}

	@ManyToOne
	@JoinColumn(name = "Resolution")
	public final ScreenResolution getScreenResolution() {
		return screenResolution;
	}

	public final void setScreenResolution(final ScreenResolution resolution) {
		this.screenResolution = resolution;
	}

	@OneToMany(mappedBy = "spectrumParameters",
			cascade = CascadeType.ALL, orphanRemoval = true)
	public final Set<Spectrum> getSpectrums() {
		return this.spectrums;
	}

	public final void setSpectrums(final Set<Spectrum> spectrums) {
		this.spectrums = spectrums;
	}

}
