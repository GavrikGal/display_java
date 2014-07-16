package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbdisplay.ls1710.domain.Measurand;
import com.kbdisplay.ls1710.repository.MeasurandRepository;
import com.kbdisplay.ls1710.service.data.MeasurandService;
import com.google.common.collect.Lists;

@Service("measurandService")
@Repository
@Transactional
public class MeasurandServiceImpl implements MeasurandService {

	@Autowired
	private MeasurandRepository measurandRepository;

	@Transactional(readOnly = true)
	public List<Measurand> findAll() {
		return Lists.newArrayList(measurandRepository.findAll());
	}

	@Transactional(readOnly = true)
	public Measurand findById(String id) {
		return measurandRepository.findOne(id);
	}

	@Override
	public Measurand save(Measurand measurand) {
		return measurandRepository.save(measurand);
	}

}
