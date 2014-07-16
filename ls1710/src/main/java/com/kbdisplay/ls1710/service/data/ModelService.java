package com.kbdisplay.ls1710.service.data;

import java.util.List;

import com.kbdisplay.ls1710.domain.Model;

public interface ModelService {

	public List<Model> findAll();

	public Model findById(Long id);

	public Model findByModelName(String modelName);

	public Model save(Model model);

}
