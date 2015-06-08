package com.kbdisplay.ls1710.view.settings;

import java.util.List;

import org.primefaces.event.CellEditEvent;
import org.primefaces.model.menu.MenuModel;

import com.kbdisplay.ls1710.domain.Limit;
import com.kbdisplay.ls1710.domain.Norm;
import com.kbdisplay.ls1710.domain.NormHandler;
import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.domain.Standard;
import com.kbdisplay.ls1710.domain.TypeOfParameter;

public interface NormsSetting {

	public void init();

	public void selectTypeOfParameter(String itemN);

	public void saveTypeOfParameter();

	public void removeSelected(int selectedIndex);

	public List<Standard> getStandards();

	public void setStandards(List<Standard> standards);

	public Standard getSelectedStandard();

	public void setSelectedStandard(Standard selectedStandard);

	public List<Norm> getNorms();

	public void setNorms(List<Norm> norms);

	public Norm getSelectedNorm();

	public void setSelectedNorm(Norm selectedNorm);

	public List<NormHandler> getNormHandlers();

	public void setNormHandlers(List<NormHandler> normHandlers);

	public NormHandler getSelectedNormHandler();

	public void setSelectedNormHandler(NormHandler selectedNormHandler);

	public List<Limit> getLimits();

	public void setLimits(List<Limit> limits);

	public TypeOfParameter getNewType();

	public void setNewType(TypeOfParameter newType);

	public TypeOfParameter getSelectedType();

	public void setSelectedType(TypeOfParameter selectedType);

	public List<TypeOfParameter> getAvailableTypes();

	public void setAvailableTypes(List<TypeOfParameter> availableTypes);

	public MenuModel getMenuModel();

	public void setMenuModel(MenuModel menuModel);

	public List<Parameter> getSelectedParameters();

	public void setSelectedParameters(List<Parameter> selectedParameters);

	public List<List<Parameter>> getAvailableParameterLists();

	public void setAvailableParameterLists(
			List<List<Parameter>> availableParameterLists);

	public void newNorm();

	public boolean isStandardSelected();

	public boolean isParameterSelected();

	public boolean isNormHandlerSelected();

	public void addPatameters();

	public void addNormHandler();

	public void onCellEdit(CellEditEvent event);

	public void initLimits();

	public void confirmLimits();

	public void addLimitLineBefore(Limit limit);

	public void addLimitLineAfter(Limit limit);

	public void delLimitLine(Limit limit);

	public void initParameters();

	public boolean isNormSelected();
}
