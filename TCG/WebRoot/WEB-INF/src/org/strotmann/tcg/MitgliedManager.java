package org.strotmann.tcg;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletContext;

import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;
import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.strotmann.mail.MailSender;

public class MitgliedManager 
{
	private long mitgliedId;
	private Mitglied mitglied;
	private MitgliedSucher mitgliedSucher;
	private List <Mitglied> mitgliederListe;
	private List <Mitglied> beitragsliste;
	private List <MitgliederListeEintrag> mailEmpfaengerListe;
	private List <MitgliederListeEintrag> adressenListe;
	private MitgliedPersister mitgliedPersister;
	private String erfNachricht;
	private String _sort;
    private boolean _ascending;
    private String betreff;
    private String mailText;
    private UploadedFile _upFile1, _upFile2, _upFile3;
	
	public String getBetreff() {
		return betreff;
	}

	public String getMailText() {
		return mailText;
	}

	public void setBetreff(String betreff) {
		this.betreff = betreff;
	}

	public void setMailText(String mailText) {
		this.mailText = mailText;
	}

	public boolean isAscending() {
		return _ascending;
	}

	public void setAscending(boolean _ascending) {
		this._ascending = _ascending;
	}

	public String getSort() {
		return _sort;
	}

	public void setSort(String _sort) {
		this._sort = _sort;
	}

	public String homeAction() {
        
        return "Erfolg";
    }
	
	public void getMitgliedAction(ActionEvent e) throws AbortProcessingException {
		HtmlCommandButton h = (HtmlCommandButton)e.getSource();
		long mId = (Long)h.getValue();
		this.mitglied = mitgliedPersister.readMitglied(mId);
	}
	
	public void getSucherAction(ActionEvent e) throws AbortProcessingException {
		HtmlCommandButton h = (HtmlCommandButton)e.getSource();
		this.mitgliedSucher = (MitgliedSucher)h.getValue();
	}
	
	public String UebernahmeAction() {
		this.erfNachricht = "Übernahme der Daten erfolgreich";
		new MitgliederImporter().importiere();
		return "Uebernahme";
    }
	
	public String uebernahmeEmailAction() {
		this.erfNachricht = "Übernahme der eMail-Adressen erfolgreich";
		new EmailImporter().importiere();
		return "Uebernahme";
    }
	
	public String selectAction() {
		this.erfNachricht = "";
		return "Aendern";
    }
	
	public String beitraegeAction() {
		this.erfNachricht = "";
		return "Beitraege";
    }
	
	public String adressenAction() {
		this.adressenListe = null;
		this.erfNachricht = "";
		return "Adressen";
    }
	
	public String mailsAction() {
		this.mailEmpfaengerListe = null;
		this.erfNachricht = "";
		return "Mails";
    }
	
	public String abbuchAction() {
		Dta dta = new Dta(this.beitragsliste);
		String dtaName = dta.erzeugeDta();
		this.erfNachricht = "Name der DTA-Datei: " + dtaName;
		return "Erfolg";
    }
	
	public String adrKomprAction() {
		
		List <MitgliederListeEintrag> liste = new ArrayList();
		
		if (this.adressenListe != null) 
			
			for (MitgliederListeEintrag mitgliederListeEintrag : this.adressenListe) {
				if (mitgliederListeEintrag.getSelektiert())
					liste.add(mitgliederListeEintrag);
			}
		
		this.adressenListe = liste;
		this.erfNachricht = "";
        return "ListeKompr";
    }
	
	public String mailKomprAction() {
		
		List <MitgliederListeEintrag> liste = new ArrayList();
		
		if (this.mailEmpfaengerListe != null) 
			
			for (MitgliederListeEintrag mitgliederListeEintrag : this.mailEmpfaengerListe) {
				if (mitgliederListeEintrag.getSelektiert())
					liste.add(mitgliederListeEintrag);
			}
		
		this.mailEmpfaengerListe = liste;
		this.erfNachricht = "";
        return "ListeKompr";
    }
	
