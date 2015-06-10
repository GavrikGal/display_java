package com.kbdisplay.ls1710.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.ModelType;
import com.kbdisplay.ls1710.service.data.ModelTypeService;

@Component("modelTypeConverter")
public class ModelTypeConverter implements Converter {

	@Autowired
	private ModelTypeService modelTypeService;

	@Override
	public Object getAsObject(final FacesContext fc, final UIComponent uic,
			final String value) {
		if (value != null && value.trim().length() > 0) {
			ModelType modelType =
					modelTypeService.findByName(value.toString());
			if (modelType != null) {
				System.out.println(modelType.getName());
				System.out.println(modelType.getId());
			} else {
				System.out.println("fuck");
			}
			return modelType;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(final FacesContext fc, final UIComponent uic,
			final Object object) {
		if (object != null) {
			return String.valueOf(((ModelType) object).getName());
		} else {
			return null;
		}
	}

}
