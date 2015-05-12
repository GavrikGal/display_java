package com.kbdisplay.ls1710.service.dataJournal.normGenerator;

import java.util.List;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.Limit;
import com.kbdisplay.ls1710.domain.Norm;
import com.kbdisplay.ls1710.service.dataJournal.NormGenerator;

@Component("linearInterpolationNormGenerator")
public class LinearInterpolationNormGenerator implements NormGenerator {

	private List<Limit> limits;
	private Norm documentNorm;
	private TreeMap<Double, Double> limitTree;


	public LinearInterpolationNormGenerator() {
	}

	public LinearInterpolationNormGenerator(List<Limit> limits) {
		this.limits = limits;
		limitTree = new TreeMap<Double, Double>();
		for (Limit limit : limits) {
			limitTree.put(limit.getFrequency(), limit.getAmplitude());
		}

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
	public void setDocumentNorm(Norm documentNorm) {
		this.documentNorm = documentNorm;
		this.limits = this.documentNorm.getLimits();
	}

}
