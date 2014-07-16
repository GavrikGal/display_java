package com.kbdisplay.ls1710.repository;

import org.joda.time.DateTime;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.DateOfMeasurement;

public interface DateOfMeasurementRepository extends
		CrudRepository<DateOfMeasurement, Long> {

	// @Query("select d from DateOfMeasurement d where d.date = :date")
	public DateOfMeasurement findByDate(/* @Param("date") */DateTime date);

}
