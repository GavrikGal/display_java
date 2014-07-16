package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "measurands")
public class Measurand implements Serializable {

	private static final long			serialVersionUID	= 4305595715434295082L;
	private String						idMeasurands;
	private Set<SpectrumParameter>	spectrumsParameters	= new HashSet<SpectrumParameter>();

	@OneToMany(mappedBy = "measurand", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<SpectrumParameter> getSpectrumsParameters() {
		return this.spectrumsParameters;
	}

	public void setSpectrumsParameters(Set<SpectrumParameter> spectrumsParameters) {
		this.spectrumsParameters = spectrumsParameters;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@Column(name = "id_Measurand")
	public String getIdMeasurands() {
		return idMeasurands;
	}

	public void setIdMeasurands(String idMeasurands) {
		this.idMeasurands = idMeasurands;
	}

	public String toString() {
		return idMeasurands;
	}
}
