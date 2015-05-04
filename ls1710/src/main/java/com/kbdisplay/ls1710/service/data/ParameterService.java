package com.kbdisplay.ls1710.service.data;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kbdisplay.ls1710.domain.Parameter;

public interface ParameterService {

	public List<Parameter> findAll();

	public Parameter findByName(String name);

	@PreAuthorize("hasRole('ROLE_USER')")
	public Parameter save(Parameter parameter);
}
