package com.kbdisplay.ls1710.web.view;

import org.joda.time.DateTime;

import com.kbdisplay.ls1710.domain.Measurement;

/**
 * @deprecated
 * @author Gavrik
 *
 */
@Deprecated
public class MeasurementsView {

	private Measurement	measurements;
	private String			modelName;
	private DateTime		dateOfMeasurement;
	private DateTime		dateOfSecondMeasurement;

	public Measurement getMeasurements() {
		return measurements;
	}

	public void setMeasurements(Measurement measurement) {
		this.measurements = measurement;
	}

	public DateTime getDateOfMeasurement() {
		return dateOfMeasurement;
	}

	public void setDateOfMeasurement(DateTime dateOfMeasurement) {
		this.dateOfMeasurement = dateOfMeasurement;
	}

	public DateTime getDateOfSecondMeasurement() {
		return dateOfSecondMeasurement;
	}

	public void setDateOfSecondMeasurement(DateTime dateOfSecondMeasurement) {
		this.dateOfSecondMeasurement = dateOfSecondMeasurement;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}
