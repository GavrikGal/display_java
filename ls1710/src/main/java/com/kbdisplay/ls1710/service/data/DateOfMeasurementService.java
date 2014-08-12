package com.kbdisplay.ls1710.service.data;

import java.util.List;

import org.joda.time.DateTime;

import com.kbdisplay.ls1710.domain.DateOfMeasurement;

public interface DateOfMeasurementService {

	public List<DateOfMeasurement> findAll();

	public DateOfMeasurement findById(Long id);

	public DateOfMeasurement findByDate(DateTime date);

	public DateOfMeasurement update(DateOfMeasurement dateOfMeasurement);

}
