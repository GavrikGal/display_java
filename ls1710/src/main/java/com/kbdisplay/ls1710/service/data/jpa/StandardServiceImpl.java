package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.Standard;
import com.kbdisplay.ls1710.repository.StandardRepository;
import com.kbdisplay.ls1710.service.data.StandardService;

@Service("standardService")
@Repository
@Transactional
public class StandardServiceImpl implements StandardService {

	@Autowired
	private StandardRepository standardRepository;

	@Override
	public List<Standard> findAll() {
		return Lists.newArrayList(standardRepository.findAll());
	}

	@Override
	public Standard findById(Long id) {
		return standardRepository.findOne(id);
	}

	@Override
	public Standard save(Standard standard) {
		return standardRepository.save(standard);
	}

}
