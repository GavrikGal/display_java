package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.ModelType;
import com.kbdisplay.ls1710.repository.ModelTypeRepository;
import com.kbdisplay.ls1710.service.data.ModelTypeService;

@Service("modelTypeService")
@Repository
@Transactional
public class ModelTypeServiceImpl implements ModelTypeService {

	@Autowired
	private ModelTypeRepository modelTypeRepository;

	@Override
	public List<ModelType> findAll() {
		return Lists.newArrayList(modelTypeRepository.findAll());
	}

	@Override
	public ModelType findById(Long id) {
		return modelTypeRepository.findOne(id);
	}

	@Override
	public ModelType findByName(String name) {
		return modelTypeRepository.findByName(name);
	}

}
