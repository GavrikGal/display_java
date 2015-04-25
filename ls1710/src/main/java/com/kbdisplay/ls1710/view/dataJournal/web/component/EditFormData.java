package com.kbdisplay.ls1710.view.dataJournal.web.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.MenuModel;

import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.domain.PurposeOfMeasurement;
import com.kbdisplay.ls1710.domain.TypeOfParameter;
import com.kbdisplay.ls1710.service.data.ModelService;
import com.kbdisplay.ls1710.service.data.PurposeOfMeasurementService;
import com.kbdisplay.ls1710.view.dataJournal.EditData;

/**
 * данные формы для редактирования.
 *
 * @author Gavrik
 */
@ManagedBean
@ViewScoped
public class EditFormData implements Serializable, EditData {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * модель испытуемого изделия.
	 */
	private ModelOfEquipment model;

	/**
	 * серийный номер испытуемого изделия.
	 */
	private String serialNumber;

	/**
	 * параметры измеренного спектра.
	 *
	 * такие как измеряемая величина, разрешение экрана, тип измерений и т.д.
	 */
	// private SpectrumParameter spectrumParameter;

	/**
	 * цель измерений (пи/пси/типовые/т.д.).
	 */
	private PurposeOfMeasurement purposeOfMeasurement;

	/**
	 * описание измерения.
	 *
	 * сюда записываются данные пользователем, затем они парсятся и заносятся в
	 * БД.
	 */
	private String description;

	/**
	 * список доступных моделей изделий.
	 */
	private List<ModelOfEquipment> modelOfEquipments;

	/**
	 * список доступных измеряемых физических величин.
	 */
	// private List<Measurand> measurands;

	/**
	 * список доступных разрешений экрана.
	 */
	// private List<ScreenResolution> screenResolutions;

	/**
	 * список доступных типов спектра.
	 */
	// private List<TypeOfSpectrum> typeOfSpectrums;

	/**
	 * список доступных целей измерений.
	 */
	private List<PurposeOfMeasurement> purposeOfMeasurements;

	private TypeOfParameter newType;

	private TypeOfParameter selectedType;
	private List<TypeOfParameter> availableTypes;
	private MenuModel menuModel;

	private List<Parameter> selectedParameters;

	private List<List<Parameter>> availableParameterLists;

	/**
	 * конструктор по умолчанию.
	 */
	public EditFormData() {
		model = null;
		selectedParameters = new ArrayList<Parameter>();
		selectedType = null;
		availableParameterLists = new ArrayList<List<Parameter>>();
	}

	@Override
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();

		ModelService modelService = (ModelService) fc.getApplication()
				.getELResolver().getValue(elContext, null, "modelService");

		PurposeOfMeasurementService purposeOfMeasurementService = (PurposeOfMeasurementService) fc
				.getApplication().getELResolver()
				.getValue(elContext, null, "purposeOfMeasurementService");

		modelOfEquipments = modelService.findAll();

		purposeOfMeasurements = purposeOfMeasurementService.findAll();
	}

	@Override
	public void initSelected(final Measurement selectedMeasurement) {
		selectedParameters = new ArrayList<Parameter>();
		availableParameterLists = new ArrayList<List<Parameter>>();
		model = selectedMeasurement.getEquipment().getModel();

		// получаем параметры из последнего измеренного спектра
		List<Parameter> parameters = selectedMeasurement.getSpectrums()
				.get(selectedMeasurement.getSpectrums().size() - 1)
				.getParameters();
		for (Parameter parameter : parameters) {
			Parameter selectedParameter = new Parameter();
			selectedParameter.setName(parameter.getName());
			selectedParameters.add(selectedParameter);

		}

		TypeOfParameter type = null;

		for (Parameter parameter : parameters) {
			type = parameter.getType();
			List<Parameter> availableParameter = type.getParameters();
			availableParameterLists.add(availableParameter);
			selectedType = type;
		}

		purposeOfMeasurement = selectedMeasurement.getPurpose();
	};



	@Override
	public ModelOfEquipment getModel() {
		return model;
	}

	@Override
	public void setModel(final ModelOfEquipment model) {
		this.model = model;
	}

	@Override
	public String getSerialNumber() {
		return serialNumber;
	}

	@Override
	public void setSerialNumber(final String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Override
	public PurposeOfMeasurement getPurposeOfMeasurement() {
		return purposeOfMeasurement;
	}

	@Override
	public void setPurposeOfMeasurement(final PurposeOfMeasurement purpose) {
		this.purposeOfMeasurement = purpose;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(final String description) {
		this.description = description;
	}

	@Override
	public List<ModelOfEquipment> getModelOfEquipments() {
		return modelOfEquipments;
	}

	@Override
	public void setModelOfEquipments(
			final List<ModelOfEquipment> modelOfEquipments) {
		this.modelOfEquipments = modelOfEquipments;
	}

	@Override
	public List<PurposeOfMeasurement> getPurposeOfMeasurements() {
		return purposeOfMeasurements;
	}

	@Override
	public void setPurposeOfMeasurements(
			final List<PurposeOfMeasurement> purposeOfMeasurements) {
		this.purposeOfMeasurements = purposeOfMeasurements;
	}

	@Override
	public List<Parameter> getSelectedParameters() {
		return selectedParameters;
	}

	@Override
	public void setSelectedParameters(List<Parameter> selectedParameters) {
		this.selectedParameters = selectedParameters;
	}

	@Override
	public List<TypeOfParameter> getAvailableTypes() {
		return availableTypes;
	}

	@Override
	public void setAvailableTypes(List<TypeOfParameter> availableTypes) {
		this.availableTypes = availableTypes;
	}

	@Override
	public TypeOfParameter getSelectedType() {
		return selectedType;
	}

	@Override
	public void setSelectedType(TypeOfParameter selectedType) {
		this.selectedType = selectedType;
	}

	@Override
	public List<List<Parameter>> getAvailableParameterLists() {
		return availableParameterLists;
	}

	@Override
	public void setAvailableParameterLists(
			List<List<Parameter>> availableParameterLists) {
		this.availableParameterLists = availableParameterLists;
	}

	@Override
	public TypeOfParameter getNewType() {
		return newType;
	}

	@Override
	public void setNewType(TypeOfParameter newType) {
		this.newType = newType;
	}

	@Override
	public MenuModel getMenuModel() {
		return menuModel;
	}

	@Override
	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
