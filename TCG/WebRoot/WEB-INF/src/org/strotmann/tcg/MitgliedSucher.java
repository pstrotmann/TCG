package org.strotmann.tcg;

import java.util.Calendar;
import java.util.Date;

public class MitgliedSucher {
	private String vorname;
    private String zuname;
    private String geschlecht; 
    private Date geburtsdatumAb;
    private Date geburtsdatumBis;
    private long gebVonBis;
    private Date eintrittsdatumAb;
    private Date eintrittsdatumBis;
    private long einVonBis;
    private Date austrittsdatumAb;
    private Date austrittsdatumBis;
    private long ausVonBis;
    private String status;
    private String email;
    private short amt;
    
    public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	
	public long getGeschNum() {
		if (this.geschlecht == null)
			return 0;
		else if (this.geschlecht.equals("m"))
				return 1;
			else
				return 2;
	}
	public void setGeschNum(long geschNum) {
		if (geschNum == 0)
			this.geschlecht = null;
		else
			if (geschNum == 1)
				this.geschlecht = "m";
			else
				this.geschlecht = "w";
	}
	
	public long getStatNum() {
		if (this.status == null)
			return 0;
		else if (this.status.equals("a"))
				return 1;
			else
				return 2;
	}
	public void setStatNum(long statNum) {
		if (statNum == 0)
			this.status = null;
		else
			if (statNum == 1)
				this.status = "a";
			else
				this.status = "p";
	}
	
	public boolean getAmt () {
		if (this.amt == 1)
			return true;
		else
			return false;
	}
	
	public void setAmt (boolean b) {
		if (b)
			this.amt = 1;
		else
			this.amt = 0;
	}
	
	public boolean matchVorname (String vorname) {
		if (this.vorname == null || this.vorname.trim().length() == 0)
			return true;
		if (vorname.startsWith(this.vorname.trim()))
			return true;
		else
			return false;
	}
	
	public boolean matchZuname (String zuname) {
		if (this.zuname == null || this.zuname.trim().length() == 0)
			return true;
		if (zuname.startsWith(this.zuname.trim()))
			return true;
		else
			return false;
	}
	
	public boolean matchGeschlecht (char geschlecht) {
		if (this.geschlecht == null || this.geschlecht.trim().length() == 0)
			return true;
		if (this.geschlecht.charAt(0) == geschlecht)
			return true;
		else
			return false;
	}

	public boolean matchGeburtsdatum (Date gebDat) {
		return between(this.geburtsdatumAb, gebDat, this.geburtsdatumBis);
	}
	
	public boolean matchEintrittsdatum (Date einDat) {
		return between(this.eintrittsdatumAb, einDat, this.eintrittsdatumBis);
	}
	
	public boolean matchAustrittsdatum (Date ausDat) {
		if (this.austrittsdatumAb == null & this.austrittsdatumBis == null)
			if (ausDat == null)
				return true;
			else
				return false;
		else
			if (ausDat == null)
				return false;
			else
				return between(this.austrittsdatumAb, ausDat, this.austrittsdatumBis);
	}
	
	public boolean matchStatus (String status) {
		if (this.status == null || this.status.trim().length() == 0)
			return true;
		if (this.status.equals(status)) 
			return true;
		else
			return false;
	}
	
	public boolean matchAmt (short amt) {
		if (this.amt == 0)
			return true;
		if (this.amt == 1 && amt > 0) 
			return true;
		else
			return false;
	}
	
	public long getGebVonBis() {
		return gebVonBis;
	}
	public void setGebVonBis(long gebVonBis) {
		this.gebVonBis = gebVonBis;
	}
	public long getAusVonBis() {
		return ausVonBis;
	}
	public void setAusVonBis(long ausVonBis) {
		this.ausVonBis = ausVonBis;
	}
	public long getEinVonBis() {
		return einVonBis;
	}
	public void setEinVonBis(long einVonBis) {
		this.einVonBis = einVonBis;
	}
	public Date getGeburtsdatumBis() {
		return geburtsdatumBis;
	}
	public void setGeburtsdatumBis(Date geburtsdatumBis) {
		this.geburtsdatumBis = geburtsdatumBis;
	}
	public Date getGeburtsdatumAb() {
		return geburtsdatumAb;
	}
	public void setGeburtsdatumAb(Date geburtsdatumAb) {
		this.geburtsdatumAb = geburtsdatumAb;
	}
	public Date getAustrittsdatumAb() {
		return austrittsdatumAb;
	}
	public void setAustrittsdatumAb(Date austrittsdatumAb) {
		this.austrittsdatumAb = austrittsdatumAb;
	}
	public Date getAustrittsdatumBis() {
		return austrittsdatumBis;
	}
	public void setAustrittsdatumBis(Date austrittsdatumBis) {
		this.austrittsdatumBis = austrittsdatumBis;
	}
	public Date getEintrittsdatumAb() {
		return eintrittsdatumAb;
	}
	public void setEintrittsdatumAb(Date eintrittsdatumAb) {
		this.eintrittsdatumAb = eintrittsdatumAb;
	}
	public Date getEintrittsdatumBis() {
		return eintrittsdatumBis;
	}
	public void setEintrittsdatumBis(Date eintrittsdatumBis) {
		this.eintrittsdatumBis = eintrittsdatumBis;
	}
	
	boolean between (Date von, Date dat, Date bis) {
		
		String sVon = null;
		String sBis = null;
		String sDat = null;
		
		if (von == null)
			sVon = "00000000";
		else
			sVon = strDat(von);
		
		if (bis == null)
			sBis = "99999999";
		else
			sBis = strDat(bis);
		
		sDat = strDat(dat);
		 
		if (sVon.compareTo(sDat) <= 0  & sDat.compareTo(sBis) <= 0)
			return true;
		else
			return false;
	}
	
	String strDat (Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int tt = c.get(Calendar.DAY_OF_MONTH);
		int mm = c.get(Calendar.MONTH) + 1;
		int jjjj = c.get(Calendar.YEAR);
		return jjjj+lPadZwei(mm)+lPadZwei(tt) ;
	}
	
	private String lPadZwei (int i) {
		if (i < 10)
		   return "0"+i;
		else
		   return ""+i;	
	}
}
