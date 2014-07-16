package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbdisplay.ls1710.domain.Model;
import com.kbdisplay.ls1710.repository.ModelRepository;
import com.kbdisplay.ls1710.service.data.ModelService;
import com.google.common.collect.Lists;

@Service("modelService")
@Repository
@Transactional
public class ModelServiceImpl implements ModelService {

	@Autowired
	private ModelRepository modelRepository;

	@Transactional(readOnly = true)
	public List<Model> findAll() {
		return Lists.newArrayList(modelRepository.findAll());
	}

	@Transactional(readOnly = true)
	public Model findById(Long id) {
		return modelRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public Model findByModelName(String modelName) {
		return modelRepository.findByModelName(modelName);
	}

	public Model save(Model model) {

		return modelRepository.save(model);
	}

}
