package com.kbdisplay.ls1710.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.TypeOfSpectrum;
import com.kbdisplay.ls1710.service.data.TypeOfSpectrumService;

/**
 * конвертирует строку в тип спектра (си или ирп).
 *
 * @author Gavrik
 *
 */
@Component("typeOfSpectrumConverter")
public class TypeOfSpectrumConverter implements Converter {

	/**
	 * сервис получения типа спектра из БД.
	 */
	@Autowired
	private TypeOfSpectrumService typeOfSpectrumService;


	@Override
	public Object getAsObject(final FacesContext fc, final UIComponent uic,
			final String value) {
		if (value != null && value.trim().length() > 0) {
			return typeOfSpectrumService.findByName(value.toString());
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(final FacesContext fc, final UIComponent uic,
			final Object object) {
		if (object != null) {
			return String.valueOf(((TypeOfSpectrum) object).getName());
		} else {
			return null;
		}
	}

}
