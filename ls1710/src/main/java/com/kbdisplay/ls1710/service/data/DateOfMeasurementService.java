package com.kbdisplay.ls1710.service.data;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.security.access.prepost.PreAuthorize;

import com.kbdisplay.ls1710.domain.DateOfMeasurement;

public interface DateOfMeasurementService {

	public List<DateOfMeasurement> findAll();

	public DateOfMeasurement findById(Long id);

	public DateOfMeasurement findByDate(DateTime date);

	@PreAuthorize("hasRole('ROLE_USER')")
	public DateOfMeasurement update(DateOfMeasurement dateOfMeasurement);

}
