package com.kbdisplay.ls1710.view.dataJournal.web;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.el.ELContext;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSeparator;
import org.primefaces.model.menu.MenuModel;
import org.springframework.faces.security.FaceletsAuthorizeTagUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;

import com.kbdisplay.ls1710.domain.Measurement;
import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.domain.TypeOfParameter;
import com.kbdisplay.ls1710.service.data.TypeOfParameterService;
import com.kbdisplay.ls1710.view.dataJournal.EditData;
import com.kbdisplay.ls1710.view.dataJournal.EditForm;
import com.kbdisplay.ls1710.view.dataJournal.Row;
import com.kbdisplay.ls1710.view.dataJournal.web.component.EditFormData;

/**
 * представление формы для добавления/редактирования списка измерений.
 *
 * @author Gavrik
 *
 */
// @Component("editFormDataJournalView")
@ManagedBean
@ViewScoped
public class DataJournalEditForm implements Serializable, EditForm {

	/**
	 * серийный номер класса.
	 */
	private static final long serialVersionUID = 141495139686315409L;

	/**
	 * данные формы.
	 */
	private EditData data;

	/**
	 * отображать ли диалог добавления новой модели.
	 */
	private boolean showNewModelDialog;

	/**
	 * конструктор по умолчанию.
	 */
	public DataJournalEditForm() {
		data = new EditFormData();
		showNewModelDialog = false;
	}

	/**
	 * инициализация формы редактирования.
	 *
	 * @param lastMeasurement
	 *            - последнее известное измерение из БД.
	 */
	@Override
	public void init(final Measurement lastMeasurement) {

		data.init();

		if (lastMeasurement != null) {
			data.initSelected(lastMeasurement);
		}

		List<TypeOfParameter> availableTypes = initNextAvailableTypes(data
				.getSelectedType());
		data.setAvailableTypes(availableTypes);

		data.setMenuModel(createTypeMenu(availableTypes));

	}

	/**
	 * обработчик события, когда по строке совершен двойной клик.
	 *
	 * @param event
	 *            - событие двойного клика.
	 */
	@PreAuthorize(value="hasAuthority('ROLE_USER')")
	@Override
	public void onRowDbSelect(final SelectEvent event) {
		//TODO заменить сраный if на аннотацию (чтоб они только заработали падлы)
		System.out.println("Пробую...");
		try {
			if (FaceletsAuthorizeTagUtils.areAllGranted("ROLE_USER")) {
				Row row = (Row) event.getObject();
				Measurement selected = row.getMeasurement();
				edit(selected);
				System.out.println("xopowo " + SecurityContextHolder.getContext().getAuthentication().getName());
			} else {
				System.out.println("xepoBo");
			}
		} catch (IOException e) {
			// TODO Автоматически созданный блок catch
			e.printStackTrace();
		}
	}

	/**
	 * установить в форме редактирования выделеное измерение.
	 *
	 * @param selected
	 *            - редактируемое измерение.
	 */
	@Override
	public void edit(final Measurement selected) {
		data.setModel(selected.getEquipment().getModel());
		data.setSerialNumber(selected.getEquipment().getSerialNumber());

		if (selected.getNextMeasurement() != null) {
			Measurement lastMeasurement = selected.getNextMeasurement();
			while (lastMeasurement.getNextMeasurement() != null) {
				lastMeasurement = lastMeasurement.getNextMeasurement();
			}
			data.setPurposeOfMeasurement(lastMeasurement.getPurpose());
			data.initSelected(lastMeasurement);
		} else {
			data.setPurposeOfMeasurement(selected.getPurpose());
			data.initSelected(selected);
		}
	}

	/**
	 * выбрать тип параметра из меню доступных типов.
	 *
	 * @param itemN
	 *            - номер типа из меню доступных типов.
	 */
	@Override
	public void selectTypeOfParameter(String itemN) {
		TypeOfParameter selectedType = data.getAvailableTypes().get(
				Integer.parseInt(itemN));
		data.setSelectedType(selectedType);
		List<TypeOfParameter> availableTypes = initNextAvailableTypes(data
				.getSelectedType());
		data.setAvailableTypes(availableTypes);
		MenuModel menuModel = createTypeMenu(availableTypes);
		data.setMenuModel(menuModel);

		if (selectedType != null) {

			List<Parameter> availableParameters = selectedType.getParameters();

			if ((availableParameters != null)
					&& (!availableParameters.isEmpty())) {
				data.getAvailableParameterLists().add(availableParameters);
				data.getSelectedParameters().add(availableParameters.get(0));
			} else {
				data.getAvailableParameterLists().add(
						new ArrayList<Parameter>());
				Parameter parameter = new Parameter();
				parameter.setName("");
				data.getSelectedParameters().add(parameter);
			}
		}
	}

