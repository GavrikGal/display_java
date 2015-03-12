package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.Measurand;

/**
 * интерфейс измеряемых физических величин для доступа к данным из БД.
 *
 * @author Gavrik
 *
 */
public interface MeasurandRepository extends CrudRepository<Measurand, String> {

	/**
	 * поиск единицы измерений по ее названию в БД по названию изделия.
	 *
	 * @param name
	 *            название единицы измерения
	 * @return найденная единица измерения или null
	 */
	Measurand findByName(String name);
}
