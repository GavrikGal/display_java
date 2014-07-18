package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.ModelOfEquipment;

public interface ModelRepository extends CrudRepository<ModelOfEquipment, Long> {
	
	public ModelOfEquipment findByModelName (String modelName);

}
