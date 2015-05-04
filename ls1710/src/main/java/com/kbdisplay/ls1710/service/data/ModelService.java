package com.kbdisplay.ls1710.service.data;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kbdisplay.ls1710.domain.ModelOfEquipment;

public interface ModelService {

	public List<ModelOfEquipment> findAll();

	public ModelOfEquipment findById(Long id);

	public ModelOfEquipment findByName(String modelName);

	@PreAuthorize("hasRole('ROLE_USER')")
	public ModelOfEquipment save(ModelOfEquipment model);

}
