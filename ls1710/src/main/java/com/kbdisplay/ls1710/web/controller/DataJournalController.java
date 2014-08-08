package com.kbdisplay.ls1710.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.Measurand;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.domain.ScreenResolution;
import com.kbdisplay.ls1710.domain.SpectrumParameter;
import com.kbdisplay.ls1710.domain.TypeOfSpectrum;
import com.kbdisplay.ls1710.service.data.MeasurandService;
import com.kbdisplay.ls1710.service.data.MeasurementService;
import com.kbdisplay.ls1710.service.data.ModelService;
import com.kbdisplay.ls1710.service.data.ScreenResolutionService;
import com.kbdisplay.ls1710.service.data.SpectrumParameterService;
import com.kbdisplay.ls1710.service.data.TypeOfSpectrumService;
import com.kbdisplay.ls1710.web.view.dataJournal.EditFormDataJournalView;
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
	 * сервис доступа к моделям изделий.
	 */
	@Autowired
	private ModelService modelService;

	/**
	 * сервис доступа к измеряемым величинам.
	 */
	@Autowired
	private MeasurandService measurandService;

	/**
	 * сервис доступа к разрешениям экранов.
	 */
	@Autowired
	private ScreenResolutionService screenResolutionService;

	/**
	 * сервис доступа к типам спектра.
	 */
	@Autowired
	private TypeOfSpectrumService typeOfSpectrumService;

	@Autowired
	private SpectrumParameterService spectrumParameterService;


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

	/**
	 * создает новую форму для редактирования списка измерений.
	 *
	 * @return форму для редактирования списка измерений
	 */
	public final EditFormDataJournalView newEditFormDataJournalView() {

		logger.info("Creating new edit form for data Journal");

		List<ModelOfEquipment> modelOfEquipments = modelService.findAll();
		List<Measurand> measurands = measurandService.findAll();
		List<ScreenResolution> screenResolutions =
				screenResolutionService.findAll();
		List<TypeOfSpectrum> typeOfSpectrums = typeOfSpectrumService.findAll();

		EditFormDataJournalView editFormDataJournalView =
				new EditFormDataJournalView();
		editFormDataJournalView.setModelOfEquipments(modelOfEquipments);
		editFormDataJournalView.setMeasurands(measurands);
		editFormDataJournalView.setScreenResolutions(screenResolutions);
		editFormDataJournalView.setTypeOfSpectrums(typeOfSpectrums);
		return editFormDataJournalView;
	}

	public void save(EditFormDataJournalView editFormDJView) {

		SpectrumParameter parameter = editFormDJView.getSpectrumParameter();
		System.out.println("1. sp id:" + parameter.getIdSpectrumParameters());
		parameter = spectrumParameterService.save(parameter);
		System.out.println("2. sp id:" + parameter.getIdSpectrumParameters());

		System.out
				.println("model id: " + editFormDJView.getEquipment().getModel().getIdModel());
		System.out.println("description: " + editFormDJView.getDescription());
		editFormDJView.setDescription(null);
	}


}
