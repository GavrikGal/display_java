package com.kbdisplay.ls1710.web.view.dataJournal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.joda.time.DateTime;

import com.google.common.collect.Lists;
import com.kbdisplay.ls1710.domain.DateOfMeasurement;
import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.Spectrum;
import com.kbdisplay.ls1710.web.view.dataJournal.component.MeasurementForView;

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
	 * список отфильтрованных из общего представления измерений.
	 */
	private List<MeasurementForView> filteredMeasurementForViews;
	/**
	 * выделеная строка данных об измерениях.
	 */
	private MeasurementForView selectedMeasurementForView;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public final List<MeasurementForView> getMeasurementForViews() {
		return setVisibleFields(measurementForViews);
		//TODO переложить эту часть на javascript
		//return measurementForViews;
	}

	public final void setMeasurementForViews(
			final List<MeasurementForView> measurementForViews) {
		this.measurementForViews = measurementForViews;
	}

	public List<MeasurementForView> getFilteredMeasurementForViews() {
		return setVisibleFields(filteredMeasurementForViews);
		//TODO переложить эту часть на javascript
		//return filteredMeasurementForViews;
	}

	public void setFilteredMeasurementForViews(
			final List<MeasurementForView> filteredMeasurementForViews) {
		this.filteredMeasurementForViews = filteredMeasurementForViews;
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
		// Version versionMeas = new Version(measurement.getVersion());
		// int secondParamOfVersion = versionMeas.getPart(1);

		/*
		 * проверка являются ли измерения начальными в серии испытаний например
		 * начальными являются испытания с версией = 1
		 */
		if (measurement.getVersion() == 1) {

			MeasurementForView measurementsForView = new MeasurementForView();

			measurementsForView.setId(id);
			measurementsForView.setFirstDateOfMeasurement(measurement
					.getDate());
			measurementsForView.setEquipment(measurement.getEquipment());
			measurementsForView.setUser(measurement.getUser());

			/*
			 * установка измерений связанных с данным изделием.
			 */
			List<Measurement> linkedMeasurements = new ArrayList<Measurement>();
			linkedMeasurements.add(measurement);
//TODO переделать			getLinkedMeasurements(/* versionMeas, */measurement);
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
	// TODO переделать и периименовать
	private List<Measurement> getLinkedMeasurements(/* final Version version, */
	final Measurement measurement) {
		Equipment equipment = measurement.getEquipment();
		List<Measurement> listOfMeasurementsForEquip =
				Lists.newArrayList(equipment.getMeasurements());
		// List<Measurement> removingMeasurements = new
		// ArrayList<Measurement>();
		// int firstParamOfVersion = version.getPart(0);
		// for (Measurement measurementEquip : listOfMeasurementsForEquip) {
		// /*
		// * проверяем версию измерения, измерения должны быть одного цикла,
		// * т.е. оставляем только измерения, первая цифра которого в версии
		// * соответсвует версии обрабатываемого измерения
		// */
		// Version versionEq = new Version(measurementEquip.getVersion());
		// int firstParamOfVersionEq = versionEq.getPart(0);
		// if (firstParamOfVersionEq != firstParamOfVersion) {
		// /*
		// * измерения не относящиеся к текущему циклу измерений заносятся
		// * в список удаляемых измерений
		// */
		// removingMeasurements.add(measurementEquip);
		// }
		// }
		// listOfMeasurementsForEquip.removeAll(removingMeasurements);
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
						if (spectrum.getParameter() == currentSpectrum
								.getParameter()) {
							if (currentSpectrum.getDate().isBefore(
									spectrum.getDate())) {
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
				/*
				 * установка даты повторных измерений требуется только для
				 * испытаний, которым это требуется. например после приемочных
				 * испытаний проходят приемосдаточные испытания. проверяем что у
				 * приемосдаточных испытаний имеются предшевствующие испытания,
				 * и тогда устанавливаем последнюю дату этих испытаний
				 */
				if (measurement.getPurpose().getPrevPurpose() != null) {
					DateOfMeasurement checkingDate =
							measurement.getDate();
					if (lastDateOfMeasurement == null) {
						lastDateOfMeasurement = checkingDate;
						continue;
					}
					if (lastDateOfMeasurement.getDate().isBefore(
							checkingDate.getDate())) {
						lastDateOfMeasurement = checkingDate;
					}
				}
			}
			return lastDateOfMeasurement;
		}
		return null;
	}

	/**
	 * делает неотображаемыми повторяющиеся элементы даты и модели.
	 *
	 * запускать после сортировки данных.
	 *
	 * @param measurementForViews
	 *            список отсортированных измерений
	 * @return список измерений, с убранными повторяющимися датами и моделями.
	 *
	 * @deprecated
	 */
	@Deprecated
	private List<MeasurementForView> setVisibleFields(
			final List<MeasurementForView> measurementForViews) {

		if (measurementForViews == null) {
			return measurementForViews;
		}

		if (measurementForViews.isEmpty()) {
			return measurementForViews;
		}

		DateTime currentDate = null;
		DateTime preDate = null;
		String currentModelName = "";
		String preModelName = "";

		MeasurementForView currentMeasForView = null;
		MeasurementForView preMeasForView = null;

		/*
		 * идут ли измерения в списке по возрастанию или же убыванию.
		 */
		boolean isIncrease = isIncrease(measurementForViews);

		for (int i = 0; i < measurementForViews.size(); i++) {

			currentMeasForView = measurementForViews.get(i);
			currentDate =
					currentMeasForView.getFirstDateOfMeasurement().getDate();
			currentModelName =
					currentMeasForView.getEquipment().getModel().getName();

			currentMeasForView.setEnableFirstDate(true);
			currentMeasForView.setEnableModelName(true);
			if (i > 0) {
				preMeasForView = measurementForViews.get(i - 1);
				preDate = preMeasForView.getFirstDateOfMeasurement().getDate();
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

		return measurementForViews;
	}

	/**
	 * предворительная проверка, идут ли измерения в списке по возрастанию или
	 * же убыванию.
	 *
	 * @param list
	 *            список проверяемых измерений
	 * @return true если значения в списке идут по возрастанию, false - если по
	 *         убыванию
	 *
	 * @deprecated
	 */
	@Deprecated
	private boolean isIncrease(final List<MeasurementForView> list) {
		if (list == null) {
			return false;
		}
		if (list.isEmpty()) {
			return false;
		}
		boolean isIncrease = false;
		MeasurementForView firstMeas = list.get(0);
		MeasurementForView currentMeas;
		for (MeasurementForView measurementForView : measurementForViews) {
			currentMeas = measurementForView;
			DateTime firstDate =
					firstMeas.getFirstDateOfMeasurement().getDate();
			DateTime currentDate =
					currentMeas.getFirstDateOfMeasurement().getDate();
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
}
