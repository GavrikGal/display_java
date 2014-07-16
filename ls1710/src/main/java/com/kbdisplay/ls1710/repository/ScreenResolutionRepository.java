package com.kbdisplay.ls1710.repository;

import org.springframework.data.repository.CrudRepository;

import com.kbdisplay.ls1710.domain.ScreenResolution;

public interface ScreenResolutionRepository extends
		CrudRepository<ScreenResolution, Long> {
	
	public ScreenResolution findByResolution(String resolution);

}
