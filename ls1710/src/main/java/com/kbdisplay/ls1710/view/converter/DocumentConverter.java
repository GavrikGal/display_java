package com.kbdisplay.ls1710.view.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kbdisplay.ls1710.domain.Document;
import com.kbdisplay.ls1710.service.data.DocumentService;


@Component("documentConverter")
public class DocumentConverter implements Converter {

	@Autowired
	private DocumentService documentService;

	@Override
	public Object getAsObject(final FacesContext fc, final UIComponent uic,
			final String value) {
		if (value != null && value.trim().length() > 0) {
			Document document =
					documentService.findByName(value.toString());
			return document;
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(final FacesContext fc, final UIComponent uic,
			final Object object) {
		if (object != null) {
			return String.valueOf(((Document) object).getName());
		} else {
			return null;
		}
	}

}
