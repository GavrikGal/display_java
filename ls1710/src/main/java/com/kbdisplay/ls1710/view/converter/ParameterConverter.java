package com.kbdisplay.ls1710.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.Parameter;
import com.kbdisplay.ls1710.service.data.ParameterService;

@Component("parameterConverter")
public class ParameterConverter implements Converter {

	@Autowired
	private ParameterService parameterService;

	@Override
	public Object getAsObject(final FacesContext fc, final UIComponent uic,
			final String value) {
		if (value != null && value.trim().length() > 0) {
			return parameterService.findByName(value.toString());
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(final FacesContext fc, final UIComponent uic,
			final Object object) {
		if (object != null) {
			return String.valueOf(((Parameter) object).getName());
		} else {
			return null;
		}
	}

}
