package org.strotmann.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.model.SelectItem;

public class Auswahl {
	
	private static List aemter;
	private static ResourceBundle amtProps = ResourceBundle.getBundle("Amt");
	private static List geschlechter;
	private static ResourceBundle geschlechtProps = ResourceBundle.getBundle("Geschlecht");
	private static List zahlungsarten;
	private static ResourceBundle zahlungsartProps = ResourceBundle.getBundle("Zahlungsart");
	private static List beitragsklassen;
	private static ResourceBundle beitragProps = ResourceBundle.getBundle("Beitrag");
	    
	static {
		
		Enumeration<String> keys = null;
		
		aemter = new ArrayList();
		keys = amtProps.getKeys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			aemter.add(new SelectItem(Short.parseShort(key), amtProps.getString(key)));
		}
		
		geschlechter = new ArrayList();
		keys = geschlechtProps.getKeys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			geschlechter.add(new SelectItem(key.charAt(0), geschlechtProps.getString(key)));
		}
		
		zahlungsarten = new ArrayList();
		keys = zahlungsartProps.getKeys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			zahlungsarten.add(new SelectItem(Short.parseShort(key), zahlungsartProps.getString(key)));
		}
		
		beitragsklassen = new ArrayList();
		keys = beitragProps.getKeys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			beitragsklassen.add(new SelectItem(Short.parseShort(key), beitragProps.getString(key).split(":")[0]));
		}
	}

	public List getAemter() {
		Comparator comparator = new ShortComparator() ;
		Collections.sort (aemter, comparator);
		return aemter;
	}

	public void setAemter(List aemter) {
		Auswahl.aemter = aemter;
	}

	public List getGeschlechter() {
		Comparator comparator = new CharComparator() ;
		Collections.sort (geschlechter, comparator);
		return geschlechter;
	}

	public void setGeschlechter(List geschlechter) {
		Auswahl.geschlechter = geschlechter;
	}
	
	public List getZahlungsarten() {
		Comparator comparator = new ShortComparator() ;
		Collections.sort (zahlungsarten, comparator);
		return zahlungsarten;
	}

	public void setZahlungsarten(List zahlungsarten) {
		Auswahl.zahlungsarten = zahlungsarten;
	}
	
	public List getBeitragsklassen() {
		Comparator comparator = new ShortComparator() ;
		Collections.sort (beitragsklassen, comparator);
		return beitragsklassen;
	}

	public void setBeitragsklassen(List beitragsklassen) {
		Auswahl.beitragsklassen = beitragsklassen;
	}
	
	class CharComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			SelectItem s1 = (SelectItem) o1;
			SelectItem s2 = (SelectItem) o2;
			if ((Character)s1.getValue() < (Character)s2.getValue()) 
				return -1;
			else
				return 1;
		}
	}
	
	class ShortComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			SelectItem s1 = (SelectItem) o1;
			SelectItem s2 = (SelectItem) o2;
			if ((Short)s1.getValue() < (Short)s2.getValue()) 
				return -1;
			else
				return 1;
		}
	}
}
