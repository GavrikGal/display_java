package com.kbdisplay.ls1710.service.dataJournal.edit.jpa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.impl.piccolo.io.FileFormatException;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kbdisplay.ls1710.domain.DateOfMeasurement;
import com.kbdisplay.ls1710.domain.Equipment;
import com.kbdisplay.ls1710.domain.Harmonic;
import com.kbdisplay.ls1710.domain.Measurand;
import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.domain.ScreenResolution;
import com.kbdisplay.ls1710.domain.Spectrum;
import com.kbdisplay.ls1710.domain.SpectrumParameter;
import com.kbdisplay.ls1710.domain.TypeOfSpectrum;
import com.kbdisplay.ls1710.domain.User;
import com.kbdisplay.ls1710.service.data.DateOfMeasurementService;
import com.kbdisplay.ls1710.service.data.EquipmentService;
import com.kbdisplay.ls1710.service.data.HarmonicService;
import com.kbdisplay.ls1710.service.data.MeasurandService;
import com.kbdisplay.ls1710.service.data.MeasurementService;
import com.kbdisplay.ls1710.service.data.ModelService;
import com.kbdisplay.ls1710.service.data.ScreenResolutionService;
import com.kbdisplay.ls1710.service.data.SpectrumParameterService;
import com.kbdisplay.ls1710.service.data.SpectrumService;
import com.kbdisplay.ls1710.service.data.TypeOfSpectrumService;
import com.kbdisplay.ls1710.service.data.jpa.CustomUserDetails.CustomUserDetails;
import com.kbdisplay.ls1710.service.dataJournal.edit.MeasurementsUpdaterService;
import com.kbdisplay.ls1710.service.file.FileFinderService;
import com.kbdisplay.ls1710.service.parcer.DescriptionForParsing;

