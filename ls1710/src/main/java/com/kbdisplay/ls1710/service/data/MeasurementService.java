package com.kbdisplay.ls1710.service.data;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.Measurement;

public interface MeasurementService {

	public List<Measurement> findAll();

	public Page<Measurement> findAllByPage(Pageable pageable);

	public Measurement findById(Long id);

	// public List<Measurements> findAllWithDetail();
	public List<Measurement> findByEquipment(Equipment equipment);

	public Measurement save(Measurement measurement);

	public void delete(Measurement measurement);

	public Long count();

}
