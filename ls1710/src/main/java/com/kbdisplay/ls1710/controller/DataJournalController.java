package com.kbdisplay.ls1710.controller;

import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.webflow.execution.RequestContext;

import com.kbdisplay.ls1710.domain.Document;
import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.domain.ModelType;
import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.domain.TypeOfParameter;
import com.kbdisplay.ls1710.service.data.DocumentService;
import com.kbdisplay.ls1710.service.data.EquipmentService;
import com.kbdisplay.ls1710.service.data.LimitService;
import com.kbdisplay.ls1710.service.data.MeasurementService;
import com.kbdisplay.ls1710.service.data.ModelService;
import com.kbdisplay.ls1710.service.data.ModelTypeService;
import com.kbdisplay.ls1710.service.data.NormService;
import com.kbdisplay.ls1710.service.data.ParameterService;
import com.kbdisplay.ls1710.service.dataJournal.MeasurementsUpdaterService;
import com.kbdisplay.ls1710.service.dataJournal.NormGenerator;
import com.kbdisplay.ls1710.service.dataJournal.NormGeneratorService;
import com.kbdisplay.ls1710.service.dataJournal.normGenerator.NormNotFindException;
import com.kbdisplay.ls1710.view.dataJournal.DataTable;
import com.kbdisplay.ls1710.view.dataJournal.EditForm;
import com.kbdisplay.ls1710.view.dataJournal.web.DataJournalEditForm;
import com.kbdisplay.ls1710.view.dataJournal.web.DataJournalTable;
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
	private MeasurementService measurementService;

	/**
	 * сервис доступа к параметрам спектров.
	 */
	// @Autowired
	// private SpectrumParameterService spectrumParameterService;

	/**
	 * сервис сохранения/обновления данных об измерениях.
	 */
	@Autowired
	private MeasurementsUpdaterService updaterService;

	/**
	 * сервис доступа к испытуемым изделиям.
	 */
	@Autowired
	private EquipmentService equipmentService;

	@Autowired
	private ParameterService parameterService;

	@Autowired
	private NormService normService;

	@Autowired
	private LimitService limitService;

	@Autowired
	private NormGeneratorService normGeneratorService;

	@Autowired
	private ModelService modelService;

	@Autowired
	private ModelTypeService modelTypeService;

	@Autowired
	private DocumentService documentService;


	/**
	 * создает новый список представления измерений.
	 *
	 * @return список измерений
	 */
	public DataTable newDataJournalTable() {

		logger.info("Creating new list of measurement for data Journal");

		DataTable dataTable = new DataJournalTable();
		List<Measurement> measurements = measurementService.findAll();
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
	public EditForm newEditFormDataJournalView() {

		logger.info("Creating new edit form for data Journal");

		EditForm editForm = new DataJournalEditForm();

		editForm.init(lastMeasurement);

		return editForm;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	public void updateFromFolder() {
		Measurement measurement = updaterService.updateFromFolder();

		FacesContext fc = FacesContext.getCurrentInstance();
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		DataTable dataTable =
				(DataTable) fc.getApplication().getELResolver()
						.getValue(elContext, null, "dataJournalTable");

		dataTable.add(measurement);

		fc.addMessage(null, new FacesMessage("Измерение успешно сохранено",
				"Изделие: " + measurement.getEquipment().getModel().getName()
						+ " № " + measurement.getEquipment().getSerialNumber()));

	}

	/**
	 * сохранить новое измерение из формы редактирования.
	 *
	 * @param editForm
	 *            - форма редактирования
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	public void save(final EditForm editForm) {
		if (editForm.getData().getModel() != null) {

			ModelOfEquipment model = editForm.getData().getModel();
			if (model.getId() == null) {
				editForm.setShowNewModelDialog(true);
				return;
			}

			// SpectrumParameter parameter =
			// editForm.getData().getSpectrumParameter();
			// parameter = spectrumParameterService.save(parameter);
			List<Parameter> selectedParameters =
					editForm.getData().getSelectedParameters();
			for (int i = 0; i < selectedParameters.size(); i++) {
				Parameter parameter = selectedParameters.get(i);
				if (parameter.getId() == null) {
					TypeOfParameter type = editForm.getData().getSelectedType();
					parameter.setType(type);
					parameter = parameterService.save(parameter);
					selectedParameters.remove(i);
					selectedParameters.add(parameter);
				}
			}

			NormGenerator normGenerator = null;
			try {
				normGenerator =
						normGeneratorService.getNormGenerator(editForm
								.getData().getModel().getDocument(),
								selectedParameters);
			} catch (NormNotFindException e) {
				FacesContext fc = FacesContext.getCurrentInstance();
				fc.addMessage(null, new FacesMessage("Норма не найдена",
						"Для текущих параметров измерений норма не найдена"));
			}

			Measurement measurement =
					updaterService.saveMeasurements(model, editForm.getData()
							.getSerialNumber(), selectedParameters, editForm
							.getData().getPurposeOfMeasurement(), editForm
							.getData().getDescription(), normGenerator);

			FacesContext fc = FacesContext.getCurrentInstance();
			ELContext elContext =
					FacesContext.getCurrentInstance().getELContext();
			DataTable dataTable =
					(DataTable) fc.getApplication().getELResolver()
							.getValue(elContext, null, "dataJournalTable");

			dataTable.add(measurement);
			editForm.getData().setDescription(null);

			fc.addMessage(null, new FacesMessage("Измерение успешно сохранено",
					"Изделие: " + model.getName() + " № "
							+ editForm.getData().getSerialNumber()));

			logger.info("Measurement was updated. Model - " + model.getName()
					+ ", serial number - "
					+ editForm.getData().getSerialNumber());
		}
	}

	/**
	 * удалить выделеное измерение из БД и из представления.
	 *
	 * @param selected
	 *            - удаляемое измерение.
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
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

		if (equipment.getMeasurements().size() < 1) {
			equipmentService.delete(equipment);
		}

		dataTable.deleteSelected();

		fc.addMessage(null, new FacesMessage("Измерение удалено"));
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	public void restrictAccess(final Measurement selected) {

		selected.setRestrictedAccess(true);

		measurementService.save(selected);

		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, new FacesMessage("Ограничение доступа установлено"));
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	public void allowAccess(final Measurement selected) {

		selected.setRestrictedAccess(false);

		measurementService.save(selected);

		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, new FacesMessage("Ограничение доступа снято"));
	}

	public List<Measurement> showDetail(RequestContext context) {

		Long id = context.getRequestScope().getLong("measurementId");
		logger.info("Selected measurement id: {}", id);
		Measurement measurement = measurementService.findById(id);

		List<Measurement> measurements = new ArrayList<Measurement>();

		measurements.add(measurement);
		while (measurement.getNextMeasurement() != null) {
			measurement = measurement.getNextMeasurement();
			measurements.add(measurement);
		}

		return measurements;
	}

	/**
	 * создает новый бин-поддержки для добавления модели в БД.
	 *
	 * @param model
	 *            - модель изделия, которую надо добавить в приложение.
	 * @return бин добавления модели.
	 */
	@PreAuthorize("hasRole('ROLE_USER')")
	public ModelBean newModelBean(final ModelOfEquipment model) {
		ModelBean modelBean = new ModelBean();
		ModelOfEquipment newModel = new ModelOfEquipment();
		Document document = documentService.findById(1l);
		newModel.setDocument(document);
		modelBean.setModel(newModel);
		List<ModelType> modelTypes = modelTypeService.findAll();
		modelBean.setSelectedModelType(modelTypes.get(0));
		modelBean.setModelTypes(modelTypes);

		return modelBean;
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	public void saveModel(ModelOfEquipment model, ModelType selectedModelType) {
		model.setModelType(selectedModelType);
		model = modelService.save(model);
	}
}
