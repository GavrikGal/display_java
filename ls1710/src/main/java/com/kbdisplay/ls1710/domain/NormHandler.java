package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "norm_handler")
public class NormHandler implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1163571011826122588L;

	/**
	 * ID норм.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	/**
	 * имя обработчика.
	 */
	@Column(name = "name")
	private String name;

	@Column(name = "handler_name")
	private String handlerName;

	@OneToMany(mappedBy = "normHandler", cascade = CascadeType.ALL,
			orphanRemoval = true)
	private Set<Norm> norms;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHandlerName() {
		return handlerName;
	}

	public void setHandlerName(String handlerName) {
		this.handlerName = handlerName;
	}

	public Set<Norm> getNorms() {
		return norms;
	}

	public void setNorms(Set<Norm> norms) {
		this.norms = norms;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
