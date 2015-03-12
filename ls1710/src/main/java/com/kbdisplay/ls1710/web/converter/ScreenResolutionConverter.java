package com.kbdisplay.ls1710.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.ScreenResolution;
import com.kbdisplay.ls1710.service.data.ScreenResolutionService;

/**
 * конвертирует строку в разрешение экрана.
 *
 * @author Gavrik
 *
 */
@Component("screenResolutionConverter")
public class ScreenResolutionConverter implements Converter {

	/**
	 * сервис получения разрешения экрана из БД.
	 */
	@Autowired
	private ScreenResolutionService screenResolutionService;


	@Override
	public Object getAsObject(final FacesContext fc, final UIComponent uic,
			final String value) {
		if (value != null && value.trim().length() > 0) {
			return screenResolutionService.findByResolution(value.toString());
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(final FacesContext fc, final UIComponent uic,
			final Object object) {
		if (object != null) {
			return String.valueOf(((ScreenResolution) object)
					.getResolution());
		} else {
			return null;
		}
	}

}
