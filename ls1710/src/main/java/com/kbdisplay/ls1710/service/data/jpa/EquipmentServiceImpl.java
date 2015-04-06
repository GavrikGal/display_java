package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.repository.EquipmentRepository;
import com.kbdisplay.ls1710.service.data.EquipmentService;

@Service("equipmentService")
@Repository
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentRepository equipmentRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Equipment> findAll() {
		return Lists.newArrayList(equipmentRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Equipment findById(Long id) {
		return equipmentRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Equipment findBySerialNumberAndModel(String serialNumber, ModelOfEquipment model) {
		return equipmentRepository.findBySerialNumberAndModel(serialNumber,
				model);
	}

	@Override
	public Equipment update(Equipment equipment) {
		Equipment checkingEquipment = equipmentRepository
				.findBySerialNumberAndModel(equipment.getSerialNumber(),
						equipment.getModel());
		if (checkingEquipment == null) {
			return equipmentRepository.save(equipment);
		} else {
			return checkingEquipment;
		}
	}

	@Override
	public void delete(Equipment equipment) {
		equipmentRepository.delete(equipment.getId());
	}

}