@Service("measurementsUpdaterService")
@Transactional
public class MeasurementsUpdaterServiceImpl implements
		MeasurementsUpdaterService {

	final Logger logger = LoggerFactory
			.getLogger(MeasurementsUpdaterServiceImpl.class);

	// TODO �������� ��������� �� �������� ����������� �� ����� ��������
	final String frequencyCellName = "�������, ���";
	final String amplitudeCellName = "��+�, �����/�";
	final String noiseCellName = "��, �����/�";
	final String receiverBandwidthCellName = "��, ���";
	final String rootPathName = "D:\\������";

	@Autowired
	private DateOfMeasurementService dateOfMeasurementService;

	@Autowired
	private ModelService modelService;

	@Autowired
	private EquipmentService equipmentService;

	@Autowired
	private MeasurementService measurementService;

	@Autowired
	private MeasurandService measurandService;

	@Autowired
	private TypeOfSpectrumService typesOfSpectrumService;

	@Autowired
	private ScreenResolutionService screenResolutionService;

	@Autowired
	private SpectrumService spectrumService;

	@Autowired
	private HarmonicService harmonicService;

	@Autowired
	private SpectrumParameterService spectrumParameterService;

	@Autowired
	private FileFinderService fileFinder;

	@Override
	public void updateFromFolder() {
		try {

			// String rootPath = "D:\\������";

			final Double deviationFrequency = 0.02;
			List<File> fileList = fileFinder.findFiles(rootPathName,
					"[\\w[�-��-�]]+\\.(docx|doc)");

			for (File file : fileList) {

				// ��������� ������ �� ����� ���������
				// ��������� ���� ��������� � ����������
				File typeMeasurementsFolder = new File(file.getParent());
				String[] typeAndResolutions = typeMeasurementsFolder.getName()
						.split(" ");
				String measurandName = typeAndResolutions[0];
				String screenResolutionsNameOrType = typeAndResolutions[1];
				String typeName;
				String screenResolutionsName;
				if (screenResolutionsNameOrType != "���") {
					typeName = "��";
					screenResolutionsName = screenResolutionsNameOrType;
				} else {
					typeName = "���";
					screenResolutionsName = "";
				}
				System.out.println("Measurand: " + measurandName);
				System.out.println("Resolution: " + screenResolutionsName);
				System.out.println("Type: " + typeName);

				// ��������� ������ �������
				File modelMeasurementsFilder = new File(
						typeMeasurementsFolder.getParent());
				String modelName = modelMeasurementsFilder.getName();
				System.out.println("model: " + modelName);

				// ��������� ��������� ������
				String fileName = file.getName();
				fileName = fileName.trim();
				String serialNumber = fileName.substring(0,
						fileName.indexOf(".doc"));
				System.out.println("sn: " + serialNumber);

				String absoluteFileName = file.getAbsolutePath();

				if (!(absoluteFileName.endsWith(".doc") || absoluteFileName
						.endsWith(".docx"))) {
					throw new FileFormatException();
				} else {

					// ���� ������, ���� �� ���, �� ������� �� � ����� � ���
					ModelOfEquipment model = getModel(modelName);

					// ���� ������� � ���� ������, ���� �� �������, �� �������
					// �����
					Equipment equipment = getEquipments(model, serialNumber);

					// ���� ������� ��������� � �� ���� ������� �����
					Measurement measurement = getMeasurements(equipment);

					// ������� �������� ��������� ������� �� ��, ��� ������� ��
					SpectrumParameter spectrumParameter = getSpectrumsParameters(
							measurandName, typeName, screenResolutionsName);

					// ���� ������ � ��, ��� ������� �����
					Spectrum spectrum = getSpectrums(measurement,
							spectrumParameter);



					// Measurements newMeasurements = new Measurements();

					// setCurrentDateOfMeasurement(newMeasurements);
					// setEquipment(newMeasurements, modelName, serialNumber);

					int frequencyCellNumber = -1;
					int amplitudeCellNumber = -1;
					int noiseCellNumber = -1;
					int receiverBandwidthCellNumber = -1;

					XWPFDocument document = new XWPFDocument(
							new FileInputStream(absoluteFileName));

					List<XWPFTable> tables = document.getTables();

					for (XWPFTable table : tables) {
						List<XWPFTableRow> rows = table.getRows();

						List<XWPFTableCell> headTable = rows.get(0)
								.getTableCells();
						for (int a = 0; a < headTable.size(); a++) {
							if (headTable.get(a).getText()
									.equalsIgnoreCase(frequencyCellName)) {
								frequencyCellNumber = a;
							}
							if (headTable.get(a).getText()
									.equalsIgnoreCase(amplitudeCellName)) {
								amplitudeCellNumber = a;
							}
							if (headTable.get(a).getText()
									.equalsIgnoreCase(noiseCellName)) {
								noiseCellNumber = a;
							}
							if (headTable
									.get(a)
									.getText()
									.equalsIgnoreCase(receiverBandwidthCellName)) {
								receiverBandwidthCellNumber = a;
							}
						}

						if (frequencyCellNumber == -1) {
							System.out.println("�� ������� ������� �������");
							break;
						}
						if (receiverBandwidthCellNumber == -1) {
							System.out
									.println("�� ������� ������� ������ �����������");
							break;
						}
						if (amplitudeCellNumber == -1) {
							System.out.println("�� ������� ������� ���������");
							break;
						}
						if (noiseCellNumber == -1) {
							System.out.println("�� ������� ������� ����");
							break;
						}

						for (int i = 1; i < rows.size(); i++) {
							List<XWPFTableCell> cells = rows.get(i)
									.getTableCells();
//							System.out.print(cells.get(frequencyCellNumber)
//									.getText() + "\t");
//							System.out.print(cells.get(
//									receiverBandwidthCellNumber).getText()
//									+ "\t");
//							System.out.print(cells.get(amplitudeCellNumber)
//									.getText() + "\t");
//							System.out.print(cells.get(noiseCellNumber)
//									.getText());
//							System.out.println();

							Double frequency,receiverBandwidth, amplitude, noise;

							frequency = Double.parseDouble(cells.get(
									frequencyCellNumber).getText());
							receiverBandwidth = Double
									.parseDouble(cells.get(
											receiverBandwidthCellNumber)
											.getText());
							amplitude = Double.parseDouble(cells.get(
									amplitudeCellNumber).getText());
							noise = Double.parseDouble(cells.get(
									noiseCellNumber).getText());

							// �������� � ������ �������� �� ��������. ������
							// �������� �� �������
							// ��������, ���������� ����� �������� ��� ��������

							Harmonic newHarmonics = new Harmonic();
							List<Harmonic> oldHarmonics = spectrum.getHarmonics();
							for (Harmonic harmonics : oldHarmonics) {
								if (((harmonics.getFrequency() + deviationFrequency
										* harmonics.getFrequency()) > frequency)
										&& ((harmonics.getFrequency() - deviationFrequency
												* harmonics.getFrequency()) < frequency)) {
									newHarmonics = harmonics;
								}
							}
							newHarmonics.setFrequency(frequency);
							newHarmonics.setReceiverBandwidth(receiverBandwidth);
							if (amplitude != null) {
								newHarmonics.setAmplitude(amplitude);
								if (noise == null) {
									noise = 0.0;
								}
								newHarmonics.setNoise(noise);
								newHarmonics.setSpectrum(spectrum);
								if (newHarmonics.getIdHarmonics() == null) {
									harmonicService.save(newHarmonics);
									spectrum.getHarmonics().add(newHarmonics);
								}


							}

							//spectrum.getHarmonics().add(newHarmonics);

						}
						//measurement.getSpectrums().add(spectrum);
						System.out.println("new spectrum if : " + spectrum.getIdSpectrums());
					}


					measurement = getMeasurements(equipment);
					System.out.println("New Measurement with ID - " + measurement.getIdMeasurements()+ ":");

					//System.out.println("     Date of meas -" + measurement.getDateOfMeasurement().getDateString());
					System.out.println("     Id model -" + measurement.getEquipment().getModel().getIdModel());
					System.out.println("     model name - " + measurement.getEquipment().getModel().getModelName());
					System.out.println("     serial Number - " + measurement.getEquipment().getSerialNumber());
					System.out.println("     user name - " + measurement.getUser().getFirstName());
					System.out.println("     Spectrums:");
					for (Spectrum spectrums : measurement.getSpectrums()) {
						System.out.println("        Spectrum id - " + spectrums.getIdSpectrums());
						System.out.println("        getMeasurement() - " + spectrums.getMeasurement().getIdMeasurements());
						System.out.println("        spectrum parameters:");
						System.out.println("            spectrum parameters ID -" + spectrums.getSpectrumParameters().getIdSpectrumParameters());
						System.out.println("            Measurands -" + spectrums.getSpectrumParameters().getMeasurand().getIdMeasurands());
						System.out.println("            type - " + spectrums.getSpectrumParameters().getTypeOfSpectrum().getIdType());
						System.out.println("            Resolution - " + spectrums.getSpectrumParameters().getScreenResolution().getResolution());
						System.out.println("        Harmonics:");
						for (Harmonic harmonics : spectrums.getHarmonics()) {

							System.out.print("            " + harmonics.getIdHarmonics());
							System.out.print("\t" + harmonics.getFrequency());
							System.out.print("\t" + harmonics.getReceiverBandwidth());
							System.out.print("\t" + harmonics.getAmplitude());
							System.out.print("\t" + harmonics.getNoise());
							System.out.println("            For spectrum with Id: " + harmonics.getSpectrum().getIdSpectrums());

						}

					}


					// ��������� ��������
					//setDescription(spectrum, newDescription);
				}

			}

		} catch (FileFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Measurement saveMeasurements(String modelName, String serialNumber,
			String measurandName, String typeName,
			String screenResolutionsName, String description, String user) {

		// ���� ������, ���� �� ���, �� ������� �� � ����� � ���
		ModelOfEquipment model = getModel(modelName);

		// ���� ������� � ���� ������, ���� �� �������, �� ������� �����
		Equipment equipment = getEquipments(model, serialNumber);

		// ���� ������� ��������� � �� ���� ������� �����
		Measurement measurement = getMeasurements(equipment);

		// ������� �������� ��������� ������� �� ��, ��� ������� ��
		SpectrumParameter spectrumParameter = getSpectrumsParameters(
				measurandName, typeName, screenResolutionsName);

		// ���� ������ � ��, ��� ������� �����
		Spectrum spectrum = getSpectrums(measurement, spectrumParameter);

		// �������� � ������ �������� �� ��������. ������ �������� �� �������
		// ��������, ���������� ����� �������� ��� ��������
		String newDescription = setHarmonicsFromDescription(spectrum,
				description);

		// ��������� ��������
		setDescription(spectrum, newDescription);

		return measurement;
	}

	// ��������� ������� ���� �� �� ���� �������� ����� ������ � ��
	private DateOfMeasurement getCurrentDateOfMeasurement() {
		DateTime currentDate = new DateTime();
		DateOfMeasurement currentDateOfMeasurement = new DateOfMeasurement();
		currentDateOfMeasurement.setDate(currentDate.withTime(0, 0, 0, 0));
		currentDateOfMeasurement = dateOfMeasurementService
				.save(currentDateOfMeasurement);
		return currentDateOfMeasurement;
	}

	// �������� ������ �� ��, ���� �� ��� ��� �� ������� �������
	private ModelOfEquipment getModel(String modelName) {
		ModelOfEquipment model = findModel(modelName);
		if (model == null) {
			logger.info("Trying create new models");
			try {
				model = createNewModel(modelName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return model;
	}

	// ����� ������ ������� � ����
	private ModelOfEquipment findModel(String modelName) {
		modelName = modelName.trim();
		ModelOfEquipment model = modelService.findByModelName(modelName);
		if (model != null) {
			logger.info("Model found in the database. Id: " + model.getIdModel());
		}
		return model;
	}

	// �������� ����� ������ � ����
	private ModelOfEquipment createNewModel(String modelName) {
		modelName = modelName.trim();
		ModelOfEquipment model = new ModelOfEquipment();
		model.setModelName(modelName);
		model = modelService.save(model);

		// ��������� ������� ����� ������, ���� �� ������ �� ������� �� �������
		try {
			List<File> searchModelFolder = fileFinder.findDirectories(
					rootPathName, modelName);

			if (searchModelFolder.isEmpty()) {
				File modelFolder = new File(rootPathName + File.separator
						+ modelName);
				modelFolder.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	// �������� ������� �� ��, ���� ��� ��� ��� �� ������� �������
	private Equipment getEquipments(ModelOfEquipment model, String serialNumber) {
		Equipment equipment = new Equipment();
		equipment.setModel(model);
		equipment.setSerialNumber(serialNumber);
		equipment = equipmentService.save(equipment);
		if (equipment.getMeasurements() == null) {
			equipment.setMeasurements(new HashSet<Measurement>());
		}
		return equipment;
	}

	// ��������� ���������� ��������� ��� �������� ������� � ������� ����
	// �����������
	private Measurement getMeasurements(Equipment equipment) {

		DateOfMeasurement currentDateOfMeasurement = getCurrentDateOfMeasurement();
		Measurement measurement;
		// setCurrentDateOfMeasurement(measurement);

		User user = null;

		if (SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal() != "anonymousUser") {
			user = ((CustomUserDetails) SecurityContextHolder.getContext()
					.getAuthentication().getPrincipal()).getUsersDetails();
		}

		List<Measurement> measurementsList = measurementService
				.findByEquipment(equipment);

		if (measurementsList.isEmpty()) {
			measurement = new Measurement();
			measurement.setDateOfMeasurement(currentDateOfMeasurement);
			measurement.setEquipment(equipment);
			measurement.setSpectrums(new ArrayList<Spectrum>());
			measurement = measurementService.save(measurement);
			equipment.getMeasurements().add(measurement);
		} else {
			Measurement lastMeasurements = measurementsList
					.get(measurementsList.size() - 1);
			if (lastMeasurements.getDateOfMeasurement().getIdDate() == currentDateOfMeasurement
					.getIdDate()) {
				measurement = lastMeasurements;
			} else {
				if (lastMeasurements.getDateOfSecondMeasurement() == null) {
					lastMeasurements
							.setDateOfSecondMeasurement(currentDateOfMeasurement);
					measurement = lastMeasurements;
				} else {
					if (lastMeasurements.getDateOfSecondMeasurement()
							.getIdDate() == currentDateOfMeasurement
							.getIdDate()) {
						measurement = lastMeasurements;
					} else {
						measurement = new Measurement();
						measurement
								.setDateOfMeasurement(currentDateOfMeasurement);
						measurement.setEquipment(equipment);
						measurement.setSpectrums(new ArrayList<Spectrum>());
						measurement = measurementService.save(measurement);
						equipment.getMeasurements().add(measurement);
					}
				}
			}
		}
		if (user != null) {
			measurement.setUser(user);
		}

		if (measurement.getIdMeasurements() == null) {
			System.out
					.println("�������. � ��� ��������. IdMeasurements == null");
		}
		return measurement;
	}

	// �������� ��������� ������� �� �� ��� ������� �����
	private SpectrumParameter getSpectrumsParameters(String measurandName,
			String typeName, String screenResolutionName) {
		Measurand measurand = measurandService.findById(measurandName);
		if (measurand == null) {
			logger.error("�� ������ ��������: measurand = " + measurandName
					+ ". ������� ��� � ��");
		}
		ScreenResolution screenResolution = getScreenResolutions(screenResolutionName);
		TypeOfSpectrum typeOfSpectrum = typesOfSpectrumService.findById(typeName);
		if (typeOfSpectrum == null) {
			logger.error("�� ������ ��������: type = " + typeName
					+ ". ������� ��� � ��");
		}

		SpectrumParameter spectrumParameter = new SpectrumParameter();
		spectrumParameter.setMeasurand(measurand);
		spectrumParameter.setScreenResolution(screenResolution);
		spectrumParameter.setTypeOfSpectrum(typeOfSpectrum);
		spectrumParameter.setSpectrums(new HashSet<Spectrum>());

		spectrumParameter = spectrumParameterService
				.save(spectrumParameter);
		return spectrumParameter;
	}

	// ��������� ���������� ������, ���� ��� ���
	private ScreenResolution getScreenResolutions(String screenResolutionsName) {
		screenResolutionsName = screenResolutionsName.trim();
		ScreenResolution screenResolution = screenResolutionService
				.findByResolution(screenResolutionsName);
		if (screenResolution == null) {
			screenResolution = new ScreenResolution();
			screenResolution.setResolution(screenResolutionsName);
			screenResolution
					.setSpectrumsParameters(new HashSet<SpectrumParameter>());
			screenResolution = screenResolutionService.save(screenResolution);
		}
		return screenResolution;
	}

	// �������� ����� �� �� ��� ������� �����
	private Spectrum getSpectrums(Measurement measurement,
			SpectrumParameter spectrumParameter) {

		Spectrum spectrum = spectrumService
				.findByMeasurementAndSpectrumParameters(measurement,
						spectrumParameter);

		if (spectrum == null) {
			spectrum = new Spectrum();
			spectrum.setMeasurement(measurement);
			spectrum.setSpectrumParameters(spectrumParameter);
			spectrum.setHarmonics(new ArrayList<Harmonic>());
		}
		DateTime currentDate = new DateTime();
		spectrum.setDateTime(currentDate);

		spectrum = spectrumService.save(spectrum);
		if (measurement.getSpectrums().isEmpty()) {
			measurement.getSpectrums().add(spectrum);

		}
		return spectrum;
	}

	// ������ �������� �� ������� �������� � ������������� �� � ������
	private String setHarmonicsFromDescription(Spectrum spectrum,
			String description) {

		DescriptionForParsing newDescription = new DescriptionForParsing(
				description);
		final Double deviationFrequency = 0.02;

		Double frequency, receiverBandwidth, amplitude, noise;

		receiverBandwidth = 30.0;

		while (!newDescription.isString()) {

			frequency = newDescription.parseFrequency();
			if (frequency != null) {
				Harmonic newHarmonics = new Harmonic();
				List<Harmonic> oldHarmonics = spectrum.getHarmonics();
				for (Harmonic harmonics : oldHarmonics) {
					if (((harmonics.getFrequency() + deviationFrequency
							* harmonics.getFrequency()) > frequency)
							&& ((harmonics.getFrequency() - deviationFrequency
									* harmonics.getFrequency()) < frequency)) {
						newHarmonics = harmonics;
					}
				}
				newHarmonics.setFrequency(frequency);
				newHarmonics.setReceiverBandwidth(receiverBandwidth);
				amplitude = newDescription.parseAmplitude();
				if (amplitude != null) {
					newHarmonics.setAmplitude(amplitude);
					noise = newDescription.parseNoise();
					if (noise == null) {
						noise = 0.0;
					}
					newHarmonics.setNoise(noise);
					newHarmonics.setSpectrum(spectrum);
					harmonicService.save(newHarmonics);
				}
			} else {
				break;
			}
		}

		description = newDescription.getDescription();
		return description;
	}

	// ��������� �������� � ������� � ���� ����, �� ��������� �����
	private void setDescription(Spectrum spectrum, String description) {
		if (description != null) {
			if (spectrum.getDescription() == null) {
				spectrum.setDescription(description);
			} else {
				if (spectrum.getDescription().isEmpty()) {
					spectrum.setDescription(description);
				} else {
					if (!(spectrum.getDescription().contains(description))) {
						spectrum.setDescription(spectrum.getDescription()
								+ "; " + description);
					}
				}
			}
			spectrum = spectrumService.save(spectrum);
		}
	}

}
