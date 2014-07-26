package com.kbdisplay.ls1710.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <b>Объект измеренных амплитуд и частот (гармоник) из БД.</b>
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "harmonics")
public class Harmonic implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = -3487239594233702920L;
	/**
	 * ID измеренной гармоники.
	 */
	private Long idHarmonics;
	/**
	 * Частота.
	 */
	private Double frequency;
	/**
	 * Полоса пропускания приемника.
	 */
	private Double receiverBandwidth;
	/**
	 * Амплитуда.
	 */
	private Double amplitude;
	/**
	 * Шум.
	 */
	private Double noise;
	/**
	 * Спект, содержащий данную гармонику.
	 */
	private Spectrum spectrum;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Harmonics")
	public final Long getIdHarmonics() {
		return idHarmonics;
	}

	public final void setIdHarmonics(final Long idHarmonics) {
		this.idHarmonics = idHarmonics;
	}

	@Column(name = "Frequency")
	public final Double getFrequency() {
		return frequency;
	}

	public final void setFrequency(final Double frequency) {
		this.frequency = frequency;
	}

	@Column(name = "ReceiverBandwidth")
	public final Double getReceiverBandwidth() {
		return receiverBandwidth;
	}

	public final void setReceiverBandwidth(final Double receiverBandwidth) {
		this.receiverBandwidth = receiverBandwidth;
	}

	@Column(name = "Amplitude")
	public final Double getAmplitude() {
		return amplitude;
	}

	public final void setAmplitude(final Double amplitude) {
		this.amplitude = amplitude;
	}

	@Column(name = "Noise")
	public final Double getNoise() {
		return noise;
	}

	public final void setNoise(final Double noise) {
		this.noise = noise;
	}

	@ManyToOne
	@JoinColumn(name = "Spectrum")
	public final Spectrum getSpectrum() {
		return spectrum;
	}

	public final void setSpectrum(final Spectrum spectrum) {
		this.spectrum = spectrum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
