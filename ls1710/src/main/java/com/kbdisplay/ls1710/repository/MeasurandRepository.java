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

}
