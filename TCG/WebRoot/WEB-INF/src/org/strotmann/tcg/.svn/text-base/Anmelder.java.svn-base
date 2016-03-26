package org.strotmann.tcg;

import java.util.ResourceBundle;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;


public class Anmelder {
	
	private ResourceBundle benutzerProps = ResourceBundle.getBundle("Benutzer");
	
	private String benutzer;
    private String kennwort;
    private String nachricht;
    
	public String getNachricht() {
		return nachricht;
	}
	public void setNachricht(String nachricht) {
		this.nachricht = nachricht;
	}
	public String getBenutzer() {
		return benutzer;
	}
	public void setBenutzer(String benutzer) {
		this.benutzer = benutzer;
	}
	public String getKennwort() {
		return kennwort;
	}
	public void setKennwort(String kennwort) {
		this.kennwort = kennwort;
	}
	
	public String anmeldeAction() {
		String s = null;
		String kennwort = null;
		try {
			s = this.benutzerProps.getString(this.benutzer);
			kennwort = s.split(",")[0];
		} catch (RuntimeException e) {
		}
        if (kennwort == null) {
        	this.nachricht = "Benutzer ist dem System unbekannt";
        	return "Fehler";
        }
        else if (this.kennwort.equals(kennwort)) {
        		storeBenutzer (this.benutzer, s.split(","));
        		return "Erfolg";
        		}
        	else	{
        		this.nachricht = "Kennwort ist falsch";
            	return "Fehler";
        	}
        		
    }
	
	private void storeBenutzer (String tcgUser, String [] props) {
		Benutzer b = null;
		if (props.length == 1) {
			ResourceBundle p = ResourceBundle.getBundle("MailServer");
			b = new Benutzer (tcgUser, props[0], p.getString("smtpServer"), p.getString("from"), p.getString("user"), p.getString("password"));
		}
		else {		
			b = new Benutzer (tcgUser, props[0], props[1], props[2], props[3], props[4]); 
			}
		ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		servletContext.setAttribute("Benutzer", b);
	}
	
}
