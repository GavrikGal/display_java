package com.kbdisplay.ls1710.controller;

import java.util.List;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.Equipment;
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
import com.kbdisplay.ls1710.view.dataJournal.DataTable;
import com.kbdisplay.ls1710.view.dataJournal.web.DataJournalTable;
import com.kbdisplay.ls1710.view.dataJournal.web.EditFormDataJournalView;
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

	/**
	 * сервис доступа к параметрам спектров.
	 */
	@Autowired
	private SpectrumParameterService spectrumParameterService;

	/**
	 * сервис сохранения/обновления данных об измерениях.
	 */
	@Autowired
	private MeasurementsUpdaterService updaterService;

	/**
	 * сервис доступа к измерениям.
	 */
	@Autowired
	private MeasurementService measurementService;

	/**
	 * сервис доступа к испытуемым изделиям.
	 */
	@Autowired
	private EquipmentService equipmentService;

	/**
	 * сервис доступа к целям испытаний.
	 */
	@Autowired
	private PurposeOfMeasurementService purposeOfMeasurementService;


	/**
	 * создает новый список представления измерений.
	 *
	 * @return список измерений
	 */
	public final DataTable newDataJournalTable() {

		logger.info("Creating new list of measurement for data Journal");

		DataTable dataTable = new DataJournalTable();
		List<Measurement> measurements = measurementsService.findAll();
		if (measurements.size() > 0) {
			lastMeasurement = measurements.get(measurements.size() - 1);
		} else {
			lastMeasurement = null;
		}
		dataTable.init(measurements);

		return dataTable;
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
			logger.info(" Last Measurement do not initialize!!");
		}

		return editFormDataJournalView;
	}

	/**
	 * сохранить новое измерение из формы редактирования.
	 *
	 * TODO заменить на интерфейс
	 *
	 * @param editFormDJView
	 *            - форма редактирования
	 */
	public void save(final EditFormDataJournalView editFormDJView) {

		if (editFormDJView.getModel() != null) {

			ModelOfEquipment model = editFormDJView.getModel();
			if (model.getId() == null) {
				editFormDJView.setShowNewModelDialog(true);
				return;
			}

			SpectrumParameter parameter = editFormDJView.getSpectrumParameter();
			parameter = spectrumParameterService.save(parameter);

			Measurement measurement =
					updaterService.saveMeasurements(model,
							editFormDJView.getSerialNumber(), parameter,
							editFormDJView.getPurposeOfMeasurement(),
							editFormDJView.getDescription());
			// List<Measurement> measurements = measurementsService.findAll();
			FacesContext fc = FacesContext.getCurrentInstance();
			ELContext elContext =
					FacesContext.getCurrentInstance().getELContext();
			DataTable dataTable =
					(DataTable) fc.getApplication().getELResolver()
							.getValue(elContext, null, "dataJournalTable");

			dataTable.add(measurement);
			// dataTable.init(measurements);
			editFormDJView.setDescription(null);

			fc.addMessage(
					null,
					new FacesMessage("Измерение успешно сохранено", "Изделие: "
							+ model.getName() + " № "
							+ editFormDJView.getSerialNumber()));

			logger.info("Measurement was updated. Model - " + model.getName()
					+ ", serial number - " + editFormDJView.getSerialNumber());
		}
	}

	/**
	 * удалить выделеное измерение из БД и из представления.
	 *
	 * @param selected
	 *            - удаляемое измерение.
	 */
	public void delete(final Measurement selected) {

		logger.info("Measurement was removed. Measurement date - "
				+ selected.getDate().getDate() + ", model - "
				+ selected.getEquipment().getModel().getName()
				+ ", serial number - "
				+ selected.getEquipment().getSerialNumber() + ", user - "
				+ selected.getUser().getLogin());

		FacesContext fc = FacesContext.getCurrentInstance();
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();

		DataTable dataTable =
				(DataTable) fc.getApplication().getELResolver()
						.getValue(elContext, null, "dataJournalTable");

		Long equipId = selected.getEquipment().getId();

		measurementService.delete(selected);
		Equipment equipment = equipmentService.findById(equipId);

		System.out.println(equipment.getMeasurements());

		if (equipment.getMeasurements().size() < 1) {
			equipmentService.delete(equipment);
		}

		dataTable.deleteSelected();

		fc.addMessage(null, new FacesMessage("Измерение удалено"));
	}

	/**
	 * установить в форме редактирования выделеное измерение.
	 *
	 * @param selected - редактируемое измерение.
	 */
	public void edit(final Measurement selected) {
		FacesContext fc = FacesContext.getCurrentInstance();
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();

		EditFormDataJournalView editForm =
				(EditFormDataJournalView) fc.getApplication().getELResolver()
						.getValue(elContext, null, "editFormDJView");
		if (selected.getEquipment().getModel() != null) {
			editForm.setModel(selected.getEquipment().getModel());
		}
		if (selected.getEquipment().getSerialNumber() != null) {
			editForm.setSerialNumber(selected.getEquipment().getSerialNumber());
		}

		if (selected.getNextMeasurement() != null) {
			Measurement lastMeasurement = selected.getNextMeasurement();
			while (lastMeasurement.getNextMeasurement() != null) {
				lastMeasurement = lastMeasurement.getNextMeasurement();
			}
			editForm.setPurposeOfMeasurement(lastMeasurement.getPurpose());
		} else {
			editForm.setPurposeOfMeasurement(selected.getPurpose());
		}
	}

	/**
	 * создает новый бин-поддержки для добавления модели в БД.
	 *
	 * @param model
	 *            - модель изделия, которую надо добавить в приложение.
	 * @return бин добавления модели.
	 */
	public ModelBean newModelBean(final ModelOfEquipment model) {
		ModelBean modelBean = new ModelBean();
		modelBean.setModel(model);
		return modelBean;
	}
}
