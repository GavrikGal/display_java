package com.kbdisplay.ls1710.service.data;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.PurposeOfMeasurement;

public interface MeasurementService {

	public List<Measurement> findAll();

	public Page<Measurement> findAllByPage(Pageable pageable);

	public Measurement findById(Long id);

	// public List<Measurements> findAllWithDetail();
	public List<Measurement> findByEquipment(Equipment equipment);

	public List<Measurement> findByEquipmentAndPurpose(Equipment equipment,
			PurposeOfMeasurement purpose);

	public Measurement save(Measurement measurement);

	@PreAuthorize(value="hasAuthority('ROLE_USER')")
	public void delete(Measurement measurement);

	public Long count();

}
