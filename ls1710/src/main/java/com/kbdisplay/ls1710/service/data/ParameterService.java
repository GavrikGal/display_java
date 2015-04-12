package com.kbdisplay.ls1710.service.data;

import java.util.List;

import com.kbdisplay.ls1710.domain.Parameter;

public interface ParameterService {

	public List<Parameter> findAll();

	public Parameter findByName(String name);

	public Parameter save(Parameter parameter);
}
