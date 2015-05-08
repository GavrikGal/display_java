package com.kbdisplay.ls1710.view.dataJournal;

import java.util.List;

import com.kbdisplay.ls1710.domain.DateOfMeasurement;
import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.PurposeOfMeasurement;
import com.kbdisplay.ls1710.domain.Spectrum;
import com.kbdisplay.ls1710.domain.User;

/**
 * интерфейс строки таблицы данных проведенных измерений/испытаний.
 *
 * @author Gavrik
 *
 */
public interface Row {

	/**
	 * инициализация строки таблицы данных.
	 *
	 * @param id
	 *            - текущий id строки.
	 * @param measurement
	 *            - текущее измерение.
	 */
	void init(Long id, Measurement measurement);

	/**
	 * получение id строки.
	 *
	 * @return - id строки
	 */
	Long getId();

	/**
	 * получение даты первого измерения с любой целью.
	 *
	 * @return - дата измерений.
	 */
	DateOfMeasurement getFirstDate();

	/**
	 * получение даты последнего пси(либо аналогичного) измерения.
	 *
	 * @return - дата последнего измерения либо null
	 */
	DateOfMeasurement getLastDate();

	/**
	 * получение испытуемого изделия.
	 *
	 * @return - испытуемое изделие.
	 */
	Equipment getEquipment();

	/**
	 * получение измерения.
	 *
	 * @return - измерение.
	 */
	Measurement getMeasurement();

	/**
	 * получение цели измерений/испытаний.
	 *
	 * @return - цель измерений.
	 */
	PurposeOfMeasurement getPurpose();

	/**
	 * получение пользователя проводившего измерения.
	 *
	 * @return - пользователь.
	 */
	User getUser();

	/**
	 * установка пользователя проводившего измерения.
	 *
	 * @param user - пользователь проводивший испытания
	 */
	void setUser(final User user);

	/**
	 * получение списка спектров измерений.
	 *
	 * @return - список спектров.
	 */
	List<Spectrum> getSpectrums();

	/**
	 * отображать ли дату первого измерения. TODO переложить эту операцию на
	 * сторону клиента
	 *
	 * @return - true - отображать, false - не отображать.
	 */
	boolean isEnableFirstDate();

	/**
	 * отображать ли модель испытуемого изделия. TODO переложить эту операцию на
	 * сторону клиента
	 *
	 * @return - true - отображать, false - не отображать.
	 */
	boolean isEnableModelName();

	/**
	 * установка списка спектров измерений.
	 *
	 * @param spectrums
	 *            - устанавливаемый список спектров.
	 */
	void setSpectrums(List<Spectrum> spectrums);

	/**
	 * установка даты повторных(последних) измерений.
	 *
	 * @param lastDate
	 *            - последние измерения.
	 */
	void setLastDate(DateOfMeasurement lastDate);

	/**
	 * установка видимости даты первого измерения.
	 *
	 * @param b - true -дата будет видимой.
	 */
	void setEnableFirstDate(boolean b);

	/**
	 * установка видимости названия модели.
	 * @param b - true - название будет видимым.
	 */
	void setEnableModelName(boolean b);

}
