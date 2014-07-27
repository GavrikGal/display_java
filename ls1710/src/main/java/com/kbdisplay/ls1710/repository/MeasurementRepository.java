package com.kbdisplay.ls1710.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.Measurement;

/**
 * интерфейс проведенных измерений для доступа к данным из БД.
 *
 * @author Gavrik
 *
 */
public interface MeasurementRepository extends
		PagingAndSortingRepository<Measurement, Long> {

	/**
	 * поиск всех измерений для указанного испытуемого изделия.
	 *
	 * @param equipments
	 *            искомое испытуемое изделие
	 * @return список измерений с указанным изделием или null
	 */
	List<Measurement> findByEquipment(Equipment equipments);

}
