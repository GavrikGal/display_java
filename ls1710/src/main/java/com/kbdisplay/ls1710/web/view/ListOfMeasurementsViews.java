package com.kbdisplay.ls1710.web.view;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.kbdisplay.ls1710.domain.Measurement;

/**
 * НЕ ИСПОЛЬЗОВАТЬ, БУДЕТ УДАЛЕН!
 *
 * @deprecated весь класс
 * @author Gavrik
 *
 */
@Deprecated
public class ListOfMeasurementsViews {

	private List<MeasurementsView>	measurementsViews	= new ArrayList<MeasurementsView>();

	private String currentModelName = null;
	private DateTime currentMeasurementDate = null;

	public ListOfMeasurementsViews() {

	}

	public ListOfMeasurementsViews(List<Measurement> measurements) {

		for (Measurement measurement : measurements) {
			addMeasurement(measurement);
		}
	}

	public void addMeasurement(Measurement measurement) {
		MeasurementsView measurementsView = new MeasurementsView();

		if (measurementsViews.isEmpty()) {
			currentMeasurementDate = measurement.getDateOfMeasurement().getDate();
			currentModelName = measurement.getEquipment().getModel().getModelName();
			measurementsView.setDateOfMeasurement(currentMeasurementDate);
			measurementsView.setModelName(currentModelName);
		}

		measurementsView.setMeasurements(measurement);
		if (measurement.getDateOfSecondMeasurement() != null) {
			measurementsView.setDateOfSecondMeasurement(measurement
					.getDateOfSecondMeasurement().getDate());
		}

		if (currentMeasurementDate.isAfter(measurement.getDateOfMeasurement().getDate())) {
			currentMeasurementDate = measurement.getDateOfMeasurement().getDate();
			measurementsView.setDateOfMeasurement(currentMeasurementDate);
			measurementsView.setModelName(currentModelName);
		}

		if (currentModelName != measurement.getEquipment().getModel().getModelName()) {
			currentModelName = measurement.getEquipment().getModel().getModelName();
			measurementsView.setModelName(currentModelName);
		}

		measurementsViews.add(measurementsView);
	}

	public List<MeasurementsView> getMeasurementsViews() {
		return measurementsViews;
	}

	public void setMeasurementsViews(List<MeasurementsView> measurementsViews) {
		this.measurementsViews = measurementsViews;
	}

}
