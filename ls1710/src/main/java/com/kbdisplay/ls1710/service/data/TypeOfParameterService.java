package com.kbdisplay.ls1710.service.data;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kbdisplay.ls1710.domain.TypeOfParameter;

public interface TypeOfParameterService {

	public List<TypeOfParameter> findAll();

	public TypeOfParameter findByName(String name);

	public List<TypeOfParameter> findByPrevTypeId(Long id);

	@PreAuthorize("hasRole('ROLE_USER')")
	public TypeOfParameter save(TypeOfParameter type);

}
