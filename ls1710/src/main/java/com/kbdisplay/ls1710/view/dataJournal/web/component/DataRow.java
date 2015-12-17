package com.kbdisplay.ls1710.view.dataJournal.web.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

	private boolean enableMaxSpectrum;

	/**
	 * инициализирует строку таблицы данных соответствующими значениями.
	 */
	@Override
	public void init(final Long id, final Measurement measurement) {
		this.id = id;
		/* установка максимальных спектров из цикла измерений */
		List<Spectrum> lastSpectrums = this.getMaxSpectrums(measurement);
		this.spectrums = lastSpectrums;
		this.enableMaxSpectrum = true;
		this.firstDate = measurement.getDate();
		this.equipment = measurement.getEquipment();
		this.user = lastSpectrums.get(lastSpectrums.size() - 1).getMeasurement().getUser();
		this.measurement = measurement;
		/* установка даты последнего измерения из цикла */
		DateOfMeasurement lastDate = this.getLastDate(measurement);
		this.lastDate = lastDate;

	}

	/**
	 * обновляет сторку таблицы данных новыми данными, при добавлении нового
	 * измерения.
	 */
	@Override
	public void update(Measurement newMeasurement) {
		// TODO кастыль - исправить нахрен
		Measurement rootMeasurement = newMeasurement;
		while (rootMeasurement.getParentMeasurement() != null) {
			rootMeasurement = rootMeasurement.getParentMeasurement();
		}
		// кастыль

		List<Spectrum> lastSpectrums = this.getMaxSpectrums(rootMeasurement);

		this.spectrums = lastSpectrums;
		this.user = lastSpectrums.get(lastSpectrums.size() - 1).getMeasurement().getUser();

		if (newMeasurement.getParentMeasurement() != null) {
			this.lastDate = newMeasurement.getDate();
		}
	};

	@Override
	public void toggleMaxOrAllSpectrum() {
		List<Spectrum> lastSpectrums = null;
		if (enableMaxSpectrum) {
			this.enableMaxSpectrum = false;
			lastSpectrums = this.getActualSpectrums(this.measurement);
		} else {
			this.enableMaxSpectrum = true;
			lastSpectrums = this.getMaxSpectrums(this.measurement);
		}
		this.spectrums = lastSpectrums;
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

	@Override
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

	private List<Spectrum> getMaxSpectrums(final Measurement measurement) {
		List<Spectrum> maxSpectrums = this.getActualSpectrums(measurement);

		if (maxSpectrums.size() > 2) {
			while (maxSpectrums.size() > 2) {
				maxSpectrums.remove(2);
			}

		}

		return maxSpectrums;
	}

	/**
	 * выбор актуальных спектров из цикла измерений.
	 *
	 * в списке связанных одним циклом измерений выбираются спектры, чтобы
	 * представить максимально большее число спектров с различными параметрами.
	 * Из спектров с одинаковыми параметрами выбираются те, которые были
	 * измерены позже, т.е. являются более актуальными.
	 *
	 * @param measurements
	 *            список связанных одним циклом измерений
	 * @return актуальные спектры из цикла измерений
	 */
	private List<Spectrum> getActualSpectrums(final Measurement measurement) {

		Map<Long, Spectrum> actualSpectrumMap = this.getActualSpectrumMap(new HashMap<Long, Spectrum>(), measurement);
		List<Spectrum> actualSpectrums = new ArrayList<Spectrum>();
		for (Entry<Long, Spectrum> spectrum : actualSpectrumMap.entrySet()) {
			actualSpectrums.add(spectrum.getValue());
		}

		return actualSpectrums;
	}

	private Map<Long, Spectrum> getActualSpectrumMap(final Map<Long, Spectrum> prevActualMap,
			final Measurement measurement) {
		Map<Long, Spectrum> actualMap = new HashMap<Long, Spectrum>();
		if (measurement != null) {

			List<Spectrum> spectrums = measurement.getSpectrums();

			if (!spectrums.isEmpty()) {
				/*
				 * поиск и замена более старых спектров на новые. в качестве
				 * критерия используется версия спектра, т.е. спектр с большей
				 * версией актуальнее.
				 */
				for (Spectrum spectrum : spectrums) {

					Long parameterId = spectrum.getParameters().get(spectrum.getParameters().size() - 1).getId();
					if (!actualMap.containsKey(parameterId)) {
						actualMap.put(parameterId, spectrum);
					} else {
						int actualVersion = actualMap.get(parameterId).getVersion();
						int currentVersion = spectrum.getVersion();
						if (actualVersion < currentVersion) {
							actualMap.put(parameterId, spectrum);
						}

					}
				}

				for (Entry<Long, Spectrum> actualSpectrum : actualMap.entrySet()) {
					prevActualMap.put(actualSpectrum.getKey(), actualSpectrum.getValue());
				}

				actualMap = prevActualMap;

			}
			Measurement nextMeasurement = measurement.getNextMeasurement();
			if (nextMeasurement != null) {
				actualMap = this.getActualSpectrumMap(prevActualMap, nextMeasurement);
			}
		}

		return actualMap;
	}

	/**
	 * выбор даты последнего измерения из цикла измерений.
	 *
	 * если цикл состоит из одного измерения, то это значит, что последующие
	 * измерения не проводились. в этом случае возвращается значение null.
	 *
	 * @param measurements
	 *            цикл связанных измерений
	 * @return дата последнего из цикла измерения
	 */
	private DateOfMeasurement getLastDate(final Measurement measurement) {
		Measurement lastMeasurement = null;
		if (measurement.getNextMeasurement() != null) {
			lastMeasurement = measurement;
			while (lastMeasurement.getNextMeasurement() != null) {
				lastMeasurement = lastMeasurement.getNextMeasurement();
			}

			return lastMeasurement.getDate();
		}
		return null;
	}

}
