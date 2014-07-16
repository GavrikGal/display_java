package com.kbdisplay.ls1710.service.data.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbdisplay.ls1710.domain.TypeOfSpectrum;
import com.kbdisplay.ls1710.repository.TypeOfSpectrumRepository;
import com.kbdisplay.ls1710.service.data.TypeOfSpectrumService;
import com.google.common.collect.Lists;

@Service("typesService")
@Repository
@Transactional
public class TypeOfSpectrumServiceImpl implements TypeOfSpectrumService {

	@Autowired
	private TypeOfSpectrumRepository	typesOfSpectrumRepository;

	@Transactional(readOnly = true)
	public List<TypeOfSpectrum> findAll() {
		return Lists.newArrayList(typesOfSpectrumRepository.findAll());
	}

	@Transactional(readOnly = true)
	public TypeOfSpectrum findById(String id) {
		return typesOfSpectrumRepository.findOne(id);
	}

	public TypeOfSpectrum save(TypeOfSpectrum typeOfSpectrum) {
		return typesOfSpectrumRepository.save(typeOfSpectrum);
	}

}
