package com.kbdisplay.ls1710.service.dataJournal.normGenerator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.Norm;
import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.service.dataJournal.NormGenerator;

@Component("special2005NormGenerator")
public class Special2005NormGenerator implements NormGenerator {

	private Norm documentNorm;
	private List<Parameter> parmeters;


	public Special2005NormGenerator() {
	}

	public Special2005NormGenerator(List<Parameter> parameters) {
		this.parmeters = parameters;
	}

	@Override
	public Double getNorm(Double frequency) {
		// TODO Автоматически созданная заглушка метода
		return 41.0;
	}

	@Override
	public Norm getDocumentNorm() {
		return documentNorm;
	}

	@Override
	public void setDocumentNorm(Norm documentNorm) {
		this.documentNorm = documentNorm;
		this.parmeters = documentNorm.getParameters();
	}

}
