package com.kbdisplay.ls1710.view.dataJournal.web;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.view.dataJournal.EditData;
import com.kbdisplay.ls1710.view.dataJournal.EditForm;
import com.kbdisplay.ls1710.view.dataJournal.Row;
import com.kbdisplay.ls1710.view.dataJournal.web.component.EditFormData;

/**
 * представление формы для добавления/редактирования списка измерений.
 *
 * @author Gavrik
 *
 */
// @Component("editFormDataJournalView")
@ManagedBean
@ViewScoped
public class DataJournalEditForm implements Serializable, EditForm {

	/**
	 * серийный номер класса.
	 */
	private static final long serialVersionUID = 141495139686315409L;

	/**
	 * данные формы.
	 */
	private EditData data;

	/**
	 * отображать ли диалог добавления новой модели.
	 */
	private boolean showNewModelDialog;


	/**
	 * конструктор по умолчанию.
	 */
	public DataJournalEditForm() {
		data = new EditFormData();
		showNewModelDialog = false;
	}

	/**
	 * инициализация формы редактирования.
	 *
	 * @param lastMeasurement
	 *            - последнее известное измерение из БД.
	 */
	@Override
	public void init(final Measurement lastMeasurement) {

		data.init();

		if (lastMeasurement != null) {
			data.initSelected(lastMeasurement);

		}

	}

	/**
	 * обработчик события, когда по строке совершен двойной клик.
	 *
	 * @param event
	 *            - событие двойного клика.
	 */
	@Override
	public void onRowDbSelect(final SelectEvent event) {
		Row row = (Row) event.getObject();
		Measurement selected = row.getMeasurement();
		edit(selected);
	}

	/**
	 * установить в форме редактирования выделеное измерение.
	 *
	 * @param selected
	 *            - редактируемое измерение.
	 */
	@Override
	public void edit(final Measurement selected) {
		data.setModel(selected.getEquipment().getModel());
		data.setSerialNumber(selected.getEquipment().getSerialNumber());

		if (selected.getNextMeasurement() != null) {
			Measurement lastMeasurement = selected.getNextMeasurement();
			while (lastMeasurement.getNextMeasurement() != null) {
				lastMeasurement = lastMeasurement.getNextMeasurement();
			}
			data.setPurposeOfMeasurement(lastMeasurement.getPurpose());
		} else {
			data.setPurposeOfMeasurement(selected.getPurpose());
		}
	}

	// геттеры и сеттеры.
	@Override
	public EditData getData() {
		return data;
	}

	@Override
	public void setData(final EditData data) {
		this.data = data;
	}

	@Override
	public boolean isShowNewModelDialog() {
		return showNewModelDialog;
	}

	@Override
	public void setShowNewModelDialog(final boolean showNewModelDialog) {
		this.showNewModelDialog = showNewModelDialog;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
