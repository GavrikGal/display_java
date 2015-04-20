package com.kbdisplay.ls1710.view.dataJournal.web.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

import com.kbdisplay.ls1710.domain.Measurand;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.domain.PurposeOfMeasurement;
import com.kbdisplay.ls1710.domain.ScreenResolution;
import com.kbdisplay.ls1710.domain.SpectrumParameter;
import com.kbdisplay.ls1710.domain.TypeOfParameter;
import com.kbdisplay.ls1710.domain.TypeOfSpectrum;
import com.kbdisplay.ls1710.service.data.MeasurandService;
import com.kbdisplay.ls1710.service.data.ModelService;
import com.kbdisplay.ls1710.service.data.ParameterService;
import com.kbdisplay.ls1710.service.data.PurposeOfMeasurementService;
import com.kbdisplay.ls1710.service.data.ScreenResolutionService;
import com.kbdisplay.ls1710.service.data.TypeOfParameterService;
import com.kbdisplay.ls1710.service.data.TypeOfSpectrumService;
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
	private SpectrumParameter spectrumParameter;

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
	private List<Measurand> measurands;

	/**
	 * список доступных разрешений экрана.
	 */
	private List<ScreenResolution> screenResolutions;

	/**
	 * список доступных типов спектра.
	 */
	private List<TypeOfSpectrum> typeOfSpectrums;

	/**
	 * список доступных целей измерений.
	 */
	private List<PurposeOfMeasurement> purposeOfMeasurements;

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
		spectrumParameter = new SpectrumParameter();
	}

	@Override
	public void init() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();

		ModelService modelService = (ModelService) fc.getApplication()
				.getELResolver().getValue(elContext, null, "modelService");
		MeasurandService measurandService = (MeasurandService) fc
				.getApplication().getELResolver()
				.getValue(elContext, null, "measurandService");
		ScreenResolutionService screenResolutionService = (ScreenResolutionService) fc
				.getApplication().getELResolver()
				.getValue(elContext, null, "screenResolutionService");
		TypeOfSpectrumService typeOfSpectrumService = (TypeOfSpectrumService) fc
				.getApplication().getELResolver()
				.getValue(elContext, null, "typeOfSpectrumService");
		PurposeOfMeasurementService purposeOfMeasurementService = (PurposeOfMeasurementService) fc
				.getApplication().getELResolver()
				.getValue(elContext, null, "purposeOfMeasurementService");

		ParameterService parameterService = (ParameterService) fc
				.getApplication().getELResolver()
				.getValue(elContext, null, "parameterService");

		TypeOfParameterService typeOfParameterService = (TypeOfParameterService) fc
				.getApplication().getELResolver()
				.getValue(elContext, null, "typeOfParameterService");

		menuModel = new DefaultMenuModel();

		// List<TypeOfParameter> list = typeOfParameterService.findAll();
		availableTypes = typeOfParameterService.findByPrevTypeId(null);

		if (availableTypes != null) {

			int itemN = 0;
			for (TypeOfParameter availableType : availableTypes) {
				DefaultMenuItem item = new DefaultMenuItem(
						availableType.getName());
				item.setCommand("#{editFormDJView.data.selectTypeOfParameter('"
						+ itemN + "')}");
				item.setUpdate("spectrumParameters spectrumParametersMenu");

				menuModel.addElement(item);

				itemN++;
			}
		}

		// Parameter parameter = new Parameter();
		// parameter.setName("E");
		// parameter.setType(list.get(0));
		// List<Parameter> newParen = new ArrayList<Parameter>();
		// parameter.setParentParameters(newParen);
		// parameter.getParentParameters().add(parentParameters.get(0));
		// parameterService.save(parameter);
		//
		// parameter = new Parameter();
		// parameter.setName("U");
		// parameter.setType(list.get(0));
		// newParen = new ArrayList<Parameter>();
		// parameter.setParentParameters(newParen);
		// parameter.getParentParameters().add(parentParameters.get(0));
		// parameterService.save(parameter);

		modelOfEquipments = modelService.findAll();
		measurands = measurandService.findAll();
		screenResolutions = screenResolutionService.findAll();
		typeOfSpectrums = typeOfSpectrumService.findAll();
		purposeOfMeasurements = purposeOfMeasurementService.findAll();


		selectedParameters = new ArrayList<Parameter>();
		availableParameterLists = new ArrayList<List<Parameter>>();
	}

	@Override
	public void selectTypeOfParameter(String itemN) {
		selectedType = availableTypes.get(Integer.parseInt(itemN));
		availableTypes = selectedType.getNextTypes();
		menuModel = null;

		if (selectedType != null) {

			List<Parameter> availableParameters = selectedType.getParameters();

			if ((availableParameters != null) && (!availableParameters.isEmpty())) {
				availableParameterLists.add(availableParameters);
				selectedParameters.add(selectedType.getParameters().get(0));
			}
		}


		if (availableTypes != null) {
			menuModel = new DefaultMenuModel();
			int itemN2 = 0;
			for (TypeOfParameter availableType : availableTypes) {
				DefaultMenuItem item = new DefaultMenuItem(
						availableType.getName());
				item.setCommand("#{editFormDJView.data.selectTypeOfParameter('"
						+ itemN2 + "')}");
				item.setUpdate("spectrumParameters spectrumParametersMenu");

				menuModel.addElement(item);

				itemN2++;

			}
		}
	}

	@Override
	public void initSelected(final Measurement selectedMeasurement) {
		model = selectedMeasurement.getEquipment().getModel();
		SpectrumParameter lastSpectrumParameter = selectedMeasurement
				.getSpectrums()
				.get(selectedMeasurement.getSpectrums().size() - 1)
				.getParameter();
		spectrumParameter.setMeasurand(lastSpectrumParameter.getMeasurand());
		spectrumParameter.setTypeOfSpectrum(lastSpectrumParameter
				.getTypeOfSpectrum());
		spectrumParameter.setScreenResolution(lastSpectrumParameter
				.getScreenResolution());
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
	public SpectrumParameter getSpectrumParameter() {
		return spectrumParameter;
	}

	@Override
	public void setSpectrumParameter(final SpectrumParameter parameter) {
		this.spectrumParameter = parameter;
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
	public List<Measurand> getMeasurands() {
		return measurands;
	}

	@Override
	public void setMeasurands(final List<Measurand> measurands) {
		this.measurands = measurands;
	}

	@Override
	public List<ScreenResolution> getScreenResolutions() {
		return screenResolutions;
	}

	@Override
	public void setScreenResolutions(
			final List<ScreenResolution> screenResolutions) {
		this.screenResolutions = screenResolutions;
	}

	@Override
	public List<TypeOfSpectrum> getTypeOfSpectrums() {
		return typeOfSpectrums;
	}

	@Override
	public void setTypeOfSpectrums(final List<TypeOfSpectrum> typeOfSpectrums) {
		this.typeOfSpectrums = typeOfSpectrums;
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

	public TypeOfParameter getSelectedType() {
		return selectedType;
	}

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
	public MenuModel getMenuModel() {
		return menuModel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}





}
