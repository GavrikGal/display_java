package com.kbdisplay.ls1710.web.view;

import java.io.Serializable;
import java.util.List;

import com.kbdisplay.ls1710.domain.Measurement;

public class MeasurementListBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6492327437658887339L;

	private List<Measurement> measurements;
	
	private Measurement selecedMeasurement;

	public List<Measurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}

	public Measurement getSelecedMeasurement() {
		return selecedMeasurement;
	}

	public void setSelecedMeasurement(Measurement selecedMeasurement) {
		this.selecedMeasurement = selecedMeasurement;
	}
	
	

}
