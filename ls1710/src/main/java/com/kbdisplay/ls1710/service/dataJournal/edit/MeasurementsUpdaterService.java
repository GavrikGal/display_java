package com.kbdisplay.ls1710.service.dataJournal.edit;

import com.kbdisplay.ls1710.domain.Measurement;

public interface MeasurementsUpdaterService {

	public void updateFromFolder();

	public Measurement saveMeasurements(String modelName, String serialNumber,
			String measurandName, String typeName,
			String screenResolutionName, String description, String user);

}