	public String sendeAction() {
		
		MailSender mailSender = new MailSender();
		ArrayList<InternetAddress> mailAdressen = new ArrayList <InternetAddress> ();
		ArrayList<InternetAddress> mailAdresseBen = new ArrayList <InternetAddress> ();
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		Benutzer benutzer = (Benutzer)servletContext.getAttribute("Benutzer");
		String gesendetAn = "";
		
		for (MitgliederListeEintrag mitgliederListeEintrag : this.mailEmpfaengerListe) {
			try {
				if (mitgliederListeEintrag.getSelektiert()) {
					mailAdressen.add(new InternetAddress(mitgliederListeEintrag.getMitglied().getEmail()));
					gesendetAn = gesendetAn + "\n" +mitgliederListeEintrag.getMitglied().getEmail() ;
				}
			} catch (AddressException e) {
				e.printStackTrace();
			}
			try {
				mailAdresseBen.add(new InternetAddress(benutzer.getMailUser()));
			} catch (AddressException e) {
				e.printStackTrace();
			}
		}
		
		InternetAddress[] internetAddressen = new InternetAddress[mailAdressen.size()];
		internetAddressen = mailAdressen.toArray(internetAddressen);
		InternetAddress[] internetAddresseBen = new InternetAddress[1];
		internetAddresseBen = mailAdresseBen.toArray(internetAddresseBen);
		
		try {
			byte[] attachment1 = null, attachment2 = null, attachment3 = null;
			String attachment1Name = null, attachment2Name = null, attachment3Name = null;
			if (this._upFile1 != null) {
				attachment1 = this._upFile1.getBytes();
				attachment1Name = this._upFile1.getName();
			}
			if (this._upFile2 != null) {
				attachment2 = this._upFile2.getBytes();
				attachment2Name = this._upFile2.getName();
			}
			if (this._upFile3 != null) {
				attachment3 = this._upFile3.getBytes();
				attachment3Name = this._upFile3.getName();
			}
			mailSender.send(internetAddressen, this.betreff, this.mailText
					, attachment1Name, attachment1, attachment2Name, attachment2, attachment3Name, attachment3);
			mailSender.send(internetAddresseBen, this.betreff, this.mailText + "\n \n gesendet an:" + gesendetAn
					, attachment1Name, attachment1, attachment2Name, attachment2, attachment3Name, attachment3);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.erfNachricht = "Mail wurde erfolgreich an "+ mailEmpfaengerListe.size() +" Empfänger versendet.";
		return "Erfolg";
    }
	
	public String neuAction() {
		
		this.mitglied = new Mitglied();
		this.erfNachricht = "";
        return "Neu";
    }
	
	public String suchAction() {
        return "Suchen";
    }
	
	@SuppressWarnings("deprecation")
	public String storeAction() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		HSSFWorkbook wb = null;
		boolean datOk = false, ibanOk = false;
		
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream("/vol/BLZ_BIC.xls"));
			wb = new HSSFWorkbook(fs);
		} catch (Exception e) {
			this.erfNachricht = "Fehler BIC Datei";
		}		
		
		Date d1 = this.mitglied.getGeburtsdatum();
		Date d2 = this.mitglied.getEintrittsdatum();
		Date d3 = this.mitglied.getAustrittsdatum();
		
		if (compare (d1, d2) <= 0 && compare (d2, d3) <= 0) {
			datOk = true;			
		}
		else {
			
			if (compare (d1, d2) > 0) {
				
				UIInput gebDatIn = (UIInput) context.getViewRoot()
						.findComponent("form1:geburtsdatum");
				gebDatIn.setValid(false);
				context.addMessage(gebDatIn.getClientId(context),
						new FacesMessage(
								"Geburtsdatum liegt nach Eintrittsdatum"));
			}
			if (compare (d2, d3) > 0) {
				
				UIInput einDatIn = (UIInput) context.getViewRoot()
						.findComponent("form1:eintrittsdatum");
				einDatIn.setValid(false);
				context.addMessage(einDatIn.getClientId(context),
						new FacesMessage(
								"Eintrittsdatum liegt nach Austrittsdatum"));
				
			}
			
			this.erfNachricht = "Fehler bei der Querpr�fung";			
		}
		
		if (this.mitglied.getIBAN().compareTo(" ") > 0) {
			ibanOk = true;
			this.mitglied.setBankleitzahl(new Long(this.mitglied.getIBAN().substring(4, 12)));
			this.mitglied.setKontonummer(new Long(this.mitglied.getIBAN().substring(12)));
			boolean fertig = false;
			HSSFSheet sheet = wb.getSheetAt(0);
			for (int i = 0; fertig == false && i < sheet.getLastRowNum(); i++) {
				HSSFRow row = sheet.getRow(i);
				String blz = row.getCell(0).getStringCellValue();
				if (blz.equals(((Long)this.mitglied.getBankleitzahl()).toString())) {
					fertig = true;
					this.mitglied.setBIC(row.getCell(4).getStringCellValue());
				}
			}
		}
		else {
			this.mitglied.setBankleitzahl(0);
			this.mitglied.setKontonummer(0);
			if (this.mitglied.getZahlungsart() > 1)
				ibanOk = true;
			else
				this.erfNachricht = "Lastschrift ohne Iban nicht m�glich";
		}
		if (datOk && ibanOk) {
			mitgliedPersister.store(this.mitglied);
			this.erfNachricht = "Erfassung erfolgreich: "+this.mitglied.getVorname()+" "+this.mitglied.getZuname();
	        return "Erfolg";
		}
		else
			return "Fehler";
    }
	
	public String deleteAction() {
		mitgliedPersister.delete(this.mitglied);
		this.erfNachricht = "L�schung erfolgreich: "+this.mitglied.getVorname()+" "+this.mitglied.getZuname();
        return "Erfolg";
	}
	
	public List<Mitglied> getMitgliederListe() {
		
		Date tadat = new Date();
		
		if (this.mitgliederListe == null)
			this.mitgliederListe = new ArrayList();
		else
			this.mitgliederListe.clear();
		if (this.mitgliedSucher ==  null)
			this.mitgliedSucher = new MitgliedSucher();
		List <Mitglied> liste;
		liste = mitgliedPersister.readMitglieder();
		for (Mitglied mitglied : liste) {
			if (mitgliedSucher.matchVorname(mitglied.getVorname()) &
				mitgliedSucher.matchZuname(mitglied.getZuname()) &
				mitgliedSucher.matchGeschlecht(mitglied.getGeschlecht()) &
				mitgliedSucher.matchGeburtsdatum(mitglied.getGeburtsdatum()) &
				mitgliedSucher.matchEintrittsdatum(mitglied.getEintrittsdatum()) &
				(mitgliedSucher.matchAustrittsdatum(mitglied.getAustrittsdatum()) || nachHeute(mitglied.getAustrittsdatum(),tadat) ) &
				mitgliedSucher.matchStatus(mitglied.getStatus()) &
				mitgliedSucher.matchAmt(mitglied.getAmt())
				)
				this.mitgliederListe.add(mitglied);
		}
        sort(getSort());
		return this.mitgliederListe;
	}
	
	boolean nachHeute (Date dat, Date tadat) {
		if (dat == null)
			return false;
		else
			return dat.after(tadat);
	}
	
	public List<Mitglied> getBeitragsliste() {
		
		this.beitragsliste = new ArrayList();
		
		for (Mitglied mitglied : this.mitgliederListe) {
			if (mitglied.getBeitragsklasse() > 0)
				this.beitragsliste.add(mitglied);
		}
        return this.beitragsliste; 		
	}
	
	public List<MitgliederListeEintrag> getAdressliste() {
		
		List <MitgliederListeEintrag> liste = null;
		
		if (this.adressenListe == null) {
			liste = new ArrayList();
			for (Mitglied mitglied : this.mitgliederListe) {
				if (mitglied.getKzA().equals("1")) {
					if (mitglied.getEmail() == null || mitglied.getEmail().length() == 0) 
						liste.add(new MitgliederListeEintrag(true, mitglied));
					else
						liste.add(new MitgliederListeEintrag(false, mitglied));
				}
			}
			this.adressenListe = liste;
		}
		else
			liste = this.adressenListe;
		
		return liste;
	}
	
	public List<MitgliederListeEintrag> getMailliste() {
		
		List <MitgliederListeEintrag> liste = null;
		
		if (this.mailEmpfaengerListe == null) {
			liste = new ArrayList();
			for (Mitglied mitglied : this.mitgliederListe) {
				if (mitglied.getEmail() != null
						&& mitglied.getEmail().length() > 0)
					liste.add(new MitgliederListeEintrag(true, mitglied));
			}
			this.mailEmpfaengerListe = liste;
		}
		else
			liste = this.mailEmpfaengerListe;
		
		return liste;
	}

	public long getMitgliedId() {
		return this.mitgliedId;
	}

	public void setMitgliedId(long mitgliedId) {
		this.mitgliedId = this.mitgliedPersister.getMitgliedId(this.mitglied);
	}

	public MitgliedManager() {
		
		this.mitglied = new Mitglied();
		this.mitgliedPersister = new MitgliedPersisterDB4Objects();
		this._ascending = true;
		this._sort = "zuname";
	}

	public Mitglied getMitglied() {
		return mitglied;
	}

	public void setMitglied(Mitglied mitglied) {
		this.mitglied = mitglied;
	}

	public void sort(final String column)
    {
        if (mitgliederListe == null)
        	return;
		Comparator comparator = new mitgliedComparator(column) ;
        Collections.sort(mitgliederListe , comparator);
    }
	
	class mitgliedComparator implements Comparator {
		
		String column;
		
		mitgliedComparator (String column) {
			this.column = column;
		}
		
		public int compare(Object o1, Object o2)
        {
            Mitglied c1 = (Mitglied)o1;
            Mitglied c2 = (Mitglied)o2;
            if(column == null || o1 == null || o2 == null )
                return 0;
            if(column.equals("zuname"))
                return c1.getZuname().compareTo(c2.getZuname()) ;
            else
        	if(column.equals("vorname"))
                return c1.getVorname().compareTo(c2.getVorname()) ;
            else
        	if(column.equals("geschlecht"))
                if (c1.getGeschlecht() == 1)
                	return -1;
                else
                	return 1;
            else
        	if(column.equals("geburtsdatum"))
                return c1.getGeburtsdatum().compareTo(c2.getGeburtsdatum()) ;
            else
        	if(column.equals("eintrittsdatum"))
                return c1.getEintrittsdatum().compareTo(c2.getEintrittsdatum()) ;
            else
        	if(column.equals("status"))
                if (c1.getStatus().equals("a"))
                	return -1;
                else
                	return 1;
            else
        	if(column.equals("austrittsdatum"))
        		if (c1.getAustrittsdatum() == null ) 
					return 1;
        		else
        		if (c2.getAustrittsdatum() == null) 
        			return -1;
        		else
        			return c1.getAustrittsdatum().compareTo(c2.getAustrittsdatum()) ;
            else
        	if(column.equals("email"))
        		if (c1.getEmail()== null) 
					return 1;
        		else
        		if (c2.getEmail() == null) 
        			return -1;
        		else
        			return c1.getEmail().compareTo(c2.getEmail()) ;
            else
            return 0;
        }
	}
	
	public void valIban(FacesContext context,
			UIComponent toValidate, Object value) {
		if (!new IBANCheckDigit().isValid((String)value)) {
			((UIInput)toValidate).setValid(false);
			context.addMessage(toValidate.getClientId(context), new FacesMessage("Iban falsch"));
		}
	}
	
	public void valDatum(FacesContext context,
			UIComponent toValidate, Object value) {
		String m = "";
		Date date = (Date)value;
		m = valJahr (date);
		
		if (m.length() > 0) {
			((UIInput)toValidate).setValid(false);
			context.addMessage(toValidate.getClientId(context), new FacesMessage(m));
		}
	}
	
	private String valJahr (Date date) {
		String message = "";
		Date d = (Date) date;
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		int jjjj = c.get(Calendar.YEAR);
		if (jjjj < 1900 || jjjj > 2100) {
			message = "Jahr ung�ltig";
		}
		return message;
	}
	
	private int compare (Date d1, Date d2) {
		if (d1 == null || d2 == null) 
			return 0;
		else
			return d1.compareTo(d2);
	}

	public String getErfNachricht() {
		return erfNachricht;
	}
	
	public String getAnzahlMitglieder () {
		return "Anzahl "+this.getMitgliederListe().size();
	}
	
	public String getAnzahlAdressen () {
		return "Anzahl "+this.getAdressliste().size();
	}
	
	public String getAnzahlMailEmpfaenger () {
		return "Anzahl "+this.getMailliste().size();
	}
	
	public String getAnzahlBeitragszahler () {
		return "Anzahl "+this.getBeitragsliste().size();
	}
	
	public boolean getBriefempfaenger () {
		if (this.mitglied.getKzA() == null)
			return false;
		else {
			if (this.mitglied.getKzA().equals("1"))
				return true;
			else
				return false;
		}
	}
	
	public void setBriefempfaenger (boolean b) {
		if (b)
			this.mitglied.setKzA("1");
		else
			this.mitglied.setKzA(" ");
		
	}
	
	public String getAnrede () {
		if (this.mitglied.getGeschlecht() == 'm')
			return "Sehr geehrter Herr "+this.mitglied.getZuname();
		else
			if (this.mitglied.getGeschlecht() == 'w')
				return "Sehr geehrte Frau "+this.mitglied.getZuname();
			else return "";
	}

	public UploadedFile getUpFile1()
    {
        return _upFile1;
    }
    public void setUpFile1(UploadedFile upFile1)
    {
        _upFile1 = upFile1;
    }
    
    public UploadedFile getUpFile2()
    {
        return _upFile2;
    }
    public void setUpFile2(UploadedFile upFile2)
    {
        _upFile2 = upFile2;
    }
    
    public UploadedFile getUpFile3()
    {
        return _upFile3;
    }
    public void setUpFile3(UploadedFile upFile3)
    {
        _upFile3 = upFile3;
    }
    public Benutzer getBenutzer() {    	
    	ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    	Benutzer b = (Benutzer)servletContext.getAttribute("Benutzer");
    	return b;
    }
    public void setBenutzer(){
    	//jsf verlangt das
    }
}
