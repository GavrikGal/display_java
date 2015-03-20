package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.PurposeOfMeasurement;

/**
 * интерфейс цели измерения/испытания для доступа к данным из БД.
 *
 * @author Gavrik
 *
 */
public interface PurposeOfMeasurementRepository extends
		CrudRepository<PurposeOfMeasurement, Long> {

	/**
	 * поиск цели измерений в БД по названию цели.
	 *
	 * @param name
	 *            название цели (типа пи/пси/типовые)
	 * @return объект цели измерений или null
	 */
	PurposeOfMeasurement findByName(String name);

}
