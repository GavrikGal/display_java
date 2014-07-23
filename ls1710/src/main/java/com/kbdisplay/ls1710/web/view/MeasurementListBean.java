package com.kbdisplay.ls1710.web.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;

import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.service.data.MeasurementService;
//import com.kbdisplay.ls1710.domain.ModelOfEquipment;

@ManagedBean(name="measurementListBean")
@ViewScoped
public class MeasurementListBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6492327437658887339L;

	private List<Measurement> measurements;
	
	private Measurement selecedMeasurement;
	
//	@Autowired
//	private MeasurementService measurementService; 
	
	//private ModelOfEquipment modelOfEquipment;

	
	
//	public ModelOfEquipment getModelOfEquipment() {
//		return modelOfEquipment;
//	}
//
//	public void setModelOfEquipment(ModelOfEquipment modelOfEquipment) {
//		this.modelOfEquipment = modelOfEquipment;
//	}

//	@PostConstruct
//    public void init() {
//        measurements = measurementService.findAll();
//    }
	
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
