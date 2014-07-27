package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.TypeOfSpectrum;

/**
 * интерфейс типов спектра (си или ирп) для доступа к данным из БД.
 *
 * @author Gavrik
 *
 */
public interface TypeOfSpectrumRepository extends
		CrudRepository<TypeOfSpectrum, String> {

}
