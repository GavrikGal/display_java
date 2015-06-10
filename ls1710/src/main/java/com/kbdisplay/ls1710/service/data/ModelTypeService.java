package com.kbdisplay.ls1710.service.data;

import java.util.List;

import com.kbdisplay.ls1710.domain.ModelType;

public interface ModelTypeService {

	public List<ModelType> findAll();

	public ModelType findById(Long id);

	public ModelType findByName(String modelTypeName);

}
