package org.strotmann.tcg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

public class Dta {
	
	static String pathname = null;
	
	ArrayList<Mitglied> abbucher = new ArrayList <Mitglied> ();
	long summeBtr = 0;
	long summeKto = 0;
	long summeBlz = 0;
	
	private static ResourceBundle beitragProps = ResourceBundle.getBundle("Beitrag");
	
	private static ResourceBundle bankProps = ResourceBundle.getBundle("Bank");
	String tcg27 = StringUtils.rightPad(bankProps.getString("ktoInh"),27);
	String tcgBlz = bankProps.getString("blz");
	String tcgKto = bankProps.getString("kto");
	
		
	public Dta(List<Mitglied> mitglieder) {
		super();
		for (Mitglied mitglied : mitglieder) {
			if (mitglied.getZahlungsart() == 1
			 && mitglied.getBeitragsklasse() < 9)
				abbucher.add(mitglied);
		}
	}

	public String erzeugeDta () {
		BufferedWriter dtaDatei = outFile ("dta");
		try {
		      dtaDatei.write(aSatz());
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Mitglied a : abbucher) {
			try {
				dtaDatei.write(cSatz(a));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		try {
			dtaDatei.write(eSatz());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			dtaDatei.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return pathname+" Sätze:  "+abbucher.size()+", Summe: "+summeBtr;
	}
	
	private static BufferedWriter outFile (String objName) {
		
	    BufferedWriter retBuf = null;
	    
		try {
			
			File file = new File (objName);
			pathname = file.getAbsolutePath();
			
			FileOutputStream fileOutputStream = new FileOutputStream (file);
			OutputStreamWriter outpuStreamWriter = null;
			try {
				outpuStreamWriter = new OutputStreamWriter (fileOutputStream, "UTF-8");
			} catch (UnsupportedEncodingException ex) {
				System.out.println("Encoding nicht gefunden");
			}
			retBuf = new BufferedWriter(outpuStreamWriter); 
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		return retBuf;
	}
	
	private String aSatz () {
		String s01_70  = "0128ALK"+tcgBlz+"00000000"+tcg27+erstellDat()+"    "+tcgKto;
		String s71_128 = "0000000000                                               1";
		return s01_70 + s71_128;
	}
	
	private String cSatz (Mitglied m) {
		String blz = StringUtils.leftPad((new Long (m.getBankleitzahl()).toString()),8,'0');
		summeKto = summeKto + m.getKontonummer();
		String kto = StringUtils.leftPad((new Long (m.getKontonummer()).toString()),10,'0');
		summeBlz = summeBlz + m.getBankleitzahl();
		String s = beitragProps.getString(new Short(m.getBeitragsklasse()).toString());
		String btrKl = s.split(":")[0];
		String beitrag = s.split(":")[1];
		summeBtr = summeBtr + new Integer(beitrag);
		String btr = StringUtils.leftPad((beitrag + "00"),11,'0');
		String nam = StringUtils.abbreviate(StringUtils.rightPad((ktoInh(m)),27),27);
		String verw = StringUtils.abbreviate(StringUtils.rightPad(beitrJahr()+btrKl,27),27).toUpperCase();
		String filler = "00                           00                                      ";
		
		return "0187C44050199"+blz+kto+"000000000000005000 00000000000"+tcgBlz+tcgKto+btr+"   "+nam+"        "+tcg27+verw+"1  00"+filler;
	}
	
	private String eSatz () {
		String anz = StringUtils.leftPad (new Integer(abbucher.size()).toString(), 7, "0");
		String ktoSum = StringUtils.leftPad(new Long (summeKto).toString(), 17, "0");
		String blzSum = StringUtils.leftPad(new Long (summeBlz).toString(), 17, "0");
		String btrSum = StringUtils.leftPad(new Long (summeBtr * 100).toString(), 13, "0");
		String s01  = "0128E     "+anz+"0000000000000"+ktoSum+blzSum+btrSum;
		String s02  = "                                                   ";
		return s01+s02;
	}
	
	private String ktoInh (Mitglied m) {
		String rS;
		if (m.getKontoinhaber() != null && m.getKontoinhaber().length() > 0)
			rS = m.getKontoinhaber().toUpperCase();
		else
			rS = (m.getVorname()+" "+m.getZuname()).toUpperCase();
		return rS.replaceAll("Ä", "AE").replaceAll("Ü", "UE").replaceAll("Ö", "OE").replaceAll("ß", "SS").replaceAll("É", "E");	
	}
	
	@SuppressWarnings("deprecation")
	private String erstellDat () {
		Calendar cal = Calendar.getInstance();
		Date d = cal.getTime();
		String h = d.toLocaleString().split(" ")[0];
		return h.substring(0, 2)+h.substring(3, 5)+h.substring(8, 10);
	}
	
	private String beitrJahr () {
		Calendar cal = Calendar.getInstance();
		String yyyy = new Integer (cal.get(Calendar.YEAR)).toString();
		return "BEITR "+yyyy+" ";
	}
	
}
