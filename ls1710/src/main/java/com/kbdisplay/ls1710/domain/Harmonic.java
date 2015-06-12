package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Объект измеренных амплитуд и частот (гармоник) из БД.
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "harmonic")
public class Harmonic implements Serializable {

	/**
	 * Серийный номер класса.
	 */
	private static final long serialVersionUID = -3487239594233702920L;
	/**
	 * ID измеренной гармоники.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	/**
	 * Частота.
	 */
	@Column(name = "frequency")
	private Double frequency;
	/**
	 * Полоса пропускания приемника.
	 */
	@Column(name = "receiver_bandwidth")
	private Double receiverBandwidth;
	/**
	 * Амплитуда.
	 */
	@Column(name = "amplitude")
	private Double amplitude;

	/**
	 * Шум.
	 */
	@Column(name = "noise")
	private Double noise;

	/**
	 * запас относительно нормы.
	 */
	@Column(name = "reserve")
	private Double reserve;

	/**
	 * Спект, содержащий данную гармонику.
	 */
	@ManyToOne
	@JoinColumn(name = "spectrum_id", updatable = true)
	private Spectrum spectrum;

	@ManyToMany
	@JoinTable(name = "protocol_harmonic", joinColumns = @JoinColumn(name = "harmonic_id"), inverseJoinColumns = @JoinColumn(name = "protocol_id"))
	private List<Protocol> protocols;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Double getFrequency() {
		return frequency;
	}

	public void setFrequency(final Double frequency) {
		this.frequency = frequency;
	}

	public Double getReceiverBandwidth() {
		return receiverBandwidth;
	}

	public void setReceiverBandwidth(final Double receiverBandwidth) {
		this.receiverBandwidth = receiverBandwidth;
	}

	public Double getAmplitude() {
		return amplitude;
	}

	public void setAmplitude(final Double amplitude) {
		this.amplitude = amplitude;
	}

	public Double getNoise() {
		return noise;
	}

	public void setNoise(final Double noise) {
		this.noise = noise;
	}

	public Double getReserve() {
		return reserve;
	}

	public void setReserve(Double reserve) {
		this.reserve = reserve;
	}

	public Spectrum getSpectrum() {
		return spectrum;
	}

	public void setSpectrum(final Spectrum spectrum) {
		this.spectrum = spectrum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Protocol> getProtocols() {
		return protocols;
	}

	public void setProtocols(List<Protocol> protocols) {
		this.protocols = protocols;
	}



}
