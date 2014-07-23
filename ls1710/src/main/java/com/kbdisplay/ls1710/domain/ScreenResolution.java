package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "screen_resolutions")
public class ScreenResolution implements Serializable {

	private static final long			serialVersionUID	= 8281839097726999031L;
	private Long						idResolution;
	private String						screenResolution;
	private Set<SpectrumParameter>	spectrumsParameters	= new HashSet<SpectrumParameter>();

	@OneToMany(mappedBy = "screenResolution", cascade = CascadeType.ALL, orphanRemoval = true)
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Resolution")
	public Long getIdResolution() {
		return idResolution;
	}

	public void setIdResolution(Long idResolution) {
		this.idResolution = idResolution;
	}

	@Column(name = "Resolution")
	public String getResolution() {
		return screenResolution;
	}

	public void setResolution(String resolution) {
		this.screenResolution = resolution;
	}

	public String toString() {
		return screenResolution;
	}

}
