package org.strotmann.tcg;

public class MitgliederListeEintrag {
	boolean selektiert;
	Mitglied mitglied;
	public MitgliederListeEintrag(boolean selektiert, Mitglied mitglied) {
		super();
		this.selektiert = selektiert;
		this.mitglied = mitglied;
	}
	public Mitglied getMitglied() {
		return mitglied;
	}
	public void setMitglied(Mitglied mitglied) {
		this.mitglied = mitglied;
	}
	public boolean getSelektiert() {
		return selektiert;
	}
	public void setSelektiert(boolean selektiert) {
		this.selektiert = selektiert;
	}
	
	public String getAnrede () {
		if (this.mitglied.getGeschlecht() == 'm')
			return "Sehr geehrter Herr "+this.mitglied.getZuname();
		else
			return "Sehr geehrte Frau "+this.mitglied.getZuname();
	}
}
