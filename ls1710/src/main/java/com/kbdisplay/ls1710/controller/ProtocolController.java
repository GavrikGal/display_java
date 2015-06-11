package com.kbdisplay.ls1710.controller;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.Harmonic;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.Protocol;
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
//			int lastNumber = oldProtocols.get(oldProtocols.size() - 1)
//					.getNumber();
//			protocol.setNumber(lastNumber + 1);
			protocol.setHarmonics1(measurement.getSpectrums().get(
					measurement.getSpectrums().size() - 1).getHarmonics());
			List<Harmonic> harmonics = new ArrayList<Harmonic>();
			protocol.setHarmonics2(harmonics);
			List<Measurement> measurements2 = new ArrayList<Measurement>();
			measurements2.add(measurement);
			protocol.setMeasurements(measurements2);
			protocol.setNumber(23l);
			protocol.setIndex("Ц");
			protocol = protocolService.save(protocol);

		} else {
			System.out.println("Протоколы найдены");
		}
	}

}
