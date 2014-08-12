package com.kbdisplay.ls1710.service.dataJournal.edit;

import org.joda.time.DateTime;

import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.Spectrum;
import com.kbdisplay.ls1710.domain.SpectrumParameter;
import com.kbdisplay.ls1710.domain.User;
import com.kbdisplay.ls1710.web.view.dataJournal.component.Version;

public interface MeasurementsUpdaterService {

	public void updateFromFolder();

	public Measurement saveMeasurements(String modelName, String serialNumber,
			String measurandName, String typeName,
			String screenResolutionName, String description, String user);

	public Measurement saveMeasurements(DateTime dateTime, Equipment equipment,
			Spectrum spectrum, SpectrumParameter spectrumParameter, User user,
			Version version, String description);

}
