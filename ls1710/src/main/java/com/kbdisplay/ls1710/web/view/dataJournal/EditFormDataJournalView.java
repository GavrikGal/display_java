package com.kbdisplay.ls1710.web.view.dataJournal;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.joda.time.DateTime;

import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.domain.Spectrum;
import com.kbdisplay.ls1710.domain.User;
import com.kbdisplay.ls1710.web.view.dataJournal.component.Version;

/**
 * представление формы для добавления/редактирования списка измерений.
 *
 * @author Gavrik
 *
 */
@ManagedBean(name = "editFormDataJournalView")
@ViewScoped
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


	// @ManagedProperty(value="#{modelService}")
	// private ModelService modelService;

	/**
	 * конструктор по умолчанию.
	 */
	public EditFormDataJournalView() {

		// modelOfEquipments = new ArrayList<ModelOfEquipment>();
		equipment = new Equipment();
		spectrum = new Spectrum();
		user = null;
		version = null;
	}

	// @PostConstruct
	// public void init() {
	// try {
	// List<ModelOfEquipment> models = modelService.findAll();
	// if (models != null) {
	// System.out.println("models is good:");
	// for (ModelOfEquipment modelOfEquipment : models) {
	// System.out
	// .println("  - " + modelOfEquipment.getModelName());
	// }
	// modelOfEquipments = models;
	// } else {
	// System.out.println("Bce o4eHb nJloxo (((");
	// }
	// } catch (Exception e) {
	// System.out.println("Bce ewe xy}l{e");
	// e.printStackTrace();
	// }
	// }

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
		return description;
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

	// public ModelService getModelService() {
	// return modelService;
	// }
	//
	// public void setModelService(final ModelService modelService) {
	// this.modelService = modelService;
	// }

	public void save(final ActionEvent actionEvent)  {

		System.out.println("        HopM    ");
		System.out.println(equipment.getSerialNumber());
		System.out.println(description);
		description = null;
	}

}
