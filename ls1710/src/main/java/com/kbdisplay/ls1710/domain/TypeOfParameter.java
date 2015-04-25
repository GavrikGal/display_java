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
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 * типы параметров спектра из БД.
 *
 * @author Gavrik
 *
 */
@Entity
@Table(name = "type_parameter")
public class TypeOfParameter implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * ID типа параметра.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	/**
	 * название типа параметра.
	 */
	@Column(name = "name")
	private String name;

	@ManyToOne
	@JoinColumn(name = "prev_type_id")
	private TypeOfParameter prevType;

	@OneToMany(mappedBy = "prevType", cascade = CascadeType.ALL, orphanRemoval = true)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<TypeOfParameter> nextTypes;

	@OneToMany(mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("name")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Parameter> parameters;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(final List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public TypeOfParameter getPrevType() {
		return prevType;
	}

	public void setPrevType(TypeOfParameter prevType) {
		this.prevType = prevType;
	}

	public List<TypeOfParameter> getNextTypes() {
		return nextTypes;
	}

	public void setNextTypes(List<TypeOfParameter> nextTypes) {
		this.nextTypes = nextTypes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TypeOfParameter other = (TypeOfParameter) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
