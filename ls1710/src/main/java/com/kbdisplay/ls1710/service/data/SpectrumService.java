package com.kbdisplay.ls1710.service.data;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.domain.Spectrum;

public interface SpectrumService {

	public List<Spectrum> findAll();

	public Spectrum findById(Long id);

	public List <Spectrum> findByMeasurementAndParameters(Measurement measurement,
			List<Parameter> parameters);

	@PreAuthorize("hasRole('ROLE_USER')")
	public Spectrum save(Spectrum spectrum);

}
