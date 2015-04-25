package com.kbdisplay.ls1710.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.domain.Spectrum;

/**
 * интерфейс измеренного спектра для доступа к данным из БД.
 *
 * @author Gavrik
 *
 */
public interface SpectrumRepository extends CrudRepository<Spectrum, Long> {

	/**
	 * поиск спектра по измерению и параметрам спектра.
	 *
	 * @param measurement измерение
	 * @param parameter параметры спектра
	 * @return найденный спектр или null
	 */
	List <Spectrum> findByMeasurementAndParameters(
			Measurement measurement, List<Parameter> parameters);

}
