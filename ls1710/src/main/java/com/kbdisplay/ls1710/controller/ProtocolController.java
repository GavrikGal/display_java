package com.kbdisplay.ls1710.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.Harmonic;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.Protocol;
import com.kbdisplay.ls1710.domain.Spectrum;
import com.kbdisplay.ls1710.service.data.ProtocolService;

@Component("protocolController")
public class ProtocolController {

	/**
	 * логгер класса.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(ProtocolController.class);

	@Autowired
	private ProtocolService protocolService;

	public List<Protocol> newProtocolJournal() {
		List<Protocol> protocols = protocolService.findAll();
		return protocols;
	}

	public void createProtocol(Measurement measurement) {
		List<Measurement> measurements = new ArrayList<Measurement>();
		measurements.add(measurement);
		while (measurement.getNextMeasurement() != null) {
			measurements.add(measurement.getNextMeasurement());
			measurement = measurement.getNextMeasurement();
		}
		List<Protocol> protocols = protocolService
				.findByMeasurements(measurements);
		if (protocols == null || protocols.isEmpty()) {
			logger.info("Creating new protocol");
			Protocol protocol = new Protocol();
			DateTime date = new DateTime();
			protocol.setDate(date);
			List<Protocol> oldProtocols = protocolService.findAll();
			Long lastNumber = oldProtocols.get(oldProtocols.size() - 1)
					.getNumber();
			protocol.setNumber(lastNumber + 1);
			List<Spectrum> spectrums = measurement.getSpectrums();
			int lastVersion = 0;
			for (Spectrum spectrum : spectrums) {
				int currentVersion = spectrum.getVersion();
				if (lastVersion < currentVersion) {
					lastVersion = currentVersion;
				}
			}
			List<Spectrum> lastSpectrums = new ArrayList<Spectrum>();
			for (Spectrum spectrum : spectrums) {
				if (spectrum.getVersion() == lastVersion) {
					lastSpectrums.add(spectrum);
				}
			}
			List<Harmonic> allLastHarmonic = new ArrayList<Harmonic>();
			for (Spectrum spectrum : lastSpectrums) {
				for (Harmonic harmonic : spectrum.getHarmonics()) {
					allLastHarmonic.add(harmonic);
				}
			}
			List<Harmonic> maxLastHarmonics = new ArrayList<Harmonic>();
			if (allLastHarmonic.size() > 10) {
				Collections.sort(allLastHarmonic, new Comparator() {
					@Override
					public int compare(Object synchronizedListOne,
							Object synchronizedListTwo) {
						// use instanceof to verify the references are indeed of
						// the type in question
						return ((Harmonic) synchronizedListOne).getReserve()
								.compareTo(
										((Harmonic) synchronizedListTwo)
												.getReserve());
					}
				});

				for (int i = 0; i < 10; i++) {
					maxLastHarmonics.add(allLastHarmonic.get(i));
				}
			} else {
				maxLastHarmonics = allLastHarmonic;
			}


			protocol.setHarmonics(maxLastHarmonics);

			List<Measurement> measurements2 = new ArrayList<Measurement>();
			measurements2.add(measurement);
			protocol.setMeasurements(measurements2);
			// protocol.setNumber(23l);
			protocol.setPostfix("Ц");
			protocol = protocolService.save(protocol);

		} else {
			System.out.println("Протоколы найдены");
		}
	}

}
