package com.kbdisplay.ls1710.service.dataJournal.normGenerator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.Norm;
import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.service.dataJournal.NormGenerator;

@Component("special2005NormGenerator")
public class Special2005NormGenerator implements NormGenerator {

	static final Double Fcadr = 60.0;
	static final Double Kfr = 1.38;

	private Norm documentNorm;
	private List<Parameter> parmeters;


	public Special2005NormGenerator() {
	}

	public Special2005NormGenerator(List<Parameter> parameters) {
		this.parmeters = parameters;
	}

	@Override
	public Double getNorm(Double frequency) {
		Parameter resolutionParameter = null;
		for (Parameter parameter : parmeters) {
			if(parameter.getType().getId() == 3) {
				resolutionParameter = parameter;
			}
		}
		if (resolutionParameter != null) {
			String resolution = resolutionParameter.getName();
			String[] heightAndWidth = resolution.trim().split("x");
			int width = Integer.parseInt(heightAndWidth[0]);
			int height = Integer.parseInt(heightAndWidth[1]);
			Double clockFrequency = (width*height*Fcadr*Kfr/2)/1000000;
			Double norm = 44 - 20*Math.log10(frequency/clockFrequency);

			return norm;
		} else {
		return 41.0;
		}
	}

	@Override
	public Norm getDocumentNorm() {
		return documentNorm;
	}

	@Override
	public void setDocumentNormAndParameters(Norm documentNorm, List<Parameter> parameters) {
		this.documentNorm = documentNorm;
		this.parmeters = parameters;
	}

}
