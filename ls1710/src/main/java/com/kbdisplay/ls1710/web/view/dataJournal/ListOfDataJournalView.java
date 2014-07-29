package com.kbdisplay.ls1710.web.view.dataJournal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.DateOfMeasurement;
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


	// DateOfMeasurement currentMeasurementDate;

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

		// проверка версии измерения
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
			// TODO сделать функцию добавления
			MeasurementForView measurementsForView = new MeasurementForView();

			measurementsForView.setId(id);

			measurementsForView.setFirstDateOfMeasurement(measurement
					.getDateOfMeasurement());

			measurementsForView.setEquipment(measurement.getEquipment());

			List<Measurement> listOfMeasurementsForEquip =
					Lists.newArrayList(measurement.getEquipment()
							.getMeasurements());
			List<Measurement> removingMeasurements =
					new ArrayList<Measurement>();
			int firstParamOfVersion = versionMeas.getPart(0);
			for (Measurement measurementEquip : listOfMeasurementsForEquip) {
				/*
				 * проверяем версию измерения, измерения должны быть одного
				 * цикла, т.е. оставляем только измерения, первая цифра которого
				 * в версии соответсвует версии обрабатываемого измерения
				 */
				Version versionEq = new Version(measurementEquip.getVersion());
				int firstParamOfVersionEq = versionEq.getPart(0);
				if (firstParamOfVersionEq != firstParamOfVersion) {
					removingMeasurements.add(measurementEquip);
				}
			}
			listOfMeasurementsForEquip.removeAll(removingMeasurements);
			measurementsForView.setMeasurements(listOfMeasurementsForEquip);

			measurementsForView.setUser(measurement.getUser());
			/*
			 * Установка актуальных спектров
			 */
			List<Spectrum> currentSpectrums =
					measurementsForView.getLastSpectrums();
			for (Measurement measurement2 : listOfMeasurementsForEquip) {
				List<Spectrum> spectrums = measurement2.getSpectrums();

				if (currentSpectrums.isEmpty()) {
					currentSpectrums = spectrums;
				} else {
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
			measurementsForView.setLastSpectrums(currentSpectrums);

			// установка даты последнего измерения
			if (measurementsForView.getMeasurements().size() > 1) {
				List<Measurement> measurements =
						measurementsForView.getMeasurements();
				DateOfMeasurement lastDateOfMeasurement =
						measurementsForView.getFirstDateOfMeasurement();
				for (Measurement measurement2 : measurements) {
					DateOfMeasurement checkingDate =
							measurement2.getDateOfMeasurement();
					if (lastDateOfMeasurement.getDate().isBefore(
							checkingDate.getDate())) {
						lastDateOfMeasurement = checkingDate;
					}
				}
				measurementsForView
						.setLastDateOfMeasurement(lastDateOfMeasurement);

			}

			measurementForViews.add(measurementsForView);
		}

		// if (measurementForViews.isEmpty()) {
		// currentMeasurementDate = measurement.getDateOfMeasurement();
		// measurementsForView
		// .setFirstDateOfMeasurement(currentMeasurementDate);
		//
		// }

		// if (measurementsForView.getLastSpectrums().isEmpty()) {
		// measurementsForView.setLastSpectrums(measurement.getSpectrums());
		// }
		//
		// if (measurementsForView.getMeasurements().isEmpty()) {
		// measurementsForView.getMeasurements().add(measurement);
		// measurementsForView.setId(measurement.getIdMeasurements());
		// measurementsForView.setEquipment(measurement.getEquipment());
		// measurementsForView.setUser(measurement.getUser());
		// }

		// measurementsForView.setMeasurements(measurement);
		// if (measurement.getDateOfSecondMeasurement() != null) {
		// measurementsForView.setDateOfSecondMeasurement(measurement
		// .getDateOfSecondMeasurement().getDate());
		// }

		// if (currentMeasurementDate.getDate().isBefore(
		// measurement.getDateOfMeasurement().getDate())) {
		// currentMeasurementDate = measurement.getDateOfMeasurement();
		// measurementsForView
		// .setFirstDateOfMeasurement(currentMeasurementDate);
		// measurementsForView.setModelName(currentModelName);
		// }

		// if (currentModelName != measurement.getEquipment().getModel()
		// .getModelName()) {
		// currentModelName = measurement.getEquipment().getModel()
		// .getModelName();
		// measurementsForView.setModelName(currentModelName);
		// }

	}

	/**
	 * удаление измерения из списка измерений.
	 */
	public final void deleteMeasurement() {
		measurementForViews.remove(selectedMeasurementForView);
		selectedMeasurementForView = null;
	}

}
