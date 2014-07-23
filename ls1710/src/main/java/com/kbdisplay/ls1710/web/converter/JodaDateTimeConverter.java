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

	//private static final String PATTERN = "dd.MM.yyyy";
	
	private String pattern = "dd-MM-yyyy";
	
	
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
		String pattern = (String) component.getAttributes().get("pattern");
		if (pattern == null) {
			pattern = this.pattern;
		}
		return DateTimeFormat.forPattern(pattern).parseDateTime(value);
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent component, Object value) {
		
		String pattern = (String) component.getAttributes().get("pattern");
		if (pattern == null) {
			pattern = this.pattern;
		}
		
		DateTime dateTime = (DateTime) value;
		
		return DateTimeFormat.forPattern(pattern).print(dateTime);
	}	
	
}
