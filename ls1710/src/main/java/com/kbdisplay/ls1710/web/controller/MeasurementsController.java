package com.kbdisplay.ls1710.web.controller;

//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Locale;
import java.util.List;

//import javax.servlet.http.HttpServletRequest;
//import javax.validation.Valid;

//import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//import com.kbdisplay.ls1710.domain.DateOfMeasurement;
//import com.kbdisplay.ls1710.domain.Equipment;
//import com.kbdisplay.ls1710.domain.Measurand;
import com.kbdisplay.ls1710.domain.Measurement;
//import com.kbdisplay.ls1710.domain.ModelOfEquipment;
//import com.kbdisplay.ls1710.domain.ScreenResolution;
//import com.kbdisplay.ls1710.domain.TypeOfSpectrum;
//import com.kbdisplay.ls1710.domain.User;
//import com.kbdisplay.ls1710.service.data.DateOfMeasurementService;
//import com.kbdisplay.ls1710.service.data.EquipmentService;
//import com.kbdisplay.ls1710.service.data.HarmonicService;
//import com.kbdisplay.ls1710.service.data.MeasurandService;
import com.kbdisplay.ls1710.service.data.MeasurementService;
//import com.kbdisplay.ls1710.service.dataJournal.edit.MeasurementsUpdaterService;
//import com.kbdisplay.ls1710.service.data.ModelService;
//import com.kbdisplay.ls1710.service.data.ScreenResolutionService;
//import com.kbdisplay.ls1710.service.data.SpectrumParameterService;
//import com.kbdisplay.ls1710.service.data.SpectrumService;
//import com.kbdisplay.ls1710.service.data.TypeOfSpectrumService;
//import com.kbdisplay.ls1710.service.data.UserService;
//import com.kbdisplay.ls1710.web.form.MeasurementsForm;
//import com.kbdisplay.ls1710.web.form.Message;
//import com.kbdisplay.ls1710.web.view.ListOfMeasurementsViews;
import com.kbdisplay.ls1710.web.view.MeasurementListBean;
//import com.kbdisplay.ls1710.web.view.MeasurementsView;
//import com.google.common.collect.Lists;

//@RequestMapping("/measurements")
@Component("measurementController")
public class MeasurementsController {

//	private final int rowAtPage = 30;

	private final Logger logger = LoggerFactory
			.getLogger(MeasurementsController.class);

	
	/**
	 * А тут что бляd Все нормально тут sad.
	 * 
	 * @author Gavrik
	 * 
	 * @version 1.0
	 */
	@Autowired
	private MeasurementService measurementsService;

//	@Autowired
//	private ModelService modelsService;
//
//	@Autowired
//	private MeasurandService measurandsService;
//
//	@Autowired
//	private TypeOfSpectrumService typesOfSpectrumService;
//
//	@Autowired
//	private ScreenResolutionService screenResolutionsService;
//
//	@Autowired
//	private UserService usersService;
//
//	@Autowired
//	private DateOfMeasurementService dateOfMeasurementService;
//
//	@Autowired
//	private EquipmentService equipmentsService;
//
//	@Autowired
//	private SpectrumParameterService spectrumsParametersService;
//
//	@Autowired
//	private SpectrumService spectrumsService;
//
//	@Autowired
//	private HarmonicService harmonicsService;
//
//	@Autowired
//	private MeasurementsUpdaterService measurementsUpdaterService;


//	@Autowired
//	private MessageSource messageSource;

