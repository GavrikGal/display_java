package com.kbdisplay.ls1710.view.dataJournal;

import java.util.List;

import com.kbdisplay.ls1710.domain.Measurement;

/**
 * интерфейс таблицы данных проведенных измерений/испытаний.
 *
 * @author Gavrik
 *
 */
public interface DataTable {

	/**
	 * инициализация таблицы данных.
	 *
	 * @param measurements
	 *            - список измерений для внесения в таблицу при инициализации.
	 */
	void init(final List<Measurement> measurements);

	/**
	 * добавляет данные проведенного измерения в таблицу.
	 *
	 * @param measurement
	 *            - добавляемое измерение.
	 */
	void add(final Measurement measurement);

	//TODO добавить метод получения выбранной строки данных.

	/**
	 * удалить выбранные(выделеные в таблице) данные.
	 */
	void deleteSelected();

}
