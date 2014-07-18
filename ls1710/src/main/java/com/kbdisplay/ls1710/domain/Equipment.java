package com.kbdisplay.ls1710.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "equipments")
public class Equipment implements Serializable {

	private static final long	serialVersionUID	= 7277923833960735466L;

	private Long				idEquipment;
	private ModelOfEquipment				model;
	private String				serialNumber;
	private Set<Measurement>	measurements		= new HashSet<Measurement>();

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Equipment")
	public Long getIdEquipment() {
		return idEquipment;
	}

	public void setIdEquipment(Long idEquipment) {
		this.idEquipment = idEquipment;
	}

	@ManyToOne
	@JoinColumn(name = "Model")
	public ModelOfEquipment getModel() {
		return model;
	}

	public void setModel(ModelOfEquipment model) {
		this.model = model;
	}

	@Column(name = "Serial_number")
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL, orphanRemoval = true)
	public Set<Measurement> getMeasurements() {
		return this.measurements;
	}

	public void setMeasurements(Set<Measurement> measurements) {
		this.measurements = measurements;
	}

}
