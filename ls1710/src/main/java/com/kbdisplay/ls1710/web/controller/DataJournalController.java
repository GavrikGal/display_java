package com.kbdisplay.ls1710.web.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.Measurand;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.domain.ScreenResolution;
import com.kbdisplay.ls1710.domain.SpectrumParameter;
import com.kbdisplay.ls1710.domain.TypeOfSpectrum;
import com.kbdisplay.ls1710.service.data.EquipmentService;
import com.kbdisplay.ls1710.service.data.MeasurandService;
import com.kbdisplay.ls1710.service.data.MeasurementService;
import com.kbdisplay.ls1710.service.data.ModelService;
import com.kbdisplay.ls1710.service.data.ScreenResolutionService;
import com.kbdisplay.ls1710.service.data.SpectrumParameterService;
import com.kbdisplay.ls1710.service.data.TypeOfSpectrumService;
import com.kbdisplay.ls1710.service.dataJournal.edit.MeasurementsUpdaterService;
import com.kbdisplay.ls1710.web.view.dataJournal.EditFormDataJournalView;
import com.kbdisplay.ls1710.web.view.dataJournal.ListOfDataJournalView;
import com.kbdisplay.ls1710.web.view.dataJournal.component.ModelBean;

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
	 * последнее измерение из БД.
	 */
	private Measurement lastMeasurement;

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
	@Autowired
	private MeasurementsUpdaterService updaterService;
	@Autowired
	private MeasurementService measurementService;
	@Autowired
	private EquipmentService equipmentService;


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
		lastMeasurement = measurements.get(measurements.size() - 1);
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

		if (lastMeasurement != null) {
			editFormDataJournalView.setModel(lastMeasurement.getEquipment()
					.getModel());
			SpectrumParameter lastSpectrumParameter =
					lastMeasurement.getSpectrums()
							.get(lastMeasurement.getSpectrums().size() - 1)
							.getSpectrumParameters();
			editFormDataJournalView.getSpectrumParameter().setMeasurand(
					lastSpectrumParameter.getMeasurand());
			editFormDataJournalView.getSpectrumParameter().setTypeOfSpectrum(
					lastSpectrumParameter.getTypeOfSpectrum());
			editFormDataJournalView.getSpectrumParameter().setScreenResolution(
					lastSpectrumParameter.getScreenResolution());
		} else {
			System.out.println(" Last Measurement do not initialize!!");
		}

		return editFormDataJournalView;
	}

	public void save(final EditFormDataJournalView editFormDJView,
			final ListOfDataJournalView listOfDataJournalView) {

		FacesContext fc = FacesContext.getCurrentInstance();
		if (editFormDJView.getModel() != null) {

			ModelOfEquipment model = editFormDJView.getModel();
			if (model.getIdModel() == null) {
				editFormDJView.setShowNewModelDialog(true);
				return;
			}

			SpectrumParameter parameter = editFormDJView.getSpectrumParameter();
			parameter = spectrumParameterService.save(parameter);

			Measurement measurement =
					updaterService.saveMeasurements(model,
							editFormDJView.getSerialNumber(), parameter,
							editFormDJView.getVersion(),
							editFormDJView.getDescription());
			List<Measurement> measurements = measurementsService.findAll();
			listOfDataJournalView
					.setMeasurementForViewsFromMeasurements(measurements);
			// Equipment newEquipment = new Equipment();
			// newEquipment.setModel(editFormDJView.getEquipment().getModel());
			// newEquipment.setSerialNumber(editFormDJView.getEquipment()
			// .getSerialNumber());
			// editFormDJView.setEquipment(newEquipment);
			editFormDJView.setDescription(null);

			fc.addMessage(
					null,
					new FacesMessage("Измерение успешно сохранено", "Изделие: "
							+ model.getModelName() + " № "
							+ editFormDJView.getSerialNumber()));
		}
	}

	/**
	 * проверка являются ли испытания для данного изделия повторными.
	 *
	 * @param editFormDJView
	 *            форма редактирования данных таблицы измерений.
	 */
	public void checkOnRepeatedMeasurement(
			final EditFormDataJournalView editFormDJView) {
		ModelOfEquipment model = editFormDJView.getModel();
		if (model != null) {
			String serialNumber = editFormDJView.getSerialNumber();
			if (model.getIdModel() != null && serialNumber != null) {
				Equipment equipment =
						equipmentService.findBySerialNumberAndModel(
								serialNumber, model);
				List<Measurement> measurements =
						measurementService.findByEquipment(equipment);
				if (!measurements.isEmpty()) {
					DateTime currentDate = new DateTime();
					currentDate = currentDate.withTime(0, 0, 0, 0);
					boolean repeated = false;
					for (Measurement measurement : measurements) {
						DateTime date =
								measurement.getDateOfMeasurement().getDate();
						if (currentDate.isAfter(date)) {
							repeated = true;
						} else {
							repeated = false;
						}
					}
					editFormDJView.setRepeated(repeated);
				} else {
					editFormDJView.setRepeated(false);
				}
			} else {
				editFormDJView.setRepeated(false);
			}
		} else {
			editFormDJView.setRepeated(false);
		}
	}

	/**
	 * создает новый бин-поддержки для добавления модели в БД.
	 *
	 * @return бин добавления модели.
	 */
	public ModelBean newModelBean(ModelOfEquipment model) {
		ModelBean modelBean = new ModelBean();
		modelBean.setModel(model);
		return modelBean;
	}
}
