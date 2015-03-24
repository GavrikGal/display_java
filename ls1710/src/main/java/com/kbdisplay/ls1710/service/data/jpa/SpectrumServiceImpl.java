package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.Spectrum;
import com.kbdisplay.ls1710.domain.SpectrumParameter;
import com.kbdisplay.ls1710.repository.SpectrumRepository;
import com.kbdisplay.ls1710.service.data.SpectrumService;

@Service("spectrumService")
@Repository
@Transactional
public class SpectrumServiceImpl implements SpectrumService {

	@Autowired
	private SpectrumRepository	spectrumRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Spectrum> findAll() {
		return Lists.newArrayList(spectrumRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Spectrum findById(Long id) {
		return spectrumRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List <Spectrum> findByMeasurementAndParameter(Measurement measurement,
			SpectrumParameter parameter) {
		return spectrumRepository.findByMeasurementAndParameter(measurement,
				parameter);
	}

	@Override
	public Spectrum save(Spectrum spectrum) {
		return spectrumRepository.save(spectrum);
	}

}
