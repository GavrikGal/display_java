package com.kbdisplay.ls1710.service.dataJournal.normGenerator;

import java.util.List;

import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.service.dataJournal.NormGenerator;

public class Special2005NormGenerator implements NormGenerator {

	private List<Parameter> parmeters;

	public Special2005NormGenerator(List<Parameter> parameters) {
		this.parmeters = parameters;
	}

	@Override
	public Double getNorm(Double frequency) {
		// TODO Автоматически созданная заглушка метода
		return 41.0;
	}

}
