package com.kbdisplay.ls1710.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.Measurand;
import com.kbdisplay.ls1710.service.data.MeasurandService;

/**
 * конвертирует строку с в измеряемую величину.
 *
 * @author Gavrik
 *
 */
@Component("measurandConverter")
public class MeasurandConverter implements Converter {

	/**
	 * сервис получения измеряемой величины из БД.
	 */
	@Autowired
	private MeasurandService measurandService;


	@Override
	public Object getAsObject(final FacesContext fc, final UIComponent uic,
			final String value) {
		if (value != null && value.trim().length() > 0) {
			return measurandService.findById(value.toString());
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(final FacesContext fc, final UIComponent uic,
			final Object object) {
		if (object != null) {
			return String.valueOf(((Measurand) object).getIdMeasurands());
		} else {
			return null;
		}
	}

}
