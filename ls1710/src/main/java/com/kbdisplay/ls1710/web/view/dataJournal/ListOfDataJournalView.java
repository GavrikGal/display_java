package com.kbdisplay.ls1710.web.view.dataJournal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.DateOfMeasurement;
import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.Spectrum;
import com.kbdisplay.ls1710.web.view.dataJournal.component.MeasurementForView;
import com.kbdisplay.ls1710.web.view.dataJournal.component.Version;

/**
 * представление для обработки списка данных об измерениях. для обработки перед
 * выводом измерений на вэб-страницу.
 *
 * @author Gavrik
 *
 */
@ManagedBean(name = "listOfDataJournalView")
@ViewScoped
public class ListOfDataJournalView implements Serializable {

	/**
	 * серийный номер класса.
	 */
	private static final long serialVersionUID = 13968292769953764L;

	/**
	 * список обработанных для вывода в вэб-страницу измерений.
	 */
	private List<MeasurementForView> measurementForViews;
	/**
	 * выделеная строка данных об измерениях.
	 */
	private MeasurementForView selectedMeasurementForView;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public final List<MeasurementForView> getMeasurementForViews() {
		return measurementForViews;
	}

	public final void setMeasurementForViews(
			final List<MeasurementForView> measurementForViews) {
		this.measurementForViews = measurementForViews;
	}

	public final MeasurementForView getSelectedMeasurementForView() {
		return selectedMeasurementForView;
	}

	public final void setSelectedMeasurementForView(
			final MeasurementForView selectedMeasurementForView) {
		this.selectedMeasurementForView = selectedMeasurementForView;
	}

	/**
	 * устанавливает список измерений для вывода по списку измерений из БД.
	 *
	 * @param measurements
	 *            список измерений из БД
	 */
	public final void setMeasurementForViewsFromMeasurements(
			final List<Measurement> measurements) {
		measurementForViews = new ArrayList<MeasurementForView>();

		Long id = 0L;
		for (Measurement measurement : measurements) {
			addMeasurement(measurement, id);
			id++;
		}
	}

	/**
	 * добавляет измерение к текущему списку измерений.
	 *
	 * @param measurement
	 *            добавляемое измерение
	 * @param id
	 *            id измерения в списке
	 */
	public final void addMeasurement(final Measurement measurement,
			final Long id) {

		/* проверка версии измерения */
		Version versionMeas = new Version(measurement.getVersion());
		int secondParamOfVersion = versionMeas.getPart(1);
		if (secondParamOfVersion == 0) {
			/*
			 * если второй параметр версии равен нулю, то это значит, что
			 * текущее измерение является первым измерением из цикла всех
			 * измерений изделия, он должен отображаться вы журнале как
			 * отдельная строка данных. поэтому добавляем измерение в список
			 * измерений
			 */
			MeasurementForView measurementsForView = new MeasurementForView();

			measurementsForView.setId(id);
			measurementsForView.setFirstDateOfMeasurement(measurement
					.getDateOfMeasurement());
			measurementsForView.setEquipment(measurement.getEquipment());
			measurementsForView.setUser(measurement.getUser());

			/*
			 * установка измерений связанных одним циклом. номер цикла - это
			 * номер первого элемента в версии измерений.
			 */
			List<Measurement> linkedMeasurements =
					getLinkedMeasurements(versionMeas, measurement);
			measurementsForView.setMeasurements(linkedMeasurements);

			/* установка актуальных спектров из цикла измерений */
			List<Spectrum> lastSpectrums =
					getActualSpectrums(measurementsForView.getMeasurements());
			measurementsForView.setLastSpectrums(lastSpectrums);

			/* установка даты последнего измерения из цикла */
			DateOfMeasurement lastDateOfMeasurement =
					getLastDateOfMeasurement(measurementsForView
							.getMeasurements());
			measurementsForView.setLastDateOfMeasurement(lastDateOfMeasurement);

			/*
			 * добавление подготовленного для отображения измерения к списку
			 * всех подготовленных измерений.
			 */
			measurementForViews.add(measurementsForView);
		}
	}

