package com.kbdisplay.ls1710.web.view.dataJournal;

import java.io.Serializable;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.Measurand;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.domain.ScreenResolution;
import com.kbdisplay.ls1710.domain.Spectrum;
import com.kbdisplay.ls1710.domain.SpectrumParameter;
import com.kbdisplay.ls1710.domain.TypeOfSpectrum;
import com.kbdisplay.ls1710.domain.User;
import com.kbdisplay.ls1710.web.view.dataJournal.component.Version;

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
	 * поле даты измерений.
	 */
	private DateTime dateTime;
	/**
	 * испытуемое изделие.
	 *
	 * в форме используются название модели и серийный номер изделия, которые
	 * содержит объект изделия.
	 */
	private Equipment equipment;
	/**
	 * измеренный спектры, и параметры спектра, такие как разрешение, измеряемая
	 * величина и т.д.
	 */
	private Spectrum spectrum;
	/**
	 * параметры измеренного спектра.
	 *
	 * такие как измеряемая величина, разрешение экрана, тип измерений и т.д.
	 */
	private SpectrumParameter spectrumParameter;
	/**
	 * пользователь, проводивший измерения.
	 */
	private User user;
	/**
	 * версия измерений.
	 *
	 * определяет начало нового цикла измерений или продолжение старого.
	 */
	private Version version;
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
	 * конструктор по умолчанию.
	 */
	public EditFormDataJournalView() {

		equipment = new Equipment();
		spectrum = new Spectrum();
		spectrumParameter = new SpectrumParameter();



		user = null;
		version = null;
	}




	/*
	 * геттеры и сеттеры.
	 */
	public DateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(final DateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(final Equipment equipment) {
		this.equipment = equipment;
	}

	public Spectrum getSpectrum() {
		return spectrum;
	}

	public void setSpectrum(final Spectrum spectrum) {
		this.spectrum = spectrum;
	}

	public SpectrumParameter getSpectrumParameter() {
		return spectrumParameter;
	}

	public void setSpectrumParameter(final SpectrumParameter spectrParameter) {
		this.spectrumParameter = spectrParameter;
	}

	public User getUser() {
		return user;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(final Version version) {
		this.version = version;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
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



}
