package com.kbdisplay.ls1710.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.ModelOfEquipment;
import com.kbdisplay.ls1710.service.data.ModelService;

/**
 * конвертирует строку с названием модели в модель изделия.
 *
 * @author Gavrik
 *
 */
@Component("modelConverter")
public class ModelOfEqupmentConverter implements Converter {

	/**
	 * сервис получения модели из БД.
	 */
	@Autowired
	private ModelService modelService;


	@Override
	public Object getAsObject(final FacesContext fc, final UIComponent uic,
			final String value) {
		if (value != null && value.trim().length() > 0) {
			ModelOfEquipment model =
					modelService.findByModelName(value.toString());
			if (model == null) {
				model = new ModelOfEquipment();
				model.setModelName(value.toString());
				model = modelService.save(model);
			}
			return model;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(final FacesContext fc, final UIComponent uic,
			final Object object) {
		if (object != null) {
			return String.valueOf(((ModelOfEquipment) object).getModelName());
		} else {
			return null;
		}
	}

}
