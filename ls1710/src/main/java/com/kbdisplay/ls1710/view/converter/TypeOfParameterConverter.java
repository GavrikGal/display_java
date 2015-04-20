package com.kbdisplay.ls1710.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.TypeOfParameter;
import com.kbdisplay.ls1710.service.data.TypeOfParameterService;

@Component("typeOfParameterConverter")
public class TypeOfParameterConverter implements Converter {

	private TypeOfParameterService typeOfParameterService;

	@Override
	public Object getAsObject(final FacesContext fc, final UIComponent uic,
			final String value) {
		if (value != null && value.trim().length() > 0) {
			return typeOfParameterService.findByName(value.trim().toString());
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(final FacesContext fc, final UIComponent uic,
			final Object object) {
		if (object != null) {
			return String.valueOf(((TypeOfParameter) object).getName());
		} else {
			return null;
		}
	}

}
