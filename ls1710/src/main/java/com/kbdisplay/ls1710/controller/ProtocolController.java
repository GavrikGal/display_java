package com.kbdisplay.ls1710.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.el.ELContext;
import javax.faces.context.FacesContext;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

import com.kbdisplay.ls1710.domain.Harmonic;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.Protocol;
import com.kbdisplay.ls1710.domain.Spectrum;
import com.kbdisplay.ls1710.service.data.ProtocolService;
import com.kbdisplay.ls1710.view.protocol.ProtocolJournal;
import com.kbdisplay.ls1710.view.protocol.web.ProtocolJournalImpl;

@Component("protocolController")
public class ProtocolController {

	/**
	 * логгер класса.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(ProtocolController.class);

	@Autowired
	private ProtocolService protocolService;

	public ProtocolJournal newProtocolJournal() {
		List<Protocol> protocols = protocolService.findAll();
		ProtocolJournal protocolJournal = new ProtocolJournalImpl();
		protocolJournal.setProtocols(protocols);
		return protocolJournal;
	}

	public void createProtocol(Measurement measurement) {

		FacesContext fc = FacesContext.getCurrentInstance();
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		ProtocolJournal protocolJournal =
				(ProtocolJournal) fc.getApplication().getELResolver()
						.getValue(elContext, null, "protocolJournal");

		List<Measurement> measurements = new ArrayList<Measurement>();
		measurements.add(measurement);

		List<Protocol> protocols = protocolService
				.findByMeasurements(measurements);
		if (protocols == null || protocols.isEmpty()) {
			logger.info("Creating new protocol");

			List<Spectrum> spectrums = measurement.getSpectrums();
			List<Harmonic> maxLastHarmonics = this.getLastMaxHarmonic(spectrums);

			List<Measurement> measurements2 = new ArrayList<Measurement>();
			measurements2.add(measurement);

			Protocol protocol = this.newProtocol(maxLastHarmonics, measurements2);

			protocolJournal.setSelectedProtocol(protocol);
		} else {
			/*	if (measurement.getNextMeasurement() != null) {
				logger.info("update protocol");

				while (measurement.getNextMeasurement() != null) {
					measurements.add(measurement.getNextMeasurement());
					measurement = measurement.getNextMeasurement();
				}

				List<Spectrum> allSpectrums = new ArrayList<Spectrum>();
				for (Measurement curMeasurement : measurements) {
					allSpectrums.addAll(curMeasurement.getSpectrums());
				}

				List<Harmonic> maxLastHarmonics = this.getLastMaxHarmonic(allSpectrums);

				Protocol protocol = this.newProtocol(maxLastHarmonics, measurements);

				protocolJournal.setSelectedProtocol(protocol);

			} else {
				protocolJournal.setSelectedProtocol(protocols.get(protocols.size()-1));
			}*/
			protocolJournal.setSelectedProtocol(protocols.get(protocols.size()-1));
		}
	}

	private Protocol newProtocol(List<Harmonic> maxLastHarmonics, List<Measurement> measurements){
		Protocol protocol = new Protocol();
		DateTime date = new DateTime();
		protocol.setDate(date);
		List<Protocol> oldProtocols = protocolService.findAll();
		Long lastNumber = oldProtocols.get(oldProtocols.size() - 1)
				.getNumber();
		protocol.setNumber(lastNumber + 1);
		protocol.setHarmonics(maxLastHarmonics);
		protocol.setMeasurements(measurements);
		protocol.setPostfix("Ц");
		protocol = protocolService.save(protocol);

		return protocol;
	}

	private List<Harmonic> getLastMaxHarmonic(List<Spectrum> spectrums) {
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
		return maxLastHarmonics;
	}

	public Protocol showProtocol(RequestContext context) {

		Protocol protocol = (Protocol) context.getRequestScope().get("protocol");


		return protocol;
	}

}
