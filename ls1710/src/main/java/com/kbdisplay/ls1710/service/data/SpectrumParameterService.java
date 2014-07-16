package com.kbdisplay.ls1710.service.data;

import java.util.List;

import com.kbdisplay.ls1710.domain.Measurand;
import com.kbdisplay.ls1710.domain.ScreenResolution;
import com.kbdisplay.ls1710.domain.SpectrumParameter;
import com.kbdisplay.ls1710.domain.TypeOfSpectrum;

public interface SpectrumParameterService {

	public List<SpectrumParameter> findAll();

	public SpectrumParameter findById(Long id);

	public SpectrumParameter findWithDetail(Measurand measurand, TypeOfSpectrum type,
			ScreenResolution screenResolution/*, PurposeOfMeasurement purposeOfMeasurement*/);
	
	

	public SpectrumParameter save(SpectrumParameter spectrumParameter);
}
