package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.repository.EquipmentRepository;
import com.kbdisplay.ls1710.repository.MeasurementRepository;
import com.kbdisplay.ls1710.service.data.MeasurementService;
import com.google.common.collect.Lists;

@Service("measurementService")
@Repository
@Transactional
public class MeasurementServiceImpl implements MeasurementService {

	@Autowired
	private MeasurementRepository measurementRepository;

	@Autowired
	private EquipmentRepository equipmentRepository;

	@Transactional(readOnly = true)
	public List<Measurement> findAll() {
		return Lists.newArrayList(measurementRepository.findAll());
	}

	@Transactional(readOnly = true)
	public Measurement findById(Long id) {
		return measurementRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public List<Measurement> findByEquipment(Equipment equipment) {
		return Lists.newArrayList(measurementRepository
				.findByEquipment(equipment));
	}

	public Measurement save(Measurement measurement) {
		return measurementRepository.save(measurement);
	}

	public void delete(Measurement measurement) {
		Equipment delitingEquipment = measurement.getEquipment();
		if (delitingEquipment.getMeasurements().size() > 1) {
			measurementRepository.delete(measurement);
		} else {
			equipmentRepository.delete(delitingEquipment);
		}
	}

	@Transactional(readOnly = true)
	public Page<Measurement> findAllByPage(Pageable pageable) {
		return measurementRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Long count() {
		return measurementRepository.count();
	}

}
