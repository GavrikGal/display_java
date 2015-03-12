package com.kbdisplay.ls1710.web.view.dataJournal;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.Measurand;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.domain.PurposeOfMeasurement;
import com.kbdisplay.ls1710.domain.ScreenResolution;
import com.kbdisplay.ls1710.domain.SpectrumParameter;
import com.kbdisplay.ls1710.domain.TypeOfSpectrum;

/**
 * представление формы для добавления/редактирования списка измерений.
 *
 * @author Gavrik
 *
 */
@Component("editFormDataJournalView")
public class EditFormDataJournalView implements Serializable {

	/**
	 * серийный номер класса.
	 */
	private static final long serialVersionUID = 141495139686315409L;

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
	 * версия измерений.
	 *
	 * определяет начало нового цикла измерений или продолжение старого.
	 */
//	private Version version;

	/**
	 * описание измерения.
	 *
	 * сюда записываются данные пользователем, затем они парсятся и заносятся в
	 * БД.
	 */
	private String description;

	/**
	 * являются ли испытания повторными.
	 */
	private boolean repeated;

	/**
	 * отображать ли диалог добавления новой модели.
	 */
	private boolean showNewModelDialog;

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


	/**
	 * конструктор по умолчанию.
	 */
	public EditFormDataJournalView() {
		model = null;
		spectrumParameter = new SpectrumParameter();
		repeated = false;
		showNewModelDialog = false;
//		version = null;
	}

	/*
	 * геттеры и сеттеры.
	 */
	public SpectrumParameter getSpectrumParameter() {
		return spectrumParameter;
	}

	public ModelOfEquipment getModel() {
		return model;
	}

	public void setModel(final ModelOfEquipment model) {
		this.model = model;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(final String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public void setSpectrumParameter(final SpectrumParameter spectrParameter) {
		this.spectrumParameter = spectrParameter;
	}

	public PurposeOfMeasurement getPurposeOfMeasurement() {
		return purposeOfMeasurement;
	}

	public void setPurposeOfMeasurement(
			final PurposeOfMeasurement purposeOfMeasurement) {
		this.purposeOfMeasurement = purposeOfMeasurement;
	}

//	public Version getVersion() {
//		return version;
//	}
//
//	public void setVersion(final Version version) {
//		this.version = version;
//	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public boolean isRepeated() {
		return repeated;
	}

	public void setRepeated(final boolean repeated) {
		this.repeated = repeated;
	}

	public boolean isShowNewModelDialog() {
		return showNewModelDialog;
	}

	public void setShowNewModelDialog(final boolean showNewModelDialog) {
		this.showNewModelDialog = showNewModelDialog;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<ModelOfEquipment> getModelOfEquipments() {
		return modelOfEquipments;
	}

	public void setModelOfEquipments(
			final List<ModelOfEquipment> modelOfEquipments) {
		this.modelOfEquipments = modelOfEquipments;
	}

	public List<Measurand> getMeasurands() {
		return measurands;
	}

	public void setMeasurands(final List<Measurand> measurands) {
		this.measurands = measurands;
	}

	public List<ScreenResolution> getScreenResolutions() {
		return screenResolutions;
	}

	public void setScreenResolutions(
			final List<ScreenResolution> screenResolutions) {
		this.screenResolutions = screenResolutions;
	}

	public List<TypeOfSpectrum> getTypeOfSpectrums() {
		return typeOfSpectrums;
	}

	public void setTypeOfSpectrums(final List<TypeOfSpectrum> typeOfSpectrums) {
		this.typeOfSpectrums = typeOfSpectrums;
	}

	public List<PurposeOfMeasurement> getPurposeOfMeasurements() {
		return purposeOfMeasurements;
	}

	public void setPurposeOfMeasurements(
			final List<PurposeOfMeasurement> purposeOfMeasurements) {
		this.purposeOfMeasurements = purposeOfMeasurements;
	}

}
