package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.TypeOfParameter;

/**
 * интерфейс типа параметра спектра для доступа к данным из БД.
 *
 * @author Gavrik
 *
 */
public interface TypeOfParameterRepository extends
		CrudRepository<TypeOfParameter, Long> {

}
