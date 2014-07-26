package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <b>Объект типа измеряемого спектра (си или ирп) из БД.</b>
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "types")
public class TypeOfSpectrum implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = -1529433046273888216L;

	/**
	 * ID типа измеряемого спектра. В качестве ID выступает само название типа.
	 */
	private String idType;
	/**
	 * Список параметров спектра, в которых используется данный тип спектра.
	 */
	private Set<SpectrumParameter> spectrumsParameters =
			new HashSet<SpectrumParameter>();

	@OneToMany(mappedBy = "typeOfSpectrum",
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
	@Column(name = "id_Type")
	public final String getIdType() {
		return idType;
	}

	public final void setIdType(final String idType) {
		this.idType = idType;
	}

	@Override
	public final String toString() {
		return idType;
	}

}
