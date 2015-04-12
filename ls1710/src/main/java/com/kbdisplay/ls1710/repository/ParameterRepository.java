package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.Parameter;

/**
 * интерфейс параметров спектра для доступа к данным из БД.
 *
 * @author Gavrik
 */
public interface ParameterRepository extends CrudRepository<Parameter, Long> {

	/**
	 * поиск параметра спектра в БД по названию параметра.
	 *
	 * @param name
	 *            название параметра (типа пи/пси/типовые/1024х768)
	 * @return объект параметра спектра или null
	 */
	Parameter findByName(String name);

}
