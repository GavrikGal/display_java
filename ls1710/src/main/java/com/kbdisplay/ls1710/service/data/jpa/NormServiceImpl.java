package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.Norm;
import com.kbdisplay.ls1710.repository.NormRepository;
import com.kbdisplay.ls1710.service.data.NormService;

@Service("normService")
@Repository
@Transactional
public class NormServiceImpl implements NormService {

	@Autowired
	private NormRepository normRepository;

	@Override
	public List<Norm> findAll() {
		return Lists.newArrayList(normRepository.findAll());
	}

	@Override
	public Norm findById(Long id) {
		return normRepository.findOne(id);
	}

	@Override
	public Norm save(Norm norm) {
		return normRepository.save(norm);
	}

}
