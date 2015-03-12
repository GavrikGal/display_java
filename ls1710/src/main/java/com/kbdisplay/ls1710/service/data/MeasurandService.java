package com.kbdisplay.ls1710.service.data;

import java.util.List;

import com.kbdisplay.ls1710.domain.Measurand;

public interface MeasurandService {

	public List<Measurand> findAll();

	public Measurand findByName(String name);

	public Measurand save(Measurand measurand);

}
