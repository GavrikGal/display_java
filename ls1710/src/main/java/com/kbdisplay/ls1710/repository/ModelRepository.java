package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.Model;

public interface ModelRepository extends CrudRepository<Model, Long> {
	
	public Model findByModelName (String modelName);

}
