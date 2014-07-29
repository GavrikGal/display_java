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
 * Объект измеренных амплитуд и частот (гармоник) из БД.
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
	public Long getIdHarmonics() {
		return idHarmonics;
	}

	public void setIdHarmonics(final Long idHarmonics) {
		this.idHarmonics = idHarmonics;
	}

	@Column(name = "Frequency")
	public Double getFrequency() {
		return frequency;
	}

	public void setFrequency(final Double frequency) {
		this.frequency = frequency;
	}

	@Column(name = "ReceiverBandwidth")
	public Double getReceiverBandwidth() {
		return receiverBandwidth;
	}

	public void setReceiverBandwidth(final Double receiverBandwidth) {
		this.receiverBandwidth = receiverBandwidth;
	}

	@Column(name = "Amplitude")
	public Double getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(final Double amplitude) {
		this.amplitude = amplitude;
	}

	@Column(name = "Noise")
	public Double getNoise() {
		return noise;
	}

	public void setNoise(final Double noise) {
		this.noise = noise;
	}

	@ManyToOne
	@JoinColumn(name = "Spectrum")
	public Spectrum getSpectrum() {
		return spectrum;
	}

	public void setSpectrum(final Spectrum spectrum) {
		this.spectrum = spectrum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
