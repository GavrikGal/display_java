package com.kbdisplay.ls1710.view.dataJournal.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kbdisplay.ls1710.domain.Harmonic;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.domain.Spectrum;

public class DataJournalDetail {

	private List<Measurement> measurements;

	public DataJournalDetail(List<Measurement> measurements) {
		this.measurements = measurements;

		Map<Double, Harmonic> comparisonTable = new HashMap<Double, Harmonic>();
		List<Double> comparisonFrequencyList = new ArrayList<Double>();

		for (Measurement measurement : measurements) {
			List<Spectrum> spectrums = measurement.getSpectrums();

			for (Spectrum spectrum : spectrums) {
				List<Parameter> parameters = spectrum.getParameters();

				List<Harmonic> harmonics = spectrum.getHarmonics();

				for (Harmonic harmonic : harmonics) {

					if(!comparisonFrequencyList.contains(harmonic.getFrequency())){
						comparisonFrequencyList.add(harmonic.getFrequency());
					}
				}
			}
		}

	}

}
