package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.ModelOfEquipment;

/**
 * интерфейс модели испытуемого изделия для доступа к данным из БД.
 *
 * @author Gavrik
 *
 */
public interface ModelRepository extends
		CrudRepository<ModelOfEquipment, Long> {

	/**
	 * поиск модели изделия в БД по названию изделия.
	 *
	 * @param name
	 *            название изделия
	 * @return найденная модель изделия или null
	 */
	ModelOfEquipment findByName(String name);

}
