package com.kbdisplay.ls1710.controller;

import java.util.List;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.Measurand;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.domain.PurposeOfMeasurement;
import com.kbdisplay.ls1710.domain.ScreenResolution;
import com.kbdisplay.ls1710.domain.SpectrumParameter;
import com.kbdisplay.ls1710.domain.TypeOfSpectrum;
import com.kbdisplay.ls1710.service.data.EquipmentService;
import com.kbdisplay.ls1710.service.data.MeasurandService;
import com.kbdisplay.ls1710.service.data.MeasurementService;
import com.kbdisplay.ls1710.service.data.ModelService;
import com.kbdisplay.ls1710.service.data.PurposeOfMeasurementService;
import com.kbdisplay.ls1710.service.data.ScreenResolutionService;
import com.kbdisplay.ls1710.service.data.SpectrumParameterService;
import com.kbdisplay.ls1710.service.data.TypeOfSpectrumService;
import com.kbdisplay.ls1710.service.dataJournal.edit.MeasurementsUpdaterService;
import com.kbdisplay.ls1710.view.dataJournal.web.EditFormDataJournalView;
import com.kbdisplay.ls1710.view.dataJournal.web.ListOfDataJournalView;
import com.kbdisplay.ls1710.view.dataJournal.web.component.ModelBean;

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
	@Autowired
	private PurposeOfMeasurementService purposeOfMeasurementService;


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
		if (measurements.size() > 0) {
			lastMeasurement = measurements.get(measurements.size() - 1);
		} else {
			lastMeasurement = null;
		}
		listOfDataJournalView
				.createMeasurementDataTable(measurements);

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
		List<PurposeOfMeasurement> purposeOfMeasurements =
				purposeOfMeasurementService.findAll();

		EditFormDataJournalView editFormDataJournalView =
				new EditFormDataJournalView();
		editFormDataJournalView.setModelOfEquipments(modelOfEquipments);
		editFormDataJournalView.setMeasurands(measurands);
		editFormDataJournalView.setScreenResolutions(screenResolutions);
		editFormDataJournalView.setTypeOfSpectrums(typeOfSpectrums);
		editFormDataJournalView.setPurposeOfMeasurements(purposeOfMeasurements);
		editFormDataJournalView.setPurposeOfMeasurement(purposeOfMeasurements
				.get(0));

		if (lastMeasurement != null) {
			editFormDataJournalView.setModel(lastMeasurement.getEquipment()
					.getModel());
			SpectrumParameter lastSpectrumParameter =
					lastMeasurement.getSpectrums()
							.get(lastMeasurement.getSpectrums().size() - 1)
							.getParameter();
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

		if (editFormDJView.getModel() != null) {

			ModelOfEquipment model = editFormDJView.getModel();
			if (model.getId() == null) {
				editFormDJView.setShowNewModelDialog(true);
				return;
			}

			SpectrumParameter parameter = editFormDJView.getSpectrumParameter();
			parameter = spectrumParameterService.save(parameter);

			// Version lastVersion = editFormDJView.getVersion();
			// Version currentVersion;
			// if (lastVersion == null) {
			// currentVersion = new Version("1.0");
			// } else {
			// currentVersion = lastVersion;
			// if (editFormDJView.isRepeated()) {
			// currentVersion.setPart(1, currentVersion.getPart(1) + 1);
			// } else {
			// currentVersion.setPart(0, currentVersion.getPart(0) + 1);
			// }
			// }

			// Measurement measurement =
			updaterService.saveMeasurements(model,
					editFormDJView.getSerialNumber(), parameter,
					editFormDJView.getPurposeOfMeasurement(),
					/* currentVersion, */editFormDJView.getDescription());
			List<Measurement> measurements = measurementsService.findAll();
			listOfDataJournalView
					.createMeasurementDataTable(measurements);
			// Equipment newEquipment = new Equipment();
			// newEquipment.setModel(editFormDJView.getEquipment().getModel());
			// newEquipment.setSerialNumber(editFormDJView.getEquipment()
			// .getSerialNumber());
			// editFormDJView.setEquipment(newEquipment);
			editFormDJView.setDescription(null);

			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage(
					null,
					new FacesMessage("Измерение успешно сохранено", "Изделие: "
							+ model.getName() + " № "
							+ editFormDJView.getSerialNumber()));
		}
	}

	public void delete(ListOfDataJournalView listOfDataJournalView) {

		measurementService.delete(listOfDataJournalView
				.getSelectedMeasurementForView().getMeasurement());

		listOfDataJournalView.deleteMeasurement();

		FacesContext fc = FacesContext.getCurrentInstance();



		ELContext elContext = FacesContext.getCurrentInstance().getELContext();

		ListOfDataJournalView list = (ListOfDataJournalView) FacesContext.getCurrentInstance().getApplication()
			    .getELResolver().getValue(elContext, null, "listOfDataJournalView");

		fc.addMessage(null, new FacesMessage("Измерение удалено"));
	}

	/**
	 * проверка являются ли испытания для данного изделия повторными.
	 *
	 * @param editFormDJView
	 *            форма редактирования данных таблицы измерений.
	 *
	 * @deprecated надо убрать этот метод
	 */
	@Deprecated
	// public void checkOnRepeatedMeasurement(
	// final EditFormDataJournalView editFormDJView) {
	// ModelOfEquipment model = editFormDJView.getModel();
	// if (model != null) {
			// String serialNumber = editFormDJView.getSerialNumber();
			// if (model.getIdModel() != null && serialNumber != null) {
			// Equipment equipment =
			// equipmentService.findBySerialNumberAndModel(
			// serialNumber, model);
			// List<Measurement> measurements =
			// measurementService.findByEquipment(equipment);
			// if (!measurements.isEmpty()) {
			// DateTime currentDate = new DateTime();
			// currentDate = currentDate.withTime(0, 0, 0, 0);
			// boolean repeated = false;
			// Version version = null;
			// for (Measurement measurement : measurements) {
			// DateTime date =
			// measurement.getDateOfMeasurement().getDate();
			// if (currentDate.isAfter(date)) {
			// repeated = true;
			// } else {
			// repeated = false;
			// }
			// version = new Version(measurement.getVersion());
			// }
			// if (repeated) {
			// editFormDJView.setPurposeOfMeasurement(editFormDJView
			// .getPurposeOfMeasurements().get(1));
			// }
			// // editFormDJView.setRepeated(repeated);
			// editFormDJView.setVersion(version);
			// }
			// }
			// }
			// }
			/**
			 * создает новый бин-поддержки для добавления модели в БД.
			 *
			 * @return бин добавления модели.
			 */
			public
			ModelBean newModelBean(ModelOfEquipment model) {
		ModelBean modelBean = new ModelBean();
		modelBean.setModel(model);
		return modelBean;
	}
}
