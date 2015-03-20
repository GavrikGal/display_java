package com.kbdisplay.ls1710.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.PurposeOfMeasurement;
import com.kbdisplay.ls1710.service.data.PurposeOfMeasurementService;

/**
 * конвертирует строку в цель измерения.
 *
 * @author Gavrik
 *
 */
@Component("purposeOfMeasurementConverter")
public class PurposeOfMeasurementConverter implements Converter {

	/**
	 * сервис получения целей измерений из БД.
	 */
	@Autowired
	private PurposeOfMeasurementService purposeOfMeasurementService;


	@Override
	public Object getAsObject(final FacesContext fc, final UIComponent uic,
			final String value) {
		if (value != null && value.trim().length() > 0) {
			return purposeOfMeasurementService.findByName(value.toString());
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(final FacesContext fc, final UIComponent uic,
			final Object object) {
		if (object != null) {
			return String.valueOf(((PurposeOfMeasurement) object).getName());
		} else {
			return null;
		}
	}

}
