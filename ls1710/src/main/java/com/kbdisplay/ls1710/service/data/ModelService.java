package com.kbdisplay.ls1710.service.data;

import java.util.List;

import com.kbdisplay.ls1710.domain.ModelOfEquipment;

public interface ModelService {

	public List<ModelOfEquipment> findAll();

	public ModelOfEquipment findById(Long id);

	public ModelOfEquipment findByName(String modelName);

	public ModelOfEquipment save(ModelOfEquipment model);

}
