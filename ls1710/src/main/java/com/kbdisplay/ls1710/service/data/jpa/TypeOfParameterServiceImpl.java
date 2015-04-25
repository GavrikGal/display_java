package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.TypeOfParameter;
import com.kbdisplay.ls1710.repository.TypeOfParameterRepository;
import com.kbdisplay.ls1710.service.data.TypeOfParameterService;

@Service("typeOfParameterService")
@Repository
@Transactional
public class TypeOfParameterServiceImpl implements TypeOfParameterService {

	@Autowired
	private TypeOfParameterRepository typeOfParameterRepository;

	@Override
	@Transactional(readOnly = true)
	public List<TypeOfParameter> findAll() {
		return Lists.newArrayList(typeOfParameterRepository.findAll());
	}

	@Override
	public TypeOfParameter findByName(String name) {
		return typeOfParameterRepository.findByName(name);
	}

	@Override
	public List<TypeOfParameter> findByPrevTypeId(Long id) {
		return typeOfParameterRepository.findByPrevTypeId(id);
	}

	@Override
	public TypeOfParameter save(TypeOfParameter type) {
		return typeOfParameterRepository.save(type);
	}



}
