package com.kbdisplay.ls1710.repository;

import org.joda.time.DateTime;
import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.DateOfMeasurement;

/**
 * интерфейс даты измерений для обращения к БД за данными.
 *
 * @author Gavrik
 *
 */
public interface DateOfMeasurementRepository extends
		CrudRepository<DateOfMeasurement, Long> {

	// @Query("select d from DateOfMeasurement d where d.date = :date")
	/**
	 * поиск даты измерений в БД.
	 *
	 * @param date интересующая дата
	 * @return найденную дату из БД либо null
	 */
	DateOfMeasurement findByDate(/* @Param("date") */DateTime date);

}
