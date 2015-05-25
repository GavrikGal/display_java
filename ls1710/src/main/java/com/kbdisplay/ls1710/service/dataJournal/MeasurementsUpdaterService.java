package com.kbdisplay.ls1710.service.dataJournal;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.domain.PurposeOfMeasurement;

@PreAuthorize("hasRole('ROLE_USER')")
public interface MeasurementsUpdaterService {

	public Measurement updateFromFolder();

	public Measurement saveMeasurements(String modelName, String serialNumber,
			String measurandName, String typeName, String screenResolutionName,
			String description, String user);

	public Measurement saveMeasurements(ModelOfEquipment modelOfEquipment,
			String serialNumber, List<Parameter> selectedParameters,
			PurposeOfMeasurement purposeOfMeasurement,
			/* Version version, */String description, NormGenerator normGenerator);

}
