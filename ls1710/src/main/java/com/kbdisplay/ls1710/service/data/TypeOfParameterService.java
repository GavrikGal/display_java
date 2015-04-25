package com.kbdisplay.ls1710.service.data;

import java.util.List;

import com.kbdisplay.ls1710.domain.TypeOfParameter;

public interface TypeOfParameterService {

	public List<TypeOfParameter> findAll();

	public TypeOfParameter findByName(String name);

	public List<TypeOfParameter> findByPrevTypeId(Long id);

	public TypeOfParameter save(TypeOfParameter type);

}
