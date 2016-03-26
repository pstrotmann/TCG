package org.strotmann.tcg;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MitgliederImporter {
	
	private MitgliedPersister mitgliedPersister = null;
	private BufferedReader mitglieder = null;
	
	public void importiere() {
		
		mitgliedPersister = new MitgliedPersisterDB4Objects();
		EmailImporter emailImporter  = new EmailImporter ();
		emailImporter.importiere();
	
	try {
		//FileInputStream fileInputStream = new FileInputStream ("D:\\workspaces\\workspace\\TCG\\altDaten\\ABCSISTA.DAT");
		FileInputStream fileInputStream = new FileInputStream ("/home/peter/workspaces/tcg/TCG/altDaten/abcsista.dat");
		InputStreamReader inputStreamReader = null;
		
		try{
			inputStreamReader = new InputStreamReader (fileInputStream, "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			System.out.println("Encoding nicht gefunden");
		}
		
		mitglieder = new BufferedReader(inputStreamReader); 
		
		} catch(FileNotFoundException e) {System.out.println("Altdaten nicht gefunden");}
	try {
		while (mitglieder.ready()) {
			String s = mitglieder.readLine();
			if (s.substring(0, 16).endsWith("****ABC 9002****") )
				break;
			if (!s.substring(354, 364).equals("00.00.0000") & !s.substring(360, 364).equals("2007"))
				continue;
			Mitglied m = new Mitglied();
			m.setVorname(s.substring(49, 72).trim());
			m.setZuname(s.substring(73, 120).trim());
			String eMail = emailImporter.sucheEmail(m.getZuname(), m.getVorname());
			if (eMail != null) {
				m.setEmail(eMail.trim());
				emailImporter.removeEntry(m.getZuname()+m.getVorname());
			}
				
			String t = s.substring(121, 145);
			Object[] o = strassenString(t);
			m.setStrasse(((String)o[0]).trim());
			m.setHausnummer((Integer)o[1]);
			if (o[2] != null)
				m.setHausnummerZus(((String)o[2]).trim());
			try {
				m.setPostleitzahl(Integer.parseInt(s.substring(145, 150)));
			} catch (NumberFormatException e) {
				m.setPostleitzahl(0);
			}
			m.setOrt(s.substring(150, 174).trim());
			m.setGeburtsdatum(stringToDate(s.substring(229, 239)));
			
			m.setGeschlecht(s.substring(239, 240).charAt(0));
			
			m.setFestnetz(s.substring(243, 258).trim());
			m.setDienstanschluss(s.substring(258, 273).trim());
			m.setHandy(s.substring(288, 303).trim());
			m.setEintrittsdatum(stringToDate(s.substring(343, 353)));
			if (!s.substring(354, 364).equals("00.00.0000"))
				m.setAustrittsdatum(stringToDate(s.substring(354, 364)));
			if (s.substring(486, 487).equals(" ")) {
				m.setBeitragsklasse(Short.parseShort("0"));
				m.setZahlungsart(Short.parseShort("0"));
				m.setKontonummer(0);
				m.setBankleitzahl(0);
				m.setKontoinhaber("");
			}
			else {
				switch (s.substring(486, 487).charAt(0)) {
				case 'a': m.setBeitragsklasse(Short.parseShort("1"));break;
				case 'b': m.setBeitragsklasse(Short.parseShort("2"));break;
				case 'c': m.setBeitragsklasse(Short.parseShort("3"));break;
				case 'd': m.setBeitragsklasse(Short.parseShort("4"));break;
				case 'g': m.setBeitragsklasse(Short.parseShort("5"));break;
				case 'h': m.setBeitragsklasse(Short.parseShort("6"));break;
				case 'i': m.setBeitragsklasse(Short.parseShort("7"));break;
				case 'k': m.setBeitragsklasse(Short.parseShort("9"));break;
					
				default:
					break;
				}
				
				switch (s.substring(376, 377).charAt(0)) {
				case 'l': m.setZahlungsart(Short.parseShort("1"));break;
				case 'b': m.setZahlungsart(Short.parseShort("2"));break;
				case 'r': m.setZahlungsart(Short.parseShort("3"));break;
				default:
					break;
				}
				if (m.getZahlungsart() == 1)
				{
					try {
						m.setKontonummer(Long.parseLong(s.substring(377, 387).trim()));
					} catch (NumberFormatException e) {
						System.out.println(s.substring(377, 387));
					}
					try {
						m.setBankleitzahl(Long.parseLong(s.substring(387, 395).trim()));
					} catch (NumberFormatException e) {
						System.out.println(s.substring(387, 395));
					}
					m.setKontoinhaber(s.substring(395, 425).trim());
				}
				
			}
			
			m.setKzA(s.substring(713, 714));
			
			this.mitgliedPersister.store(m);
		}
		mitglieder.close();
		emailImporter.listEmail();
	} catch (IOException e) {
		e.printStackTrace();
	}
	
	}
	
	private Date stringToDate (String d) {
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		
		Date date = null;
		
		try {
			date = df.parse(d);
		} catch (ParseException e) { //do nothing
		}
		return date;
	}
	
	private Object[] strassenString (String s) {
		Object[] o = {null, null, null};
		String hNr = null; 
		String t[]; 
		t = s.split ("[^0-9]+");
		if (t.length >= 2)
			hNr = t[1];
		if (hNr == null) {
			o[0] = s;
			o[1] = 0;
			}
		else {
			t = s.split(hNr);
			o[0] = t[0];
			o[1] = Integer.parseInt(hNr);
			o[2] = t[1];
			}
		return o;
	}
}
