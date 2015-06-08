package com.kbdisplay.ls1710.view.settings.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSeparator;
import org.primefaces.model.menu.MenuModel;

import com.kbdisplay.ls1710.domain.Limit;
import com.kbdisplay.ls1710.domain.Norm;
import com.kbdisplay.ls1710.domain.NormHandler;
import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.domain.Standard;
import com.kbdisplay.ls1710.domain.TypeOfParameter;
import com.kbdisplay.ls1710.service.data.NormHandlerService;
import com.kbdisplay.ls1710.service.data.NormService;
import com.kbdisplay.ls1710.service.data.StandardService;
import com.kbdisplay.ls1710.service.data.TypeOfParameterService;
import com.kbdisplay.ls1710.view.settings.NormsSetting;

public class NormsSettingImpl implements NormsSetting, Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 5763240494064173752L;

	private List<Standard> standards;
	private Standard selectedStandard;
	private List<Norm> norms;
	private Norm selectedNorm;
	private List<NormHandler> normHandlers;
	private NormHandler selectedNormHandler;

	private List<Limit> limits;

	private TypeOfParameter newType;
	private TypeOfParameter selectedType;
	private List<TypeOfParameter> availableTypes;
	private MenuModel menuModel;
	private List<Parameter> selectedParameters;
	private List<List<Parameter>> availableParameterLists;


	public NormsSettingImpl() {
		selectedParameters = new ArrayList<Parameter>();
		selectedType = null;
		availableParameterLists = new ArrayList<List<Parameter>>();
	}

	@Override
	public void init() {
		List<TypeOfParameter> availableTypes =
				initNextAvailableTypes(selectedType);
		this.availableTypes = availableTypes;
		this.menuModel = createTypeMenu(availableTypes);

		FacesContext fc = FacesContext.getCurrentInstance();
		ELContext elContext = FacesContext.getCurrentInstance().getELContext();
		StandardService standardService =
				(StandardService) fc.getApplication().getELResolver()
						.getValue(elContext, null, "standardService");

		standards = standardService.findAll();

		NormService normService =
				(NormService) fc.getApplication().getELResolver()
						.getValue(elContext, null, "normService");

		norms = normService.findAll();

		NormHandlerService normHandlerService =
				(NormHandlerService) fc.getApplication().getELResolver()
						.getValue(elContext, null, "normHandlerService");

		normHandlers = normHandlerService.findAll();

		limits = new ArrayList<Limit>();
	}

	private List<TypeOfParameter> initNextAvailableTypes(
			TypeOfParameter currentType) {
		List<TypeOfParameter> availableTypes = null;

		if (currentType == null) {
			FacesContext fc = FacesContext.getCurrentInstance();
			ELContext elContext =
					FacesContext.getCurrentInstance().getELContext();
			TypeOfParameterService typeOfParameterService =
					(TypeOfParameterService) fc
							.getApplication()
							.getELResolver()
							.getValue(elContext, null, "typeOfParameterService");
			availableTypes = typeOfParameterService.findByPrevTypeId(null);

		} else {
			availableTypes = currentType.getNextTypes();
			if (availableTypes == null) {
				availableTypes = new ArrayList<TypeOfParameter>();
			}
		}

		return availableTypes;
	}

	private MenuModel createTypeMenu(List<TypeOfParameter> availableTypes) {
		MenuModel menuModel = new DefaultMenuModel();
		if (availableTypes != null && !availableTypes.isEmpty()) {

			for (int i = 0; i < availableTypes.size(); i++) {
				DefaultMenuItem item =
						new DefaultMenuItem(availableTypes.get(i).getName());
				item.setCommand("#{normsSetting.selectTypeOfParameter('" + i
						+ "')}");
				item.setUpdate("spectrumParameters spectrumParametersMenu spectrumParameterscontextMenu");

				menuModel.addElement(item);
			}
			menuModel.addElement(new DefaultSeparator());
		}

		DefaultMenuItem item = new DefaultMenuItem("Новый тип параметра");

		item.setUpdate("spectrumParameters spectrumParametersMenu spectrumParameterscontextMenu");
		item.setOnclick("PF('newTypeOfParameterWV').show()");

		menuModel.addElement(item);
		return menuModel;
	}

	@Override
	public void selectTypeOfParameter(String itemN) {
		TypeOfParameter selectedType =
				availableTypes.get(Integer.parseInt(itemN));
		this.selectedType = selectedType;
		List<TypeOfParameter> availableTypes =
				initNextAvailableTypes(selectedType);
		this.availableTypes = availableTypes;
		MenuModel menuModel = createTypeMenu(availableTypes);
		this.menuModel = menuModel;

		if (selectedType != null) {

			List<Parameter> availableParameters = selectedType.getParameters();

			if ((availableParameters != null)
					&& (!availableParameters.isEmpty())) {
				availableParameterLists.add(availableParameters);
				selectedParameters.add(availableParameters.get(0));
			} else {
				availableParameterLists.add(new ArrayList<Parameter>());
				Parameter parameter = new Parameter();
				parameter.setName("");
				selectedParameters.add(parameter);
			}
		}
	}

	@Override
	public void saveTypeOfParameter() {

		TypeOfParameter newType = this.newType;

		if (newType != null) {
			if (newType.getName() != null) {
				FacesContext fc = FacesContext.getCurrentInstance();
				ELContext elContext =
						FacesContext.getCurrentInstance().getELContext();
				TypeOfParameterService typeOfParameterService =
						(TypeOfParameterService) fc
								.getApplication()
								.getELResolver()
								.getValue(elContext, null,
										"typeOfParameterService");

				newType.setPrevType(selectedType);
				newType = typeOfParameterService.save(newType);
				if (selectedType != null) {
					selectedType.getNextTypes().add(newType);
				}
				if (!availableTypes.contains(newType)) {
					availableTypes.add(newType);
				}
				menuModel = createTypeMenu(availableTypes);
			}
		}

		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, new FacesMessage("Тип параметров спектра сохранен",
				"Тип параметра: " + newType.getName()));
	}

	@Override
	public void removeSelected(int selectedIndex) {

		// предварительно устанавливаем текущий тип параметров на тип идущий
		// перед убираемым списком параметров.
		// TODO тут есть косяк, исправить
		/*
		 * описание косяка: если будет идти несколько вподряд идущих пустых
		 * списков доступных параметров, то при удалении первого пустого списка
		 * менюшка выбора типа параметра будет от последнего списка, походу,
		 * чтобы исправить этот баг, надо вводить еще один список для всех
		 * используемых типов параметров, а не только для последнего, иначе
		 * никак не получить тип конкретного пустого списка параметров.
		 */
		TypeOfParameter prevType = null;
		if (selectedParameters.get(selectedIndex) != null) {
			prevType =
					selectedParameters.get(selectedIndex).getType()
							.getPrevType();
		} else {
			prevType = selectedType.getPrevType();
		}

		selectedType = prevType;

		// убираем выбранный параметр из списка выбранных для данного измерения
		// параметров, и, параллельно, убираем список доступных параметров.
		for (int i = selectedIndex; i < selectedParameters.size();) {
			selectedParameters.remove(i);
			availableParameterLists.remove(i);
		}

		// создаем список следующих доступных типов параметров.
		List<TypeOfParameter> availableTypes =
				initNextAvailableTypes(selectedType);
		this.availableTypes = availableTypes;

		menuModel = createTypeMenu(availableTypes);
	}

	@Override
	public List<Standard> getStandards() {
		return standards;
	}

	@Override
	public void setStandards(List<Standard> standards) {
		this.standards = standards;
	}

	@Override
	public Standard getSelectedStandard() {
		return selectedStandard;
	}

	@Override
	public void setSelectedStandard(Standard selectedStandard) {
		this.selectedStandard = selectedStandard;
	}

	@Override
	public List<Norm> getNorms() {
		return norms;
	}

	@Override
	public void setNorms(List<Norm> norms) {
		this.norms = norms;
	}

	@Override
	public Norm getSelectedNorm() {
		return selectedNorm;
	}

	@Override
	public List<NormHandler> getNormHandlers() {
		return normHandlers;
	}

	@Override
	public void setNormHandlers(List<NormHandler> normHandlers) {
		this.normHandlers = normHandlers;
	}

	@Override
	public NormHandler getSelectedNormHandler() {
		return selectedNormHandler;
	}

	@Override
	public void setSelectedNormHandler(NormHandler selectedNormHandler) {
		this.selectedNormHandler = selectedNormHandler;
	}

	@Override
	public void setSelectedNorm(Norm selectedNorm) {
		this.selectedNorm = selectedNorm;
	}

	@Override
	public List<Limit> getLimits() {
		return limits;
	}

	@Override
	public void setLimits(List<Limit> limits) {
		this.limits = limits;
	}

	@Override
	public TypeOfParameter getNewType() {
		return newType;
	}

	@Override
	public void setNewType(TypeOfParameter newType) {
		this.newType = newType;
	}

	@Override
	public TypeOfParameter getSelectedType() {
		return selectedType;
	}

	@Override
	public void setSelectedType(TypeOfParameter selectedType) {
		this.selectedType = selectedType;
	}

	@Override
	public List<TypeOfParameter> getAvailableTypes() {
		return availableTypes;
	}

	@Override
	public void setAvailableTypes(List<TypeOfParameter> availableTypes) {
		this.availableTypes = availableTypes;
	}

	@Override
	public MenuModel getMenuModel() {
		return menuModel;
	}

	@Override
	public void setMenuModel(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	@Override
	public List<Parameter> getSelectedParameters() {
		return selectedParameters;
	}

	@Override
	public void setSelectedParameters(List<Parameter> selectedParameters) {
		this.selectedParameters = selectedParameters;
	}

	@Override
	public List<List<Parameter>> getAvailableParameterLists() {
		return availableParameterLists;
	}

	@Override
	public void setAvailableParameterLists(
			List<List<Parameter>> availableParameterLists) {
		this.availableParameterLists = availableParameterLists;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public boolean isStandardSelected() {
		if (selectedStandard != null) {
			selectedNorm.setStandard(selectedStandard);
			return true;
		} else {
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Не выбран стандарт", "Чтобы продолжить выберите стандарт"));
			return false;
		}
	}

	@Override
	public boolean isParameterSelected() {
		if (selectedParameters != null && !selectedParameters.isEmpty()) {
			return true;
		} else {
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Не выбраны параметры", "Выберите проверяемые параметры"));
			return false;
		}
	}

	@Override
	public boolean isNormHandlerSelected() {
		if (selectedNorm.getNormHandler() != null) {
			return true;
		} else {
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Не выбран обработчик норм", "Выберите обработчик из списка"));
			return false;
		}
	}

	@Override
	public void newNorm() {
		if (selectedNorm == null) {
			selectedNorm = new Norm();
			selectedNorm.setStandard(selectedStandard);
			List<Limit> limits = new ArrayList<Limit>();
			this.limits = limits;
			selectedNorm.setLimits(limits);
		} else {
			selectedStandard = selectedNorm.getStandard();
		}
	}

	@Override
	public void initLimits() {
		this.limits = selectedNorm.getLimits();
		Limit newLimit = new Limit();
		this.limits.add(newLimit);
	}

	@Override
	public void addPatameters() {
		selectedNorm.setParameters(selectedParameters);
	}

	@Override
	public void addNormHandler() {
	}

	@Override
	public void onCellEdit(CellEditEvent event) {
        	if (event.getColumn().getClientId().contains("ampl")) {
    			Limit newLimit = new Limit();
    			this.limits.add(newLimit);
    		}
	}

	@Override
	public void addLimitLineBefore(Limit limit) {
		System.out.println("add line " + limit);
		if (limit != null) {
			int limitIndex = limits.indexOf(limit);
			Limit newLimit = new Limit();
			limits.add(limitIndex, newLimit);
		} else {
			Limit newLimit = new Limit();
			limits.add(newLimit);
		}
	}

	@Override
	public void addLimitLineAfter(Limit limit) {
		System.out.println("add line " + limit);
		if (limit != null) {
			int limitIndex = limits.indexOf(limit);
			Limit newLimit = new Limit();
			limits.add(limitIndex + 1 , newLimit);
		} else {
			Limit newLimit = new Limit();
			limits.add(newLimit);
		}
	}

	@Override
	public void delLimitLine(Limit limit) {
		System.out.println("del line " + limit);
		this.limits.remove(limit);
	}

	@Override
	public void confirmLimits() {
		List<Limit> newLimits = new ArrayList<Limit>();
		for (Limit limit : this.limits) {
			if (limit.getAmplitude() != null && limit.getFrequency() != null) {
				newLimits.add(limit);
				System.out.println("fr: " + limit.getFrequency() + " , amppl: "
						+ limit.getAmplitude());

			}
		}
		this.selectedNorm.setLimits(newLimits);
	}

	@Override
	public void initParameters() {
		if (selectedNorm.getParameters() != null) {
			selectedParameters = selectedNorm.getParameters();
			this.initSelected(selectedNorm);
		}
	}

	private void initSelected(Norm selectedNorm) {
		selectedParameters = new ArrayList<Parameter>();
		availableParameterLists = new ArrayList<List<Parameter>>();

		// получаем параметры из последнего измеренного спектра
		List<Parameter> parameters = selectedNorm.getParameters();
		for (Parameter parameter : parameters) {
			Parameter selectedParameter = new Parameter();
			selectedParameter.setName(parameter.getName());
			selectedParameters.add(selectedParameter);

		}

		TypeOfParameter type = null;

		for (Parameter parameter : parameters) {
			type = parameter.getType();
			List<Parameter> availableParameter = type.getParameters();
			availableParameterLists.add(availableParameter);
			selectedType = type;
		}

		List<TypeOfParameter> availableTypes = initNextAvailableTypes(selectedType);
		this.availableTypes = availableTypes ;

		menuModel = createTypeMenu(availableTypes);

	}

	@Override
	public boolean isNormSelected() {
		if (selectedNorm != null) {
			selectedStandard = selectedNorm.getStandard();
			return true;
		} else {
			return false;
		}

	}


}
