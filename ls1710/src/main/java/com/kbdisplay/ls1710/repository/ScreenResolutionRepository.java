package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.ScreenResolution;

/**
 * интерфейс разрешений экрана для доступа к данным из БД.
 *
 * @author Gavrik
 *
 */
public interface ScreenResolutionRepository extends
		CrudRepository<ScreenResolution, Long> {

	/**
	 * поиск разрешения экрана в БД по названию разрешения.
	 *
	 * @param screenResolution
	 *            название разрешения (типа 800х600)
	 * @return объект разрешения экрана или null
	 */
	ScreenResolution findByScreenResolution(String screenResolution);

}
