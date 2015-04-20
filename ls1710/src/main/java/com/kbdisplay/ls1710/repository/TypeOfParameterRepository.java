package com.kbdisplay.ls1710.repository;

import java.util.List;

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

	TypeOfParameter findByName(String name);

	List<TypeOfParameter> findByPrevTypeId(Long id);
}
