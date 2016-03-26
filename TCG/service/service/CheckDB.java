package service;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.Date;

import org.strotmann.tcg.Mitglied;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;

public class CheckDB {
	
	private static BufferedWriter printoutAbw = null;
	private static BufferedWriter printoutNo = null;
	private static BufferedWriter printoutDup = null;
	static boolean abweichung = false;
	
	public static void main(String[] args) {
		
		int anzNicht = 0;
		String nameAlt = "";
		
		Comparator<Mitglied> mCmp = new mComparator();
		
		ObjectContainer dbR=Db4o.openFile(args[0]);
		ObjectContainer dbM=Db4o.openFile(args[1]);
		printoutAbw = printFile (args[2]);
		printoutNo = printFile (args[3]);
		printoutDup = printFile (args[4]);

		Query qR=dbR.query();
		qR.constrain(Mitglied.class);
		qR.sortBy(mCmp);
		ObjectSet<Mitglied> rR =qR.execute();
		
		while(rR.hasNext()) {
			
			Mitglied m = rR.next();
			if ((m.getZuname()+", "+m.getVorname()).equals(nameAlt) ) 
				w(printoutDup, m.getZuname()+", "+m.getVorname()+" doppelt in "+args[0]);
			nameAlt = m.getZuname()+", "+m.getVorname();
			Query qName = dbM.query();
			qName.constrain(Mitglied.class);
			Constraint cZuname = qName.descend("zuname").constrain(m.getZuname());
			Constraint cVorname = qName.descend("vorname").constrain(m.getVorname());
			qName.constraints().and(cZuname).and(cVorname);
			ObjectSet<Mitglied> rM = qName.execute();
			if (rM.size() == 0) {
				anzNicht++;
				w(printoutNo, m.getZuname()+", "+m.getVorname()+" nicht in "+args[1]);
				}
			else
				if (rM.size() == 1) {
					System.out.println(m.getZuname()+", "+m.getVorname()+" auch  in "+args[1]);
					Mitglied mM = rM.next();
					printAbweichung (m, mM, printoutAbw);
				}
					
				else 
					System.out.println(m.getZuname()+", "+m.getVorname()+" "+rM.size()+"-fach in "+args[1]);
		}
		
		System.out.println("==========================");
		System.out.println("Datei "+args[0]+" Sätze:"+rR.size());
		System.out.println("nicht in "+args[1]+":"+anzNicht);
		
		dbM.close();
		dbR.close();
		try {
			printoutAbw.close();
			printoutNo.close();
			printoutDup.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static class mComparator implements Comparator<Mitglied>
	{

		@Override
		public int compare(Mitglied m1, Mitglied m2) {
						
			int r = (m1.getZuname()+m1.getVorname()).compareTo(m2.getZuname()+m2.getVorname());
				
			return r;
		}
		
	}
	
	private static BufferedWriter printFile (String objName) {
		
	    BufferedWriter retBuf = null;
	    
		try {
			FileOutputStream fileOutputStream = new FileOutputStream (objName+".TXT");
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
	
	private static void printAbweichung (Mitglied m1, Mitglied m2, BufferedWriter p) {
				
		abweichung = false;
		
		String na = m1.getZuname()+","+m1.getVorname();
		
		if (m1.getAmt() != m2.getAmt())
			wAbw(na, p, " Amt: "+m1.getAmt()+" "+m2.getAmt());
		if (unEqual(m1.getAustrittsdatum(), m2.getAustrittsdatum()))
			wAbw(na, p, " Austrittsdatum: "+dL(m1.getAustrittsdatum())+" <----> "+dL(m2.getAustrittsdatum()));
		if (unEqual(m1.getDienstanschluss(), m2.getDienstanschluss()))
			wAbw(na, p, " Dienstanschluss: "+m1.getDienstanschluss()+" <----> "+m2.getDienstanschluss());
		if (unEqual(m1.getEintrittsdatum(), m2.getEintrittsdatum()))
			wAbw(na, p, " Eintrittsdatum: "+dL(m1.getEintrittsdatum())+" <----> "+dL(m2.getEintrittsdatum()));
		if (unEqual(m1.getEmail(), m2.getEmail()))
			wAbw(na, p, " Email: "+m1.getEmail()+" <----> "+m2.getEmail());
		if (unEqual(m1.getFestnetz(), m2.getFestnetz()))
			wAbw(na, p, " Festnetz: "+m1.getFestnetz()+" <----> "+m2.getFestnetz());
		if (unEqual(m1.getGeburtsdatum(), m2.getGeburtsdatum()))
			wAbw(na, p, " Geburtsdatum: "+m1.getGeburtsdatum()+" <----> "+m2.getGeburtsdatum());
		if (unEqual(m1.getHandy(), m2.getHandy()))
			wAbw(na, p, " Handy: "+m1.getHandy()+" <----> "+m2.getHandy());
		if (unEqual(m1.getHausnummerZus(), m2.getHausnummerZus()))
			wAbw(na, p, " HausnummerZus: "+m1.getHausnummerZus()+" <----> "+m2.getHausnummerZus());
		if (unEqual(m1.getKontoinhaber(), m2.getKontoinhaber()))
			wAbw(na, p, " Kontoinhaber: "+m1.getKontoinhaber()+" <----> "+m2.getKontoinhaber());
		if (unEqual(m1.getKzA(), m2.getKzA()))
			wAbw(na, p, " KzA: "+m1.getKzA()+" <----> "+m2.getKzA());
		if (unEqual(m1.getKzB(), m2.getKzB()))
			wAbw(na, p, " KzB: "+m1.getKzB()+" <----> "+m2.getKzB());
		if (unEqual(m1.getKzC(), m2.getKzC()))
			wAbw(na, p, " KzC: "+m1.getKzC()+" <----> "+m2.getKzC());
		if (unEqual(m1.getOrt(), m2.getOrt()))
			wAbw(na, p, " Ort: "+m1.getOrt()+" <----> "+m2.getOrt());
		if (unEqual(m1.getStatus(), m2.getStatus()))
			wAbw(na, p, " Status: "+m1.getStatus()+" <----> "+m2.getStatus());
		if (unEqual(m1.getStrasse(), m2.getStrasse()))
			wAbw(na, p, " Strasse: "+m1.getStrasse()+" <----> "+m2.getStrasse());
		if (unEqual(m1.getVorname(), m2.getVorname()))
			wAbw(na, p, " Vorname: "+m1.getVorname()+" <----> "+m2.getVorname());
		if (unEqual(m1.getZuname(), m2.getZuname()))
			wAbw(na, p, " Zuname: "+m1.getZuname()+" <----> "+m2.getZuname());
		if (m1.getBankleitzahl() != m2.getBankleitzahl())
			wAbw(na, p, " Bankleitzahl: "+m1.getBankleitzahl()+" <----> "+m2.getBankleitzahl());
		if (m1.getKontonummer() != m2.getKontonummer())
			wAbw(na, p, " Kontonummer: "+m1.getKontonummer()+" <----> "+m2.getKontonummer());
		if (m1.getBeitragsklasse() != m2.getBeitragsklasse())
			wAbw(na, p, " Beitragsklasse: "+m1.getBeitragsklasse()+" <----> "+m2.getBeitragsklasse());
		if (m1.getPostleitzahl() != m2.getPostleitzahl())
			wAbw(na, p, " Postleitzahl: "+m1.getPostleitzahl()+" <----> "+m2.getPostleitzahl());
		if (m1.getHausnummer() != m2.getHausnummer())
			wAbw(na, p, " Hausnummer: "+m1.getHausnummer()+" <----> "+m2.getHausnummer());
		if (m1.getGeschlecht() != m2.getGeschlecht())
			wAbw(na, p, " Geschlecht: "+m1.getGeschlecht()+" <----> "+m2.getGeschlecht());
		if (m1.getZahlungsart() != m2.getZahlungsart())
			wAbw(na, p, " Zahlungsart: "+m1.getZahlungsart()+" <----> "+m2.getZahlungsart());
	}
	
	private static void wAbw (String n, BufferedWriter p, String s ) {
		if (!abweichung) {
			abweichung = true;
			w(p, n);
		}
		w(p, s);
	}
	
	private static void w (BufferedWriter p, String s) {
		try {
			p.write(s);
			p.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static boolean unEqual (String o1, String o2) {
		
		if (o1 == null)
			o1 = "";
		if (o2 == null)
			o2 = "";
		
		if (o1.trim().equals(o2.trim()))
			return false;
		else
			return true;
	}
	
	private static boolean unEqual (Date d1, Date d2) {
		if (d1 == null && d2 == null)
			return false;
		if (d1 == null && d2 != null)
			return true;
		if (d1 != null && d2 == null)
			return true;
		if (dL(d1).equals(dL(d2)))
			return false;
		else
			return true;
	}
	
	@SuppressWarnings("deprecation")
	private static String dL(Date d) {
		if (d == null)
			return "";
		else
			return d.toLocaleString().split(" ")[0];
	}
}
