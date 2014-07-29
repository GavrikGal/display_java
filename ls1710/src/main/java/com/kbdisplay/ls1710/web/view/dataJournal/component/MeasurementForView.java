package com.kbdisplay.ls1710.web.view.dataJournal.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.kbdisplay.ls1710.domain.DateOfMeasurement;
import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.Spectrum;
import com.kbdisplay.ls1710.domain.User;

/**
 * измерение, в котором данные и поля подобраны для вывода в список на
 * вэб-странице.
 *
 * @author Gavrik
 *
 */
public class MeasurementForView implements Serializable {

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
	private DateOfMeasurement firstDateOfMeasurement;
	/**
	 * дата последнего измерения.
	 */
	private DateOfMeasurement lastDateOfMeasurement;
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
	private List<Spectrum> lastSpectrums;
	/**
	 * измерения, связанные с данным изделием одним циклом.
	 *
	 * сюда входят повторные измерения, такие как пи и пси. но сюда не будут
	 * входить новые измерения одного и того же изделия, например когда изделие
	 * второй раз приходит на пи.
	 */
	private List<Measurement> measurements;
	/**
	 * пользователь, проводивший измерения.
	 */
	private User user;

	/**
	 * конструктор по умолчанию инициализирует списки в полях новыми списками
	 * соответствующего типа.
	 */
	public MeasurementForView() {
		lastSpectrums = new ArrayList<Spectrum>();
		measurements = new ArrayList<Measurement>();
	}

	public final Long getId() {
		return id;
	}

	public final void setId(final Long id) {
		this.id = id;
	}

	public final DateOfMeasurement getFirstDateOfMeasurement() {
		return firstDateOfMeasurement;
	}

	public final void setFirstDateOfMeasurement(
			final DateOfMeasurement firstDateOfMeasurement) {
		this.firstDateOfMeasurement = firstDateOfMeasurement;
	}

	public final DateOfMeasurement getLastDateOfMeasurement() {
		return lastDateOfMeasurement;
	}

	public final void setLastDateOfMeasurement(
			final DateOfMeasurement lastDateOfMeasurement) {
		this.lastDateOfMeasurement = lastDateOfMeasurement;
	}

	public final Equipment getEquipment() {
		return equipment;
	}

	public final void setEquipment(final Equipment equipment) {
		this.equipment = equipment;
	}

	public final List<Spectrum> getLastSpectrums() {
		return lastSpectrums;
	}

	public final void setLastSpectrums(final List<Spectrum> lastSpectrums) {
		this.lastSpectrums = lastSpectrums;
	}

	public final List<Measurement> getMeasurements() {
		return measurements;
	}

	public final void setMeasurements(final List<Measurement> measurements) {
		this.measurements = measurements;
	}

	public final User getUser() {
		return user;
	}

	public final void setUser(final User user) {
		this.user = user;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
