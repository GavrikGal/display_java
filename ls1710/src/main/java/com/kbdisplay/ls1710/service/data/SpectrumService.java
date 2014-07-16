package com.kbdisplay.ls1710.service.data;

import java.util.List;

import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.Spectrum;
import com.kbdisplay.ls1710.domain.SpectrumParameter;

public interface SpectrumService {

	public List<Spectrum> findAll();

	public Spectrum findById(Long id);

	public Spectrum findByMeasurementAndSpectrumParameters(Measurement measurement,
			SpectrumParameter spectrumParameter);

	public Spectrum save(Spectrum spectrum);

}