	// ____________________Request Mapping_________________________________
	public final MeasurementListBean newMeasurementListBean() {
		logger.info("newMeasurementListBean() execution...");
		MeasurementListBean measurementListBean = new MeasurementListBean();

		List<Measurement> measurements = measurementsService.findAll();

		measurementListBean.setMeasurements(measurements);

		return measurementListBean;
	}
//
//	@RequestMapping(method = RequestMethod.GET)
//	public final String list(final Model uiModel,
//			final MeasurementsForm measurementsForm,
//			final HttpServletRequest request) {
//		logger.info("Listing measurements");
//
//		if (!uiModel.containsAttribute("measurementsForm")) {
//			measurementsForm = new MeasurementsForm();
//
//			List<MeasurementsView> measurementsViews = getMeasurementsView(
//					new ListOfMeasurementsViews(), uiModel);
//
//			measurementsForm.setDescription("");
//			measurementsForm.setMeasurand(measurementsViews.get(0)
//					.getMeasurements().getSpectrums().get(0)
//					.getSpectrumParameters().getMeasurand().getIdMeasurands());
//			measurementsForm
//					.setModel(measurementsViews.get(0).getMeasurements()
//							.getEquipment().getModel().getModelName());
//			measurementsForm.setScreenResolutions(measurementsViews.get(0)
//					.getMeasurements().getSpectrums().get(0)
//					.getSpectrumParameters().getScreenResolution()
//					.getResolution());
//			measurementsForm.setType(measurementsViews.get(0).getMeasurements()
//					.getSpectrums().get(0).getSpectrumParameters()
//					.getTypeOfSpectrum().getIdType());
//
//			uiModel.addAttribute("measurementsForm", measurementsForm);
//		} else {
//			measurementsForm.setDescription("");
//			uiModel.addAttribute("measurementsForm", measurementsForm);
//		}
//
//		return "measurements/list";
//	}
//
//	@RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
//	public final String listNextPage(@PathVariable("page") final int page,
//			final ListOfMeasurementsViews listOfMeasurementsViews,
//			final MeasurementsForm measurementsForm, final Model uiModel,
//			final RedirectAttributes redirectAttributes, final Locale locale) {
//
//		PageRequest pageRequest = null;
//		pageRequest = new PageRequest(page, rowAtPage);
//		Page<Measurement> measurementsPage = measurementsService
//				.findAllByPage(pageRequest);
//		List<Measurement> measurements = Lists.newArrayList(measurementsPage
//				.iterator());
//		Collections.reverse(measurements);
//		for (Measurement measurement : measurements) {
//			listOfMeasurementsViews.addMeasurement(measurement);
//		}
//		List<MeasurementsView> measurementsViews = listOfMeasurementsViews
//				.getMeasurementsViews();
//
//		uiModel.asMap().clear();
//		redirectAttributes.addFlashAttribute("measurementsViews",
//				measurementsViews);
//		redirectAttributes.addFlashAttribute("measurementsForm",
//				measurementsForm);
//		redirectAttributes.addFlashAttribute("listOfMeasurementsViews",
//				listOfMeasurementsViews);
//		redirectAttributes.addFlashAttribute("currentPage", page);
//
//		return "redirect:/measurements";
//	}
//
//	@PreAuthorize("isAuthenticated()")
//	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
//	public final String delete(@PathVariable("id") final Long id,
//			final MeasurementsForm measurementsForm, final Model uiModel,
//			final RedirectAttributes redirectAttributes, final Locale locale) {
//
//		Measurement deletedMeasurement = measurementsService.findById(id);
//		System.out.println("deliting Measurement: id - "
//				+ deletedMeasurement.getIdMeasurements() + "; Model - "
//				+ deletedMeasurement.getEquipment().getModel().getModelName()
//				+ ", serial No - "
//				+ deletedMeasurement.getEquipment().getSerialNumber()
//				+ ", with date - " + deletedMeasurement.getDateOfMeasurement());
//
//		logger.info("Deliting measutements with id : "
//				+ deletedMeasurement.getIdMeasurements());
//
//		measurementsService.delete(deletedMeasurement);
//
//		uiModel.asMap().clear();
//
//		redirectAttributes.addFlashAttribute("measurementsForm",
//				measurementsForm);
//		redirectAttributes
//				.addFlashAttribute(
//						"message",
//						new Message("success", messageSource.getMessage(
//								"measurements_delete_success", new Object[] {},
//								locale)));
//		return "redirect:/measurements";
//	}
//
//	@PreAuthorize("isAuthenticated()")
//	@RequestMapping(value = "/protocol/{id}", method = RequestMethod.GET)
//	public final String protocol(@PathVariable("id") final Long id,
//			final Model uiModel, final RedirectAttributes redirectAttributes,
//			final Locale locale) {
//		Measurement protocolMeasurement = measurementsService.findById(id);
//		uiModel.addAttribute("protocolMeasurement", protocolMeasurement);
//
//		return "measurements/protocol";
//	}
//
//	@PreAuthorize("isAuthenticated()")
//	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
//	public final String edit(@PathVariable("id") final Long id,
//			final MeasurementsForm measurementsForm, final Model uiModel,
//			final RedirectAttributes redirectAttributes, final Locale locale) {
//
//		Measurement editedMeasurement = measurementsService.findById(id);
//
//		measurementsForm.setSerialNumber(editedMeasurement.getEquipment()
//				.getSerialNumber());
//		measurementsForm.setModel(editedMeasurement.getEquipment().getModel()
//				.getModelName());
//		measurementsForm.setMeasurand(editedMeasurement.getSpectrums().get(0)
//				.getSpectrumParameters().getMeasurand().getIdMeasurands());
//		measurementsForm.setScreenResolutions(editedMeasurement.getSpectrums()
//				.get(0).getSpectrumParameters().getScreenResolution()
//				.getResolution());
//		measurementsForm.setType(editedMeasurement.getSpectrums().get(0)
//				.getSpectrumParameters().getTypeOfSpectrum().getIdType());
//		measurementsForm.setDescription("");
//
//		uiModel.asMap().clear();
//		redirectAttributes.addFlashAttribute("measurementsForm",
//				measurementsForm);
//		return "redirect:/measurements";
//	}
//
//	@RequestMapping(method = RequestMethod.POST)
//	public final String update(@Valid final MeasurementsForm measurementsForm,
//			final BindingResult bindingResult, final Model uiModel,
//			final HttpServletRequest httpServletRequest,
//			final RedirectAttributes redirectAttributes, final Locale locale) {
//
//		logger.info("Updating measutements");
//
//		if (bindingResult.hasErrors()) {
//			uiModel.addAttribute(
//					"message",
//					new Message("error", messageSource.getMessage(
//							"measurements_save_fail", new Object[] {}, locale)));
//			uiModel.addAttribute("measurementsForm", measurementsForm);
//			return "measurements/list";
//		}
//
//		measurementsUpdaterService.saveMeasurements(
//				measurementsForm.getModel(),
//				measurementsForm.getSerialNumber(),
//				measurementsForm.getMeasurand(), measurementsForm.getType(),
//				measurementsForm.getScreenResolutions(),
//				measurementsForm.getDescription(), measurementsForm.getUser());
//
//		uiModel.asMap().clear();
//		redirectAttributes.addFlashAttribute("measurementsForm",
//				measurementsForm);
//		redirectAttributes.addFlashAttribute(
//				"message",
//				new Message("success", messageSource.getMessage(
//						"measurements_save_success", new Object[] {}, locale)));
//
//		return "redirect:/measurements";
//	}
//
//	// _________________Model Attribute_______________________________________
//	@ModelAttribute
//	public final List<MeasurementsView> getMeasurementsView(
//			final ListOfMeasurementsViews listOfMeasurementsViews,
//			final Model uiModel) {
//
//		if (SecurityContextHolder.getContext().getAuthentication()
//				.getPrincipal() != "anonymousUser") {
//			logger.info("Update starting");
//			measurementsUpdaterService.updateFromFolder();
//		}
//
//		List<MeasurementsView> measurementsViews = null;
//
//		if (listOfMeasurementsViews.getMeasurementsViews().isEmpty()) {
//			Long countOfMeasurements = measurementsService.count();
//			System.out.println(countOfMeasurements);
//			int numberOfLastPage = countOfMeasurements.intValue() / rowAtPage;
//			if (countOfMeasurements == (numberOfLastPage * rowAtPage)) {
//				numberOfLastPage--;
//			}
//
//			PageRequest pageRequest = null;
//			pageRequest = new PageRequest(numberOfLastPage, rowAtPage);
//			Page<Measurement> measurementsPage = measurementsService
//					.findAllByPage(pageRequest);
//			List<Measurement> measurements = Lists
//					.newArrayList(measurementsPage.iterator());
//			if ((measurements.size() < rowAtPage) && (numberOfLastPage > 0)) {
//				numberOfLastPage--;
//				pageRequest = new PageRequest(numberOfLastPage, rowAtPage);
//				measurementsPage = measurementsService
//						.findAllByPage(pageRequest);
//				List<Measurement> newMeasurements = Lists
//						.newArrayList(measurementsPage.iterator());
//				Collections.reverse(measurements);
//				Collections.reverse(newMeasurements);
//				for (Measurement measurement : newMeasurements) {
//					measurements.add(measurement);
//				}
//			} else {
//				Collections.reverse(measurements);
//			}
//			listOfMeasurementsViews = new ListOfMeasurementsViews(measurements);
//			measurementsViews = listOfMeasurementsViews.getMeasurementsViews();
//
//			uiModel.addAttribute("listOfMeasurementsViews",
//					listOfMeasurementsViews);
//			uiModel.addAttribute("measurementsViews", measurementsViews);
//			uiModel.addAttribute("currentPage", numberOfLastPage);
//		} else {
//			measurementsViews = listOfMeasurementsViews.getMeasurementsViews();
//		}
//
//		return measurementsViews;
//
//	}
//
//	@ModelAttribute("currentDate")
//	public final DateTime getCurrentDate() {
//		return new DateTime();
//	}
//
//	// @ModelAttribute("page")
//	// private Long getNumberOfLastPage(){
//	// final Long rowAtPage = 20l;
//	// Long countOfMeasurements = measurementsService.count();
//	// Long numberOfLastPage = countOfMeasurements / rowAtPage;
//	// if (countOfMeasurements > (numberOfLastPage*rowAtPage)) {
//	// numberOfLastPage++;
//	// }
//	// return numberOfLastPage;
//	// }
//
//	@ModelAttribute("models")
//	public final List<ModelOfEquipment> getAllModels() {
//		return modelsService.findAll();
//	}
//
//	@ModelAttribute("measurands")
//	public final List<Measurand> getAllMeasurands() {
//		return measurandsService.findAll();
//	}
//
//	@ModelAttribute("types")
//	public final List<TypeOfSpectrum> getAllTypes() {
//		return typesOfSpectrumService.findAll();
//	}
//
//	@ModelAttribute("screenResolutions")
//	public final List<ScreenResolution> getAllScreenResolutions() {
//		return screenResolutionsService.findAll();
//	}
//
//	@ModelAttribute("usersList")
//	public final List<String> getAllUsers() {
//		List<User> usersList = usersService.findAll();
//		List<String> stringUserList = new ArrayList<String>();
//		for (User user : usersList) {
//			stringUserList.add(user.getFirstName());
//		}
//		return stringUserList;
//	}
//
//	// _____________Utils_____________________________________

}
