package com.kbdisplay.ls1710.view.dataJournal;

import java.util.List;

import org.primefaces.model.menu.MenuModel;

import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.domain.PurposeOfMeasurement;
import com.kbdisplay.ls1710.domain.TypeOfParameter;

public interface EditData {

	void init();

	void initSelected(final Measurement selectedMeasurement);

	ModelOfEquipment getModel();

	void setModel(final ModelOfEquipment model);

	String getSerialNumber();

	void setSerialNumber(final String serialNumber);

	// SpectrumParameter getSpectrumParameter();
	//
	// void setSpectrumParameter(final SpectrumParameter spectrParameter);

	PurposeOfMeasurement getPurposeOfMeasurement();

	void setPurposeOfMeasurement(final PurposeOfMeasurement purpose);

	String getDescription();

	void setDescription(final String description);

	List<ModelOfEquipment> getModelOfEquipments();

	void setModelOfEquipments(final List<ModelOfEquipment> modelOfEquipments);

	// List<Measurand> getMeasurands();
	//
	// void setMeasurands(final List<Measurand> measurands);
	//
	// List<ScreenResolution> getScreenResolutions();
	//
	// void setScreenResolutions(final List<ScreenResolution>
	// screenResolutions);
	//
	// List<TypeOfSpectrum> getTypeOfSpectrums();
	//
	// void setTypeOfSpectrums(final List<TypeOfSpectrum> typeOfSpectrums);

	List<PurposeOfMeasurement> getPurposeOfMeasurements();

	void setPurposeOfMeasurements(
			final List<PurposeOfMeasurement> purposeOfMeasurements);

	List<TypeOfParameter> getAvailableTypes();

	void setAvailableTypes(List<TypeOfParameter> availableTypes);

	List<Parameter> getSelectedParameters();

	void setSelectedParameters(List<Parameter> selectedParameters);



	List<List<Parameter>> getAvailableParameterLists();

	void setAvailableParameterLists(
			List<List<Parameter>> availableParameterLists);



	TypeOfParameter getNewType();

	void setNewType(TypeOfParameter newType);

	TypeOfParameter getSelectedType();

	void setSelectedType(TypeOfParameter selectedType);

	MenuModel getMenuModel();

	void setMenuModel(MenuModel menuModel);

}
