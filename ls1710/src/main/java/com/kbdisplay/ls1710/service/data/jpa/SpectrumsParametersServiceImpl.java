package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbdisplay.ls1710.domain.Measurand;
//import com.gmail.gal.gavrik.display.domain.PurposeOfMeasurement;
import com.kbdisplay.ls1710.domain.ScreenResolution;
import com.kbdisplay.ls1710.domain.SpectrumParameter;
import com.kbdisplay.ls1710.domain.TypeOfSpectrum;
import com.kbdisplay.ls1710.repository.SpectrumParameterRepository;
import com.kbdisplay.ls1710.service.data.SpectrumParameterService;
import com.google.common.collect.Lists;

@Service("spectrumParameterService")
@Repository
@Transactional
public class SpectrumsParametersServiceImpl implements SpectrumParameterService {

	@Autowired
	private SpectrumParameterRepository	spectrumParameterRepository;

	@Transactional(readOnly = true)
	public List<SpectrumParameter> findAll() {
		return Lists.newArrayList(spectrumParameterRepository.findAll());
	}

	@Transactional(readOnly = true)
	public SpectrumParameter findById(Long id) {
		return spectrumParameterRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	public SpectrumParameter findWithDetail(Measurand measurand, TypeOfSpectrum typeOfSpectrum,
			ScreenResolution screenResolution/*
												 * , PurposeOfMeasurement
												 * purposeOfMeasurement
												 */) {
		return spectrumParameterRepository.findByMeasurandAndTypeOfSpectrumAndScreenResolution(measurand,
				typeOfSpectrum, screenResolution/* , purposeOfMeasurement */);
	}

	public SpectrumParameter save(SpectrumParameter spectrumParameter) {
		SpectrumParameter checkingParameter = spectrumParameterRepository
				.findByMeasurandAndTypeOfSpectrumAndScreenResolution(spectrumParameter.getMeasurand(),
						spectrumParameter.getTypeOfSpectrum(), spectrumParameter.getScreenResolution());
		if (checkingParameter == null) {
			return spectrumParameterRepository.save(spectrumParameter);
		} else {
			return checkingParameter;
		}
	}

}
