package com.kbdisplay.ls1710.service.data;

import java.util.List;

import com.kbdisplay.ls1710.domain.ScreenResolution;

public interface ScreenResolutionService {

	public List<ScreenResolution> findAll();

	public ScreenResolution findById(Long id);
	
	public ScreenResolution findByResolution(String screenResolution);

	public ScreenResolution save(ScreenResolution screenResolution);

}
