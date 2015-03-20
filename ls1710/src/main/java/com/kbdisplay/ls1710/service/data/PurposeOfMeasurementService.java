package com.kbdisplay.ls1710.service.data;

import java.util.List;

import com.kbdisplay.ls1710.domain.PurposeOfMeasurement;

public interface PurposeOfMeasurementService {

	public List<PurposeOfMeasurement> findAll();

	public PurposeOfMeasurement findById(Long id);

	public PurposeOfMeasurement findByName(String purpose);

	public PurposeOfMeasurement save(PurposeOfMeasurement purposeOfMeasurement);

}
