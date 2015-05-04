package com.kbdisplay.ls1710.service.data;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;

public interface EquipmentService {

	public List<Equipment> findAll();

	public Equipment findById(Long id);

	public Equipment findBySerialNumberAndModel(String serialNumber, ModelOfEquipment model);

	@PreAuthorize("hasRole('ROLE_USER')")
	public Equipment update(Equipment equipment);

	@PreAuthorize("hasRole('ROLE_USER')")
	public void delete(Equipment equipment);

}
