package com.kbdisplay.ls1710.service.dataJournal.normGenerator;

import java.util.List;

import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.Limit;
import com.kbdisplay.ls1710.domain.Norm;
import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.service.dataJournal.NormGenerator;

@Component("linearInterpolationNormGenerator")
public class LinearInterpolationNormGenerator implements NormGenerator {

	private List<Limit> limits;
	private Norm documentNorm;


	public LinearInterpolationNormGenerator() {
	}

	@Override
	public Double getNorm(Double frequency) {
		Double frequencyR = null, frequencyL = null, amplitudeR = null, amplitudeL =
				null;
		Double result = 0.0;
		for (Limit limit : limits) {
			if (frequency == limit.getFrequency()) {
				return limit.getAmplitude();
			}

			if (frequencyR == null) {
				if (frequency < limit.getFrequency()) {
					return limit.getAmplitude();
				}
				frequencyR = limit.getFrequency();
				frequencyL = limit.getFrequency();
				amplitudeL = limit.getAmplitude();
				amplitudeR = limit.getAmplitude();

			}
			if (frequency > frequencyR) {
				frequencyL = frequencyR;
				amplitudeL = amplitudeR;
				frequencyR = limit.getFrequency();
				amplitudeR = limit.getAmplitude();
			} else {
				break;
			}
		}
		if (frequency > frequencyR) {
			return amplitudeR;
		}
		result =
				amplitudeL + (frequency - frequencyL)
						* (amplitudeR - amplitudeL) / (frequencyR - frequencyL);
		return result;
	}

	@Override
	public Norm getDocumentNorm() {
		return documentNorm;
	}

	@Override
	public void setDocumentNormAndParameters(Norm documentNorm, List<Parameter> parameters) {
		this.documentNorm = documentNorm;
		this.limits = this.documentNorm.getLimits();
	}

}
