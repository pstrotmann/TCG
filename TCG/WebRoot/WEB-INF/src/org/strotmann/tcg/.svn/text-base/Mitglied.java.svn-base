package org.strotmann.tcg;

import java.io.Serializable;
import java.util.Date;

public class Mitglied implements Serializable
{
	final static long serialVersionUID = 1L;
	
	private long mitgliedId;
	
	private String zuname;
	private String vorname;
	private char geschlecht; 
	private Date geburtsdatum;
	private Date eintrittsdatum;
	private Date austrittsdatum;
	private short amt;
	private short zahlungsart;
	private short beitragsklasse;
	private long bankleitzahl;
	private long kontonummer;
	private String kontoinhaber;
	private String IBAN;
	private String BIC;
	private String festnetz;
	private String dienstanschluss;
	private String handy;
	private String email;
	private String strasse;
	private int hausnummer;
	private String hausnummerZus;
	private int postleitzahl;
	private String ort;
	private String kzA;
	private String kzB;
	private String kzC;	
		
	public long getBankleitzahl() {
		return bankleitzahl;
	}
	public void setBankleitzahl(long bankleitzahl) {
		this.bankleitzahl = bankleitzahl;
	}
	public Date getEintrittsdatum() {
		return eintrittsdatum;
	}
	public void setEintrittsdatum(Date eintrittsdatum) {
		this.eintrittsdatum = eintrittsdatum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFestnetz() {
		return festnetz;
	}
	public void setFestnetz(String festnetz) {
		this.festnetz = festnetz;
	}
	public Date getGeburtsdatum() {
		return geburtsdatum;
	}
	public void setGeburtsdatum(Date geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}
	public String getHandy() {
		return handy;
	}
	public void setHandy(String handy) {
		this.handy = handy;
	}
	public int getHausnummer() {
		return hausnummer;
	}
	public void setHausnummer(int hausnummer) {
		this.hausnummer = hausnummer;
	}
	public String getHausnummerZus() {
		return hausnummerZus;
	}
	public void setHausnummerZus(String hausnummerZus) {
		this.hausnummerZus = hausnummerZus;
	}
	public String getKontoinhaber() {
		return kontoinhaber;
	}
	public void setKontoinhaber(String kontoinhaber) {
		this.kontoinhaber = kontoinhaber;
	}
	public long getKontonummer() {
		return kontonummer;
	}
	public void setKontonummer(long kontonummer) {
		this.kontonummer = kontonummer;
	}
	public long getMitgliedId() {
		return mitgliedId;
	}
	public void setMitgliedId(long mitgliedId) {
		this.mitgliedId = mitgliedId;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public int getPostleitzahl() {
		return postleitzahl;
	}
	public void setPostleitzahl(int postleitzahl) {
		this.postleitzahl = postleitzahl;
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getZuname() {
		return zuname;
	}
	public void setZuname(String zuname) {
		this.zuname = zuname;
	}
	public Date getAustrittsdatum() {
		return austrittsdatum;
	}
	public void setAustrittsdatum(Date austrittsdatum) {
		this.austrittsdatum = austrittsdatum;
	}
	public String getStatus() {
		if (this.beitragsklasse == 7)
			return "p";
		else
			return "a";
	}
	public String getKzA() {
		return kzA;
	}
	public void setKzA(String kzA) {
		this.kzA = kzA;
	}
	public String getKzB() {
		return kzB;
	}
	public void setKzB(String kzB) {
		this.kzB = kzB;
	}
	public String getKzC() {
		return kzC;
	}
	public void setKzC(String kzC) {
		this.kzC = kzC;
	}
	public String getDienstanschluss() {
		return dienstanschluss;
	}
	public void setDienstanschluss(String dienstanschluss) {
		this.dienstanschluss = dienstanschluss;
	}
	public short getAmt() {
		return amt;
	}
	public void setAmt(short amt) {
		this.amt = amt;
	}
	public char getGeschlecht() {
		return geschlecht;
	}
	public void setGeschlecht(char geschlecht) {
		this.geschlecht = geschlecht;
	}
	public short getZahlungsart() {
		return zahlungsart;
	}
	public void setZahlungsart(short zahlungsart) {
		this.zahlungsart = zahlungsart;
	}
	public short getBeitragsklasse() {
		return beitragsklasse;
	}
	public void setBeitragsklasse(short beitragsklasse) {
		this.beitragsklasse = beitragsklasse;
	}

	public String getStrasseNrZus() {
		return strasse+' '+hausnummer+' '+hausnummerZus;
	}
	
	public String getIBAN() {
		return this.IBAN;
	}
	
	public void setIBAN(String iBAN) {
		this.IBAN = iBAN;
	}
	
	public String getBIC() {
		return BIC;
	}
	public void setBIC(String bIC) {
		BIC = bIC;
	}
}
