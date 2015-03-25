package com.kbdisplay.ls1710.view.dataJournal.web.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.kbdisplay.ls1710.domain.DateOfMeasurement;
import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.PurposeOfMeasurement;
import com.kbdisplay.ls1710.domain.Spectrum;
import com.kbdisplay.ls1710.domain.User;
import com.kbdisplay.ls1710.view.dataJournal.Row;

/**
 * измерение, в котором данные и поля подобраны для вывода в список на
 * вэб-странице.
 *
 * @author Gavrik
 *
 */
public class DataRow implements Serializable, Row {

	/**
	 * серийный номер класса.
	 */
	private static final long serialVersionUID = 4304930418756743153L;

	/**
	 * ID измерения.
	 */
	private Long id;

	/**
	 * дата первого измерения.
	 */
	private DateOfMeasurement firstDate;

	/**
	 * дата последнего измерения.
	 */
	private DateOfMeasurement lastDate;

	/**
	 * испытуемое изделие.
	 */
	private Equipment equipment;

	/**
	 * последние результирующие спектры измеренных частот и амплитуд.
	 *
	 * выбираются последние актуальные спектры.
	 *
	 */
	private List<Spectrum> spectrums;

	/**
	 * измерения, связанные с данным изделием одним циклом.
	 *
	 * сюда входят повторные измерения, такие как пи и пси. но сюда не будут
	 * входить новые измерения одного и того же изделия, например когда изделие
	 * второй раз приходит на пи.
	 */
	private Measurement measurement;

	/**
	 * пользователь, проводивший измерения.
	 */
	private User user;

	/**
	 * включить/выключить отображение даты первого измерения.
	 */
	private boolean enableFirstDate;

	/**
	 * включить/выключить отображение модели изделия.
	 */
	private boolean enableModelName;


	/**
	 * инициализирует строку таблицы данных соответствующими значениями.
	 */
	@Override
	public void init(final Long id, final Measurement measurement) {
		this.id = id;
		this.spectrums = new ArrayList<Spectrum>();
		this.firstDate = measurement.getDate();
		this.equipment = measurement.getEquipment();
		this.user = measurement.getUser();
		this.measurement = measurement;
	}

	@Override
	public final Long getId() {
		return id;
	}

	public final void setId(final Long id) {
		this.id = id;
	}

	@Override
	public final DateOfMeasurement getFirstDate() {
		return firstDate;
	}

	public final void setFirstDate(final DateOfMeasurement firstDate) {
		this.firstDate = firstDate;
	}

	@Override
	public final DateOfMeasurement getLastDate() {
		return lastDate;
	}

	@Override
	public final void setLastDate(final DateOfMeasurement lastDate) {
		this.lastDate = lastDate;
	}

	@Override
	public final Equipment getEquipment() {
		return equipment;
	}

	public final void setEquipment(final Equipment equipment) {
		this.equipment = equipment;
	}

	@Override
	public final List<Spectrum> getSpectrums() {
		return spectrums;
	}

	@Override
	public final void setSpectrums(final List<Spectrum> spectrums) {
		this.spectrums = spectrums;
	}

	@Override
	public final Measurement getMeasurement() {
		return measurement;
	}

	public final void setMeasurement(final Measurement measurement) {
		this.measurement = measurement;
	}

	@Override
	public final User getUser() {
		return user;
	}

	public final void setUser(final User user) {
		this.user = user;
	}

	@Override
	public final PurposeOfMeasurement getPurpose() {
		return measurement.getPurpose();
	}

	@Override
	public boolean isEnableFirstDate() {
		return enableFirstDate;
	}

	@Override
	public void setEnableFirstDate(final boolean enableFirstDate) {
		this.enableFirstDate = enableFirstDate;
	}

	@Override
	public boolean isEnableModelName() {
		return enableModelName;
	}

	@Override
	public void setEnableModelName(final boolean enableModelName) {
		this.enableModelName = enableModelName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
