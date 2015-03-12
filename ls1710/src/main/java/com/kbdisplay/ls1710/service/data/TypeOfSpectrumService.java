package com.kbdisplay.ls1710.service.data;

import java.util.List;

import com.kbdisplay.ls1710.domain.TypeOfSpectrum;

public interface TypeOfSpectrumService {

	public List<TypeOfSpectrum> findAll();

	public TypeOfSpectrum findByName(String id);

	public TypeOfSpectrum save(TypeOfSpectrum typeOfSpectrum);

}
