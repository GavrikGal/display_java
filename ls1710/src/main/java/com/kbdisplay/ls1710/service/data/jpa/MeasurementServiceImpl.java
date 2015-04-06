package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.PurposeOfMeasurement;
import com.kbdisplay.ls1710.repository.EquipmentRepository;
import com.kbdisplay.ls1710.repository.MeasurementRepository;
import com.kbdisplay.ls1710.service.data.MeasurementService;

@Service("measurementService")
@Repository
@Transactional
public class MeasurementServiceImpl implements MeasurementService {

	@Autowired
	private MeasurementRepository measurementRepository;

	@Autowired
	private EquipmentRepository equipmentRepository;


	@Override
	@Transactional(readOnly = true)
	public List<Measurement> findAll() {
		return Lists.newArrayList(measurementRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Measurement findById(Long id) {
		return measurementRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Measurement> findByEquipment(Equipment equipment) {
		return Lists.newArrayList(measurementRepository
				.findByEquipment(equipment));
	}

	@Override
	public Measurement save(Measurement measurement) {
		return measurementRepository.save(measurement);
	}

	@Override
	@Modifying
	@Transactional(readOnly = false)
	public void delete(Measurement measurement) {
		measurementRepository.delete(measurement.getId());
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Measurement> findAllByPage(Pageable pageable) {
		return measurementRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return measurementRepository.count();
	}

	@Override
	public List<Measurement> findByEquipmentAndPurpose(
			final Equipment equipment, final PurposeOfMeasurement purpose) {
		return measurementRepository.findByEquipmentAndPurpose(equipment,
				purpose);
	}

}
