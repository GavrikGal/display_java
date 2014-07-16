package com.kbdisplay.ls1710.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.Measurement;

public interface MeasurementRepository extends PagingAndSortingRepository<Measurement, Long> {

	// @Query("select distinct m from Measurements m left join fetch m.users u join fetch m.equipment e join fetch m.spectrums s join fetch e.model mo join fetch s.harmonics h join fetch s.date d")
	// @Query("select distinct m from Measurements m")
	// public List<Measurements> findAllWithDetail();

	// @Query("select d from DateOfMeasurement d where d.date = :date")
	public List<Measurement> findByEquipment(Equipment equipments);
	
	//public Iterable<Measurements> findAllOrderByIdMeasurements();

}
