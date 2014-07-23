package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;

import com.kbdisplay.ls1710.domain.Measurand;
import com.kbdisplay.ls1710.domain.ScreenResolution;
import com.kbdisplay.ls1710.domain.SpectrumParameter;
import com.kbdisplay.ls1710.domain.TypeOfSpectrum;

public interface SpectrumParameterRepository extends
		CrudRepository<SpectrumParameter, Long> {

	// @Query("select e from Equipments e where e.model = :models and e.serialNumber = :serialNumber")
	// @Query("select s from SpectrumsParameters s where s.measurand = :measurand and s.type = :type and s.resolution = :resolution")//
	// and s.purposeOfMeasurement = :purposeOfMeasurement")
	// public SpectrumsParameters findWithDetail(@Param("measurand") Measurands
	// measurands,
	// @Param("type") Types types,
	// @Param("resolution") ScreenResolutions screenResolutions/*,
	// @Param("purposeOfMeasurement") PurposeOfMeasurement
	// purposeOfMeasurement*/);
	//
	public SpectrumParameter findByMeasurandAndTypeOfSpectrumAndScreenResolution(
			Measurand measurand, TypeOfSpectrum typeOfSpectrum,
			ScreenResolution screenResolution);

}
