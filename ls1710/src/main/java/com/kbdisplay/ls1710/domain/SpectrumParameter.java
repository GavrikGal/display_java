package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "spectrums_parameters")
public class SpectrumParameter implements Serializable {

	private static final long		serialVersionUID	= -8217254806166488713L;
	private Long					idSpectrumParameters;
	private Measurand				measurand;
	private TypeOfSpectrum					typeOfSpectrum;
	private ScreenResolution		screenResolution;
//	private PurposeOfMeasurement	purposeOfMeasurement;

//	@ManyToOne
//	@JoinColumn(name = "Purpose")
//	public PurposeOfMeasurement getPurposeOfMeasurement() {
//		return purposeOfMeasurement;
//	}
//
//	public void setPurposeOfMeasurement(PurposeOfMeasurement purposeOfMeasurement) {
//		this.purposeOfMeasurement = purposeOfMeasurement;
//	}

	private Set<Spectrum>	spectrums	= new HashSet<Spectrum>();

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Spectrum_parameters")
	public Long getIdSpectrumParameters() {
		return idSpectrumParameters;
	}

	public void setIdSpectrumParameters(Long idSpectrumParameters) {
		this.idSpectrumParameters = idSpectrumParameters;
	}

	@ManyToOne
	@JoinColumn(name = "Measurand")
	public Measurand getMeasurand() {
		return measurand;
	}

	public void setMeasurand(Measurand measurand) {
		this.measurand = measurand;
	}

	@ManyToOne
	@JoinColumn(name = "Type")
	public TypeOfSpectrum getTypeOfSpectrum() {
		return typeOfSpectrum;
	}

	public void setTypeOfSpectrum(TypeOfSpectrum type) {
		this.typeOfSpectrum = type;
	}

	@ManyToOne
	@JoinColumn(name = "Resolution")
	public ScreenResolution getScreenResolution() {
		return screenResolution;
	}

	public void setScreenResolution(ScreenResolution resolution) {
		this.screenResolution = resolution;
	}

	@OneToMany(mappedBy = "spectrumParameters", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Spectrum> getSpectrums() {
		return this.spectrums;
	}

	public void setSpectrums(Set<Spectrum> spectrums) {
		this.spectrums = spectrums;
	}

}
