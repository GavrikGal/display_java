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
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.domain.SpectrumParameter;
import com.kbdisplay.ls1710.service.data.EquipmentService;
import com.kbdisplay.ls1710.service.data.MeasurementService;
import com.kbdisplay.ls1710.service.data.SpectrumParameterService;
import com.kbdisplay.ls1710.service.dataJournal.edit.MeasurementsUpdaterService;
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
	@Autowired
	private SpectrumParameterService spectrumParameterService;

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


	/**
	 * создает новый список представления измерений.
	 *
	 * @return список измерений
	 */
	public final DataTable newDataJournalTable() {

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
	public final EditForm newEditFormDataJournalView() {

		logger.info("Creating new edit form for data Journal");

		EditForm editForm = new DataJournalEditForm();

		editForm.init(lastMeasurement);

		return editForm;
	}

	/**
	 * сохранить новое измерение из формы редактирования.
	 *
	 * TODO заменить на интерфейс
	 *
	 * @param editForm
	 *            - форма редактирования
	 */
	public void save(final EditForm editForm) {

		if (editForm.getData().getModel() != null) {

			ModelOfEquipment model = editForm.getData().getModel();
			if (model.getId() == null) {
				editForm.setShowNewModelDialog(true);
				return;
			}

			SpectrumParameter parameter =
					editForm.getData().getSpectrumParameter();
			parameter = spectrumParameterService.save(parameter);

			Measurement measurement =
					updaterService.saveMeasurements(model, editForm.getData()
							.getSerialNumber(), parameter, editForm.getData()
							.getPurposeOfMeasurement(), editForm.getData()
							.getDescription());

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