	/**
	 * удаление измерения из списка измерений.
	 */
	public final void deleteMeasurement() {
		measurementForViews.remove(selectedMeasurementForView);
		selectedMeasurementForView = null;
	}

	/**
	 * метод позволяет получить только измерения, связанные с текущим циклом
	 * (версией) измерений.
	 *
	 * Из всех измерений, которые проводились с изделием, выбираются только те
	 * измерения версии которых входят в один цикл измерений.
	 *
	 * @param version
	 *            версия цикла-измерения
	 * @param measurement
	 *            измерение, к которому ищем связанные измерения
	 * @return список измерений, связанных с указанным циклом измерений
	 */
	private List<Measurement> getLinkedMeasurements(final Version version,
			final Measurement measurement) {
		Equipment equipment = measurement.getEquipment();
		List<Measurement> listOfMeasurementsForEquip =
				Lists.newArrayList(equipment.getMeasurements());
		List<Measurement> removingMeasurements = new ArrayList<Measurement>();
		int firstParamOfVersion = version.getPart(0);
		for (Measurement measurementEquip : listOfMeasurementsForEquip) {
			/*
			 * проверяем версию измерения, измерения должны быть одного цикла,
			 * т.е. оставляем только измерения, первая цифра которого в версии
			 * соответсвует версии обрабатываемого измерения
			 */
			Version versionEq = new Version(measurementEquip.getVersion());
			int firstParamOfVersionEq = versionEq.getPart(0);
			if (firstParamOfVersionEq != firstParamOfVersion) {
				/*
				 * измерения не относящиеся к текущему циклу измерений заносятся
				 * в список удаляемых измерений
				 */
				removingMeasurements.add(measurementEquip);
			}
		}
		listOfMeasurementsForEquip.removeAll(removingMeasurements);
		return listOfMeasurementsForEquip;
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
	private List<Spectrum> getActualSpectrums(
			final List<Measurement> measurements) {
		List<Spectrum> currentSpectrums = new ArrayList<Spectrum>();
		for (Measurement measurement : measurements) {
			List<Spectrum> spectrums = measurement.getSpectrums();

			if (currentSpectrums.isEmpty()) {
				currentSpectrums = spectrums;
			} else {
				/*
				 * поиск и замена более старых спектров на новые. в качестве
				 * критерия используется дата создания спектра, т.е. спектр с
				 * более поздней датой актуальнее.
				 */
				for (Spectrum spectrum : spectrums) {
					int found = 0;
					for (Spectrum currentSpectrum : currentSpectrums) {
						if (spectrum.getSpectrumParameters() == currentSpectrum
								.getSpectrumParameters()) {
							if (currentSpectrum.getDateTime().isBefore(
									spectrum.getDateTime())) {
								currentSpectrums.remove(currentSpectrum);
								currentSpectrums.add(spectrum);
								found = 1;
								break;
							}
							found = 1;
						}
					}
					if (found == 0) {
						currentSpectrums.add(spectrum);
					}
				}
			}
		}
		return currentSpectrums;
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
	private DateOfMeasurement getLastDateOfMeasurement(
			final List<Measurement> measurements) {
		if (measurements.size() > 1) {
			DateOfMeasurement lastDateOfMeasurement = null;

			for (Measurement measurement : measurements) {
				DateOfMeasurement checkingDate =
						measurement.getDateOfMeasurement();
				if (lastDateOfMeasurement == null) {
					lastDateOfMeasurement = checkingDate;
					continue;
				}
				if (lastDateOfMeasurement.getDate().isBefore(
						checkingDate.getDate())) {
					lastDateOfMeasurement = checkingDate;
				}
			}
			return lastDateOfMeasurement;
		}
		return null;
	}

}
