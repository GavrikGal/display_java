/**
 * Created on Dec 29, 2011
 */
package com.kbdisplay.ls1710.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

/**
 * @author Clarence
 *
 */
@FacesConverter("jodaDataTimeConverter")
public class JodaDateTimeConverter implements Converter {

	// private static final String PATTERN = "dd.MM.yyyy";

	/**
	 * шаблон формата даты.
	 */
	private String pattern = "dd-MM-yyyy";


	public String getPattern() {
		return pattern;
	}

	public void setPattern(final String pattern) {
		this.pattern = pattern;
	}

	@Override
	public Object getAsObject(final FacesContext ctx,
			final UIComponent component, final String value) {
		String newPattern = (String) component.getAttributes().get("pattern");
		if (newPattern == null) {
			newPattern = this.pattern;
		}
		return DateTimeFormat.forPattern(newPattern).parseDateTime(value);
	}

	@Override
	public String getAsString(final FacesContext ctx,
			final UIComponent component, final Object value) {

		String newPattern = (String) component.getAttributes().get("pattern");
		if (newPattern == null) {
			newPattern = this.pattern;
		}

		DateTime dateTime = (DateTime) value;

		return DateTimeFormat.forPattern(newPattern).print(dateTime);
	}

}
