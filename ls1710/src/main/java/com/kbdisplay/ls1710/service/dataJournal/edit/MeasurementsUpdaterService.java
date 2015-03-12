package com.kbdisplay.ls1710.service.dataJournal.edit;

import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.domain.PurposeOfMeasurement;
import com.kbdisplay.ls1710.domain.SpectrumParameter;

public interface MeasurementsUpdaterService {

	public void updateFromFolder();

	public Measurement saveMeasurements(String modelName, String serialNumber,
			String measurandName, String typeName, String screenResolutionName,
			String description, String user);

	public Measurement saveMeasurements(ModelOfEquipment modelOfEquipment,
			String serialNumber, SpectrumParameter spectrumParameter,
			PurposeOfMeasurement purposeOfMeasurement,
			/* Version version, */String description);

}