	/**
	 * создать меню доступных типов параметров спектра.
	 *
	 * @param availableTypes
	 *            - доступные типы параметров.
	 * @return меню доступных типов.
	 */
	private MenuModel createTypeMenu(List<TypeOfParameter> availableTypes) {
		MenuModel menuModel = new DefaultMenuModel();
		if (availableTypes != null && !availableTypes.isEmpty()) {

			for (int i = 0; i < availableTypes.size(); i++) {
				DefaultMenuItem item = new DefaultMenuItem(availableTypes
						.get(i).getName());
				item.setCommand("#{editFormDJView.selectTypeOfParameter('" + i
						+ "')}");
				item.setUpdate("spectrumParameters spectrumParametersMenu spectrumParameterscontextMenu");

				menuModel.addElement(item);
			}
			menuModel.addElement(new DefaultSeparator());
		}

		DefaultMenuItem item = new DefaultMenuItem("Новый тип параметра");

		item.setUpdate("spectrumParameters spectrumParametersMenu spectrumParameterscontextMenu :messages:message");
		item.setOnclick("PF('newTypeOfParameterWV').show()");

		menuModel.addElement(item);
		return menuModel;
	}

	/**
	 * сохранить товый тип параметра.
	 */
	@Override
	public void saveTypeOfParameter() {

		TypeOfParameter newType = data.getNewType();

		if (newType != null) {
			if (newType.getName() != null) {
				FacesContext fc = FacesContext.getCurrentInstance();
				ELContext elContext = FacesContext.getCurrentInstance()
						.getELContext();
				TypeOfParameterService typeOfParameterService = (TypeOfParameterService) fc
						.getApplication().getELResolver()
						.getValue(elContext, null, "typeOfParameterService");

				newType.setPrevType(data.getSelectedType());
				newType = typeOfParameterService.save(newType);
				if (data.getSelectedType() != null) {
					data.getSelectedType().getNextTypes().add(newType);
				}
				if (!data.getAvailableTypes().contains(newType)) {
					data.getAvailableTypes().add(newType);
				}
				data.setMenuModel(createTypeMenu(data.getAvailableTypes()));
			}
		}

		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, new FacesMessage("Тип параметров спектра сохранен",
				"Тип параметра: " + newType.getName()));
	}

	/**
	 * убрать список доступных типов параметров. Также удалить и следующие за
	 * ним списки, так как они теряют актуальность при удалении текущего списка.
	 *
	 * @param selectedIndex
	 *            - номер списка в коллекции всех списков доступных параметров.
	 */
	@Override
	public void removeSelected(int selectedIndex) {

		// предварительно устанавливаем текущий тип параметров на тип идущий
		// перед убираемым списком параметров.
		// TODO тут есть косяк, исправить
		/*
		 * описание косяка:
		 * если будет идти несколько вподряд идущих пустых списков доступных
		 * параметров, то при удалении первого пустого списка менюшка выбора
		 * типа параметра будет от последнего списка, походу, чтобы исправить
		 * этот баг, надо вводить еще один список для всех используемых типов
		 * параметров, а не только для последнего, иначе никак не получить тип
		 * конкретного пустого списка параметров.
		 */
		TypeOfParameter prevType = null;
		if (data.getSelectedParameters().get(selectedIndex) != null) {
			prevType = data.getSelectedParameters().get(selectedIndex)
					.getType().getPrevType();
		} else {
			prevType = data.getSelectedType().getPrevType();
		}

		data.setSelectedType(prevType);

		// убираем выбранный параметр из списка выбранных для данного измерения
		// параметров, и, параллельно, убираем список доступных параметров.
		for (int i = selectedIndex; i < data.getSelectedParameters().size();) {
			data.getSelectedParameters().remove(i);
			data.getAvailableParameterLists().remove(i);
		}

		// создаем список следующих доступных типов параметров.
		List<TypeOfParameter> availableTypes = initNextAvailableTypes(data
				.getSelectedType());
		data.setAvailableTypes(availableTypes);

		data.setMenuModel(createTypeMenu(availableTypes));
	}

	private List<TypeOfParameter> initNextAvailableTypes(
			TypeOfParameter currentType) {
		List<TypeOfParameter> availableTypes = null;

		if (currentType == null) {
			FacesContext fc = FacesContext.getCurrentInstance();
			ELContext elContext = FacesContext.getCurrentInstance()
					.getELContext();
			TypeOfParameterService typeOfParameterService = (TypeOfParameterService) fc
					.getApplication().getELResolver()
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

	// геттеры и сеттеры.
	@Override
	public EditData getData() {
		return data;
	}

	@Override
	public void setData(final EditData data) {
		this.data = data;
	}

	@Override
	public boolean isShowNewModelDialog() {
		return showNewModelDialog;
	}

	@Override
	public void setShowNewModelDialog(final boolean showNewModelDialog) {
		this.showNewModelDialog = showNewModelDialog;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
