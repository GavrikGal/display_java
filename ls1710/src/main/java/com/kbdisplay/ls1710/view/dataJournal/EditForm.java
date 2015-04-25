package com.kbdisplay.ls1710.view.dataJournal;

import org.primefaces.event.SelectEvent;

import com.kbdisplay.ls1710.domain.Measurement;

public interface EditForm {

	/**
	 * инициализация формы редактирования.
	 *
	 * @param lastMeasurement
	 *            - последнее известное измерение из БД.
	 */
	void init(final Measurement lastMeasurement);

	/**
	 * обработчик события, когда по строке совершен двойной клик.
	 *
	 * @param event
	 *            - событие двойного клика.
	 */
	void onRowDbSelect(final SelectEvent event);

	/**
	 * установить в форме редактирования выделеное измерение.
	 *
	 * @param selected
	 *            - редактируемое измерение.
	 */
	void edit(final Measurement selected);

	/**
	 * выбрать тип параметра из меню доступных типов.
	 *
	 * @param itemN
	 *            - номер типа из меню доступных типов.
	 */
	void selectTypeOfParameter(String itemN);

	/**
	 * сохранить товый тип параметра.
	 */
	void saveTypeOfParameter();

	/**
	 * убрать список доступных типов параметров. Также удалить и следующие за
	 * ним списки, так как они теряют актуальность при удалении текущего списка.
	 *
	 * @param selectedIndex
	 *            - номер списка в коллекции всех списков доступных параметров.
	 */
	void removeSelected(int selectedIndex);

	EditData getData();

	void setData(EditData data);

	// TODO что-то надо с этим сделать, нужно ли оно тут, или переместить
	// куда-то
	boolean isShowNewModelDialog();

	void setShowNewModelDialog(final boolean showNewModelDialog);

}
