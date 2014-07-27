package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;

import com.kbdisplay.ls1710.domain.Measurand;
import com.kbdisplay.ls1710.domain.ScreenResolution;
import com.kbdisplay.ls1710.domain.SpectrumParameter;
import com.kbdisplay.ls1710.domain.TypeOfSpectrum;

/**
 * интерфейс параметров спектра для доступа к данным из БД.
 *
 * @author Gavrik
 *
 */
public interface SpectrumParameterRepository extends
		CrudRepository<SpectrumParameter, Long> {

	/**
	 * поиск параметров спектра в БД по измеряемой величине (е, u, i), типу
	 * измеряемого спектра (си или ирп), и разрешению экрана (800х600).
	 *
	 * @param measurand измеряемая величина (e, u, i)
	 * @param typeOfSpectrum тип измеряемого спектра (си или ирп)
	 * @param screenResolution разрешение экрана (800х600, 1024х768 и т.д.)
	 * @return объект найденных параметров спектра или null
	 */
	SpectrumParameter findByMeasurandAndTypeOfSpectrumAndScreenResolution(
			Measurand measurand, TypeOfSpectrum typeOfSpectrum,
			ScreenResolution screenResolution);

}
