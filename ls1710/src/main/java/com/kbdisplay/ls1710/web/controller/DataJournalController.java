package com.kbdisplay.ls1710.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.service.data.MeasurementService;
import com.kbdisplay.ls1710.web.view.dataJournal.ListOfDataJournalView;

/**
 * контроллер отвечающий за обработку представления списка измерений.
 *
 * @author Gavrik
 * @version 2.0
 */
@Component("dataJournalController")
public class DataJournalController {

	/**
	 * логгер класса.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(DataJournalController.class);

	/**
	 * сервис доступа к данным по измерениям.
	 */
	@Autowired
	private MeasurementService measurementsService;

	/**
	 * создает новый список представления измерений.
	 *
	 * @return список измерений
	 */
	public final ListOfDataJournalView newListOfDataJournalView() {

		logger.info("Creating new list of measurement for data Journal");

		ListOfDataJournalView listOfDataJournalView =
				new ListOfDataJournalView();
		List<Measurement> measurements = measurementsService.findAll();
		listOfDataJournalView
				.setMeasurementForViewsFromMeasurements(measurements);

		return listOfDataJournalView;
	}

}
