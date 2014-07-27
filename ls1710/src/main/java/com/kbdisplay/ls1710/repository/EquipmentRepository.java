package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;

/**
 * интерфейс испытуемого изделия для доступа к данным из БД.
 *
 * @author Gavrik
 *
 */
public interface EquipmentRepository extends CrudRepository<Equipment, Long> {

	/**
	 * поиск испытуемого изделия по серийному номеру и модели.
	 *
	 * @param serialNumber серийный номер изделия
	 * @param model модель изделия
	 * @return найденное испытуемое изделие либо null
	 */
	Equipment findBySerialNumberAndModel(String serialNumber,
			ModelOfEquipment model);

}
