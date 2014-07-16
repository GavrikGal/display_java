package com.kbdisplay.ls1710.service.data;

import java.util.List;

import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.Model;

public interface EquipmentService {

	public List<Equipment> findAll();

	public Equipment findById(Long id);

	public Equipment findBySerialNumberAndModel(String serialNumber, Model model);

	public Equipment save(Equipment equipment);
	
	public void delete(Equipment equipment);

}
