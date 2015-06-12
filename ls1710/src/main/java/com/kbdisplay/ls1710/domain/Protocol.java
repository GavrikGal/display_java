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
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name = "protocol_of_measurement")
public class Protocol implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -425610875880042446L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "number")
	private Long number;

	@Column(name = "postfix")
	private String postfix;

	@Column(name = "date")
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(iso = ISO.DATE)
	private DateTime date;

	@ManyToMany
	@JoinTable(name = "protocol_measurement", joinColumns = @JoinColumn(name = "protocol_id"), inverseJoinColumns = @JoinColumn(name = "measurement_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Measurement> measurements;

	@ManyToMany
	@JoinTable(name = "protocol_harmonic", joinColumns = @JoinColumn(name = "protocol_id"), inverseJoinColumns = @JoinColumn(name = "harmonic_id"))
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Harmonic> harmonics;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}



	public String getPostfix() {
		return postfix;
	}

	public void setPostfix(String postfix) {
		this.postfix = postfix;
	}

	public DateTime getDate() {
		return date;
	}

	public void setDate(DateTime date) {
		this.date = date;
	}

	public List<Measurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}

	public List<Harmonic> getHarmonics() {
		return harmonics;
	}

	public void setHarmonics(List<Harmonic> harmonics) {
		this.harmonics = harmonics;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
