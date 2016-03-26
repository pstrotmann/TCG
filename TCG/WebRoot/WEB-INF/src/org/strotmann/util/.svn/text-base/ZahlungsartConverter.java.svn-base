package org.strotmann.util;

import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class ZahlungsartConverter implements Converter {
	
	private static ResourceBundle zahlungsartProps = ResourceBundle.getBundle("Zahlungsart");

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
			throws ConverterException {
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ConverterException {
		if (arg2 == null) 
			return null;

		Short g = (Short)arg2;
		String s = zahlungsartProps.getString(g.toString());
		return s;
	}

}
