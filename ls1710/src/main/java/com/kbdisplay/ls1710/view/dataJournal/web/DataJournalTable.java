package com.kbdisplay.ls1710.view.dataJournal.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.joda.time.DateTime;

import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.view.dataJournal.DataTable;
import com.kbdisplay.ls1710.view.dataJournal.Row;
import com.kbdisplay.ls1710.view.dataJournal.web.component.DataRow;

/**
 * представление списка данных об измерениях. для обработки перед
 * выводом измерений на вэб-страницу.
 *
 * @author Gavrik
 *
 */
/**
 * @author Gavrik
 *
 */
@ManagedBean(name = "dataJournalTable")
@ViewScoped
public class DataJournalTable implements Serializable, DataTable {

	/**
	 * серийный номер класса.
	 */
	private static final long serialVersionUID = 13968292769953764L;

	/**
	 * список обработанных для вывода в вэб-страницу измерений.
	 */
	private List<Row> rows;
	/**
	 * список отфильтрованных из общего представления измерений.
	 */
	private List<Row> filtered;
	/**
	 * выделеная строка данных об измерениях.
	 */
	private Row selected;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * получение списка строк из таблицы данных измерений.
	 *
	 * @return - список строк.
	 */
	public final List<Row> getRows() {
		return setVisibleFields(rows);
		// TODO переложить эту часть на javascript
		// return rows;
	}

	public final void setRows(final List<Row> rows) {
		this.rows = rows;
	}

	/**
	 * получение списка отфильтрованных строк из таблицы измерений.
	 *
	 * @return - список отфильтрованных строк.
	 */
	public List<Row> getFiltered() {
		return setVisibleFields(filtered);
		// TODO переложить эту часть на javascript
		// return filtered;
	}

	public void setFiltered(final List<Row> filtered) {
		this.filtered = filtered;
	}

	@Override
	public final Row getSelected() {
		return selected;
	}

	public final void setSelected(final Row selected) {
		this.selected = selected;
	}

	/**
	 * устанавливает список измерений для вывода по списку измерений из БД.
	 *
	 * @param measurements
	 *            список измерений из БД
	 */
	@Override
	public final void init(final List<Measurement> measurements) {
		rows = new ArrayList<Row>();

		for (Measurement measurement : measurements) {
			this.insertRow(measurement);
		}
	}

	/**
	 * добавляет измерение к текущему списку измерений.
	 *
	 * @param measurement
	 *            добавляемое измерение
	 */
	@Override
	public final void add(final Measurement measurement) {

		Measurement rootMeasurement = measurement;
		while (rootMeasurement.getParentMeasurement() != null) {
			rootMeasurement = rootMeasurement.getParentMeasurement();
		}

		Row rowWithMeasurement = null;
		for (Row row : rows) {
			if (row.getMeasurement().getId().equals(rootMeasurement.getId())) {
				rowWithMeasurement = row;
				break;
			}
		}
		if (rowWithMeasurement == null) {
			this.insertRow(rootMeasurement);
		} else {
			this.updateRow(rowWithMeasurement, measurement);
		}
	}

	/**
	 * вставка новой строки измерения в таблицу измерений.
	 *
	 * @param measurement
	 *            - новое измерение.
	 */
	public final void insertRow(final Measurement measurement) {
		/*
		 * проверка являются ли измерения начальными в серии испытаний например
		 * если у измемения родительское измерение равно null,то измерение
		 * начальное.
		 */
		if (measurement.getParentMeasurement() == null) {

			Row row = new DataRow();
			row.init(measurement.getId(), measurement);
			rows.add(row);
		}
	}

	/**
	 * обновление строки измерения в таблице измерений с новыми данными.
	 *
	 * @param row
	 *            - обновляемая строка.
	 * @param measurement
	 *            - новое измерение.
	 */
	public final void updateRow(final Row row, final Measurement measurement) {

		// обновляем строку новыми данными.
		row.update(measurement);

		// TODO кастыль - исправить нахрен
		int index = rows.indexOf(row);
		rows.remove(row);
		rows.add(index, row);
	}

	/**
	 * удаление измерения из списка измерений.
	 */
	@Override
	public final void deleteSelected() {
		rows.remove(selected);
		selected = null;
	}


	/**
	 * делает неотображаемыми повторяющиеся элементы даты и модели.
	 *
	 * запускать после сортировки данных. TODO переложить эту функцию на
	 * javascript
	 *
	 * @param rows
	 *            список отсортированных измерений
	 * @return список измерений, с убранными повторяющимися датами и моделями.
	 *
	 * @deprecated
	 */
	@Deprecated
	private List<Row> setVisibleFields(final List<Row> dataRows) {

		if (dataRows == null) {
			return dataRows;
		}

		if (dataRows.isEmpty()) {
			return dataRows;
		}

		DateTime currentDate = null;
		DateTime preDate = null;
		String currentModelName = "";
		String preModelName = "";

		Row currentMeasForView = null;
		Row preMeasForView = null;

		/*
		 * идут ли измерения в списке по возрастанию или же убыванию.
		 */
		boolean isIncrease = isIncrease(dataRows);

		for (int i = 0; i < dataRows.size(); i++) {

			currentMeasForView = dataRows.get(i);
			currentDate = currentMeasForView.getFirstDate().getDate();
			currentModelName =
					currentMeasForView.getEquipment().getModel().getName();

			currentMeasForView.setEnableFirstDate(true);
			currentMeasForView.setEnableModelName(true);
			if (i > 0) {
				preMeasForView = dataRows.get(i - 1);
				preDate = preMeasForView.getFirstDate().getDate();
				preModelName =
						preMeasForView.getEquipment().getModel().getName();
			} else {
				preMeasForView = currentMeasForView;
				continue;
			}
			if (preModelName == currentModelName) {
				if (isIncrease) {
					preMeasForView.setEnableModelName(false);
				} else {
					currentMeasForView.setEnableModelName(false);
				}
			}

			if (preDate == currentDate) {
				if (isIncrease) {
					preMeasForView.setEnableFirstDate(false);
				} else {
					currentMeasForView.setEnableFirstDate(false);
				}
			}

			if (preDate != currentDate) {
				if (isIncrease) {
					preMeasForView.setEnableModelName(true);
				} else {
					currentMeasForView.setEnableModelName(true);
				}
			}

		}

		return dataRows;
	}

	/**
	 * предворительная проверка, идут ли измерения в списке по возрастанию или
	 * же убыванию.
	 *
	 * @param datRows
	 *            список проверяемых измерений
	 * @return true если значения в списке идут по возрастанию, false - если по
	 *         убыванию
	 *
	 * @deprecated
	 */
	@Deprecated
	private boolean isIncrease(final List<Row> datRows) {
		if (datRows == null) {
			return false;
		}
		if (datRows.isEmpty()) {
			return false;
		}
		boolean isIncrease = false;
		Row firstMeas = datRows.get(0);
		Row currentMeas;
		for (Row measurementForView : rows) {
			currentMeas = measurementForView;
			DateTime firstDate = firstMeas.getFirstDate().getDate();
			DateTime currentDate = currentMeas.getFirstDate().getDate();
			if (firstDate.isEqual(currentDate)) {
				continue;
			}
			if (firstDate.isBefore(currentDate)) {
				isIncrease = true;
				break;
			}
			if (firstDate.isAfter(currentDate)) {
				isIncrease = false;
				break;
			}
		}
		return isIncrease;
	}

	@Override
	public void loadMoreData() {
		selected.toggleMaxOrAllSpectrum();
	}
}
