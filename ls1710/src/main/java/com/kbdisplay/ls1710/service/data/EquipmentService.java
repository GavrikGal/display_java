package com.kbdisplay.ls1710.service.data;

import java.util.List;

import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;

public interface EquipmentService {

	public List<Equipment> findAll();

	public Equipment findById(Long id);

	public Equipment findBySerialNumberAndModel(String serialNumber, ModelOfEquipment model);

	public Equipment save(Equipment equipment);
	
	public void delete(Equipment equipment);

}
