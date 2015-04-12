package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.repository.ParameterRepository;
import com.kbdisplay.ls1710.service.data.ParameterService;

@Service("parameterService")
@Repository
@Transactional
public class ParameterServiceImpl implements ParameterService {

	@Autowired
	private ParameterRepository parameterRepository;


	@Override
	@Transactional(readOnly = true)
	public List<Parameter> findAll() {
		return Lists.newArrayList(parameterRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Parameter findByName(final String name) {
		return parameterRepository.findByName(name);
	}

	@Override
	public Parameter save(final Parameter parameter) {
		return parameterRepository.save(parameter);
	}

}
