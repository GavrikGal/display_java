package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "types")
public class TypeOfSpectrum implements Serializable {

	private static final long			serialVersionUID	= -1529433046273888216L;
	private String						idType;
	private Set<SpectrumParameter>	spectrumsParameters	= new HashSet<SpectrumParameter>();

	@OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
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
	@Column(name = "id_Type")
	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String toString() {
		return idType;
	}

}
