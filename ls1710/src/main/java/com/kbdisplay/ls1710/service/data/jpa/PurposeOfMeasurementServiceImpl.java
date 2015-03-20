package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.PurposeOfMeasurement;
import com.kbdisplay.ls1710.repository.PurposeOfMeasurementRepository;
import com.kbdisplay.ls1710.service.data.PurposeOfMeasurementService;

@Service("purposeOfMeasurementService")
@Repository
@Transactional
public class PurposeOfMeasurementServiceImpl implements
		PurposeOfMeasurementService {

	@Autowired
	private PurposeOfMeasurementRepository purposeOfMeasurementRepository;


	@Override
	@Transactional(readOnly = true)
	public List<PurposeOfMeasurement> findAll() {
		return Lists.newArrayList(purposeOfMeasurementRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public PurposeOfMeasurement findById(Long id) {
		return purposeOfMeasurementRepository.findOne(id);
	}

	@Override
	public PurposeOfMeasurement save(PurposeOfMeasurement purposeOfMeasurement) {
		return purposeOfMeasurementRepository.save(purposeOfMeasurement);
	}

	@Override
	@Transactional(readOnly = true)
	public PurposeOfMeasurement findByName(String name) {
		return purposeOfMeasurementRepository.findByName(name);
	}

}
