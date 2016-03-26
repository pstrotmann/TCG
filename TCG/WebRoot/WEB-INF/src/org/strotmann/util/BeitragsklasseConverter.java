package org.strotmann.util;

import java.util.ResourceBundle;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class BeitragsklasseConverter implements Converter {
	
	private ResourceBundle beitragProps = ResourceBundle.getBundle("Beitrag");

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
			throws ConverterException {
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ConverterException {
		if (arg2 == null) 
			return null;

		Short g = (Short)arg2;
		String s = beitragProps.getString(g.toString());
		String beitragsklasse = s.split(":")[0];
		return beitragsklasse;
		
	}

}
