package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.List;

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

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "standard")
public class Standard implements Serializable {

	/**
	 * Серийный номер модели.
	 */
	private static final long serialVersionUID = -6342828537854967090L;

	/**
	 * ID норм.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "standard_type_id")
	private StandardType standardType;

	@ManyToOne
	@JoinColumn(name = "standard_number_id")
	private StandardNumber standardNumber;

	@Column(name = "year")
	private Long year;

	@Column(name = "description")
	private String description;

	@OneToMany(mappedBy = "standard", cascade = CascadeType.MERGE, orphanRemoval = false	)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Norm> norms;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StandardType getStandardType() {
		return standardType;
	}

	public void setStandardType(StandardType standardType) {
		this.standardType = standardType;
	}

	public StandardNumber getStandardNumber() {
		return standardNumber;
	}

	public void setStandardNumber(StandardNumber standardNumber) {
		this.standardNumber = standardNumber;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Norm> getNorms() {
		return norms;
	}

	public void setNorms(List<Norm> norms) {
		this.norms = norms;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFullName(){
		return (standardType.getName() + " " + standardNumber.getName() + "-" + year.toString());
	}

	@Override
	public String toString() {
		return standardType.getName() + " " + standardNumber.getName() + "-" + year;
	}
}
