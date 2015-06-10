package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.ModelType;

public interface ModelTypeRepository extends CrudRepository<ModelType, Long> {

	ModelType findByName(String name);

}
