package org.strotmann.util;

import java.util.Calendar;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class DatumConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2)
			throws ConverterException {
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ConverterException {
		if (arg2 == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime((Date)arg2);
		int tt = c.get(Calendar.DAY_OF_MONTH);
		int mm = c.get(Calendar.MONTH) + 1;
		int jjjj = c.get(Calendar.YEAR);
		return lPadZwei(tt)+"."+lPadZwei(mm)+"."+jjjj ;
	}

	private String lPadZwei (int i) {
		if (i < 10)
		   return "0"+i;
		else
		   return ""+i;	
	}
}
