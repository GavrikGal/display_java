package com.kbdisplay.ls1710.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.service.data.ModelService;

@FacesConverter("modelConverter")
public class ModelOfEqupmentConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            ModelService service = (ModelService) fc.getExternalContext().getApplicationMap().get("modelService");
            return service.findAll().get(Integer.parseInt(value));
        }
        else {
            return null;
        }
    }

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((ModelOfEquipment) object).getModelName());
        }
        else {
            return null;
        }
    }

}
