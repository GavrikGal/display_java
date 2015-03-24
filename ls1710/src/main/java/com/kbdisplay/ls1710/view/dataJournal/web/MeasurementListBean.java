package com.kbdisplay.ls1710.view.dataJournal.web;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.kbdisplay.ls1710.domain.Measurement;

//import com.kbdisplay.ls1710.domain.ModelOfEquipment;

/**
 *
 * @deprecated
 * @author Gavrik
 *
 */
@Deprecated
@ManagedBean(name = "measurementListBean")
@ViewScoped
public class MeasurementListBean implements Serializable {

	/**
	 * серийный номер класса.
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

	public void deleteMeasurement() {
		measurements.remove(selecedMeasurement);
		selecedMeasurement = null;
	}

}
